package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class CreateProjectRepository {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    // create new method for insert...
    public Boolean save(String projectId, List<String> emailAddress, String projectName, String projectUnit, String projectYear, String projectSemester) throws SQLException {
        Connection conn = DriverManager.getConnection(url, userName, password);
        try {
            conn.setAutoCommit(false);
            projectsRepository.save(new ProjectEntity(projectId, projectName, null, projectUnit, projectYear, projectSemester));
            for(int i = 0; i < emailAddress.size(); i++) {
                if (usersRepository.findUserEntityByEmailAddress(emailAddress.get(i)) != null) {
                    usersProjectsRepository.save(new UsersProjectsEntity(emailAddress.get(i), projectId, new ProjectEntity(projectId, projectName, null, projectUnit, projectYear, projectSemester), null));
                }
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            // in case of exception, rollback the transaction
            conn.rollback();
            return false;
        }
    }
}
