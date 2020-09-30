package edu.monash.userprojectservice.repository;

//import edu.monash.userprojectservice.repository.project.ProjectEntity;
//import edu.monash.userprojectservice.repository.project.ProjectsRepository;
//import edu.monash.userprojectservice.repository.user.UsersRepository;
//import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
//import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

@Slf4j
@Repository
public class EditProjectRepository {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    // create new method for insert...
    public Boolean save(String projectId, String newProjectName) throws SQLException {
        Connection conn = DriverManager.getConnection(url, userName, password);
        try {
            conn.setAutoCommit(false);

            // create the java mysql update preparedstatement
            String query = "update PROJECTS set project_name = ? where project_id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, newProjectName);
            preparedStmt.setString(2, projectId);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            // in case of exception, rollback the transaction
            log.warn(String.valueOf(e));
            conn.rollback();
            return false;
        }
    }
}
