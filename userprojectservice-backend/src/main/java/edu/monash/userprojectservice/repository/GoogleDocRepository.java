package edu.monash.userprojectservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class GoogleDocRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Gets data from GoogleDoc table
    public Optional<GoogleDoc> findbyProject(int projectId, int googleDocId){
        log.info("{\"message\":\"Querying GoogleDoc table\", \"project\":\"{}\"}", projectId);

        return jdbcTemplate.queryForObject(
                "SELECT * FROM GoogleDoc WHERE project_id = ? AND document_id = ?",
                new Object[]{projectId, googleDocId},
                (rs, rowNum) ->
                        Optional.of(new GoogleDoc(
                                rs.getInt("document_id"),
                                rs.getInt("project_id")
                        ))
        );
    };

    // Insert data into GoogleDoc table
    public boolean storeProject(int projectId, int googleDocId) {
        log.info("{\"message\":\"Storing into GoogleDoc table\", \"project\":\"{}\"}", projectId);

        jdbcTemplate.update(
                "INSERT INTO GoogleDoc (project_id, document_id) VALUES (?,?)", projectId, googleDocId
        );
        return true;
    }
}
