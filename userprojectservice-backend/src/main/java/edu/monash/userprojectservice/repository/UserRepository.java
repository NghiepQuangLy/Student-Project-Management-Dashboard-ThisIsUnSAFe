package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.model.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(CreateUserRequest user) {
        jdbcTemplate.update(
                "INSERT INTO Users (email_address, given_name, family_name, user_group) VALUES (?, ?, ?, ?)",
                user.getEmailAddress(), user.getGivenName(),user.getFamilyName(), user.getUserGroup()
        );
    };

    public Optional<User> findUserByEmail(String email_address) {
        return jdbcTemplate.queryForObject(
                "select * from Users where email_address = ?",
                new Object[]{email_address},
                (ResultSet rs, int rowNum) ->
                        Optional.of(new User(
                                rs.getString("family_name"),
                                rs.getString("given_name"),
                                rs.getString("email_address"),
                                rs.getString("user_group")
                        ))
        );
    }

    public List<ProjectShortDetail> findProjectByEmail(String email_address) {
        return jdbcTemplate.query(
                "select email_address, u.project_id, project_name from SPMD.Users_Projects as u " +
                        "Join SPMD.Projects as p " +
                        "On u.project_id = p.project_id " +
                        "where u.email_address = ?",
                new Object[]{email_address},
                (ResultSet rs, int rowNum) -> new ProjectShortDetail(
                        rs.getString("project_id"),
                        rs.getString("project_name")
                )
        );
    }
}
