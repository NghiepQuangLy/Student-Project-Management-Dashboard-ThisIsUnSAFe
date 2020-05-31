package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Repository
public class CreateProjectRepository {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;


    // create new method for insert...
    public Boolean save(String projectId, String emailAddress, String projectName) throws SQLException {
        Connection conn = DriverManager.getConnection(url, userName, password);
        try {
            conn.setAutoCommit(false);

            projectsRepository.save(new ProjectEntity(projectId, projectName));
            usersProjectsRepository.save(new UsersProjectsEntity(emailAddress, projectId, new ProjectEntity(projectId, projectName)));

            conn.commit();
            return true;
        } catch (SQLException e) {
            // in case of exception, rollback the transaction
            conn.rollback();
            return false;
        }
    }
}