package edu.monash.userprojectservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.sql.ResultSet;

@Slf4j
@Repository
public class GitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Gets data from Git table
    public List<Git> findbyProject(int projectId){
        log.info("{\"message\":\"Querying Git table\", \"project\":\"{}\"}", projectId);

        return jdbcTemplate.query(
                "SELECT * FROM Git WHERE project_id = ?",
                new Object[]{projectId},
                (ResultSet rs, int rowNum) -> new Git(
                                rs.getInt("git_id"),
                                rs.getInt("project_id")
                        )
        );
    };

    // Insert data into Git table
    public boolean storeProject(int projectId, int gitId) {
        log.info("{\"message\":\"Storing into Git table\", \"project\":\"{}\"}", projectId);

        jdbcTemplate.update(
                "INSERT INTO Git (project_id, git_id) VALUES (?,?)", projectId, gitId
        );
        return true;
    }
}
