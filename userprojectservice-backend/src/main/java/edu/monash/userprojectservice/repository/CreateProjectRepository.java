package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.user.UserEntity;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import edu.monash.userprojectservice.service.UserGroupEnum;
import edu.monash.userprojectservice.service.UserGroupHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class CreateProjectRepository {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private UserGroupHelper userGroupHelper;

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    // create new method for insert...
    public Boolean save(
            String projectId,
            List<String> emailAddresses,
            String projectName,
            String projectUnit,
            Integer projectYear,
            String projectSemester
    ) throws SQLException {
        Map<String, String> emailsUserGroups = emailAddresses.stream().collect(Collectors.toMap(
                emailAddress -> emailAddress,
                emailAddress -> userGroupHelper.getUserGroupByEmail(emailAddress).name()
        ));

        Connection conn = DriverManager.getConnection(url, userName, password);
        try {
            conn.setAutoCommit(false);
            ProjectEntity projectEntity = new ProjectEntity(projectId, projectName, null, projectUnit, projectYear, projectSemester);
            projectsRepository.save(projectEntity);

            emailsUserGroups.forEach((email, userGroup) -> {
                if (usersRepository.findUserEntityByEmailAddress(email) == null) {
                    usersRepository.save(new UserEntity(email, "", "", userGroup));
                    // Created 201
                    log.info("{\"message\":\"Created new user while adding new project\"}");
                }
                usersProjectsRepository.save(new UsersProjectsEntity(email, projectId, null, null));
            });

            conn.commit();
            return true;
        } catch (Exception e) {

            // in case of exception, rollback the transaction
            if (e instanceof SQLException) {
                conn.rollback();

                return false;
            }

            throw e;
        }
    }
}
