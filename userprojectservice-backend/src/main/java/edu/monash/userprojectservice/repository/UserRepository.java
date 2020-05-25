package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public User create(User user) {
//
//    };

    public Optional<UserDetail> findByEmail(String email_address){
        return jdbcTemplate.queryForObject(
                "select * from Users where email_address = ?",
                new Object[]{email_address},
                (rs, rowNum) ->
                        Optional.of(new UserDetail(
                                rs.getString("family_name"),
                                rs.getString("given_name"),
                                rs.getString("email_address"),
                                rs.getString("user_group")
                        ))
        );
    };
}
