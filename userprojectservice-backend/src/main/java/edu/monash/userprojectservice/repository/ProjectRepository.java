package edu.monash.userprojectservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Project> findbyProject(int projectID){
        return jdbcTemplate.queryForObject(
                "select * from Projects where project_id = ?",
                new Object[]{projectID},
                (rs, rowNum) ->
                        Optional.of(new Project(
                                rs.getInt("project_id"),
                                rs.getString("project_name")
                        ))
        );
    };

    
}
