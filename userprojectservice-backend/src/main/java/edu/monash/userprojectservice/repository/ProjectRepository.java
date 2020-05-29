package edu.monash.userprojectservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${spring.datasource.url}") String url;
    @Value("${spring.datasource.username}") String userName;
    @Value("${spring.datasource.password}") String password;


    // create new method for insert...
    public Boolean insertProject(String projectId,String userEmailAddress, String projectName) throws SQLException {
        Connection conn = DriverManager.getConnection(url,userName,password);
        try{
            conn.setAutoCommit(false);
            jdbcTemplate.update(
                    "INSERT INTO Projects (project_id, project_name) VALUES (?, ?)",
                    projectId, projectName
            );


            jdbcTemplate.update(
                    "INSERT INTO Users_Projects (email_address, project_id) VALUES (?, ?)",
                    userEmailAddress, projectId
            );

            conn.commit();
            return true;

        } catch(SQLException e) {
            // in case of exception, rollback the transaction
            conn.rollback();
            return false;
        }

    }
}
