package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.service.UserProjectDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersProjectsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserProjectDetail> findProjectsByEmail(String email_address){


        return jdbcTemplate.query(
                "select * from Users_Projects where email_address = ?",
                new Object[]{email_address},
                //(Result rs, int rowNum) -> new

        );
    };
}
