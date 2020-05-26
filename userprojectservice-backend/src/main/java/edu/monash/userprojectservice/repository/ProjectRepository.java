package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.service.ProjectDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<ProjectDetail> findbyProject(int projectID){
        return jdbcTemplate.queryForObject(
                "select * from Projects where project_id = ?",
                new Object[]{projectID},
                (rs, rowNum) ->
                        Optional.of(new ProjectDetail(
                                rs.getInt("project_id"),
                                rs.getString("project_name")
                        ))
        );
    };
}
