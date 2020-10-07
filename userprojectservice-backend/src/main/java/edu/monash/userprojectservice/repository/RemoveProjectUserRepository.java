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

    // create new method for deleting a record from the DB
    public Boolean delete(String projectId, String emailAddress) throws SQLException {
        Connection conn = DriverManager.getConnection(url, userName, password);
        try {
            conn.setAutoCommit(false);

            // create the java mysql delete prepared statement when provided with a user email address and project ID
            String query = "DELETE FROM USERS_PROJECTS WHERE (email_address=?) AND (project_id=?) ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, emailAddress);
            preparedStmt.setString(2, projectId);

            // execute the java prepared statement
            preparedStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            // in the case of an exception, rollback the transaction

            log.warn(String.valueOf(e));
            conn.rollback();
            return false;
        }
    }
}
