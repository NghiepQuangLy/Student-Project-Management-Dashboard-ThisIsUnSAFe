package edu.monash.userprojectservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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

            // create the java mysql update prepared statement
            String query = "update PROJECTS set project_name = ? where project_id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, newProjectName);
            preparedStmt.setString(2, projectId);

            // execute the java prepared statement
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
