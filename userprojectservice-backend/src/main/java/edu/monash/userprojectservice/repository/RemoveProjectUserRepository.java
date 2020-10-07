package edu.monash.userprojectservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Slf4j
@Repository
public class RemoveProjectUserRepository {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    // create new method for delete...
    public Boolean delete(String projectId, String emailAddress) throws SQLException {
        Connection conn = DriverManager.getConnection(url, userName, password);
        try {
            conn.setAutoCommit(false);

            // create the java mysql update prepared statement
            String query = "DELETE FROM USERS_PROJECTS WHERE (email_address=?) AND (project_id=?) ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, emailAddress);
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
