package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.model.InsertGoogleDocRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class GoogleDocRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Gets data from GoogleDoc table
    public List<GoogleDoc> findbyProject(String projectId){
        log.info("{\"message\":\"Querying GoogleDoc table\", \"project\":\"{}\"}", projectId);

        return jdbcTemplate.query(
                "SELECT * FROM GoogleDoc WHERE project_id = ?",
                new Object[]{projectId},
                (ResultSet rs, int rowNum) -> new GoogleDoc(
                                rs.getInt("document_id"),
                                rs.getString("project_id")
                        )
        );
    };

    // Insert data into GoogleDoc table
    public boolean storeProject(InsertGoogleDocRequest request) {
        log.info("{\"message\":\"Storing into GoogleDoc table\", \"project\":\"{}\"}", request.getProjectId());

        jdbcTemplate.update(
                "INSERT INTO GoogleDoc (project_id, document_id) VALUES (?,?)",
                request.getProjectId(), request.getGoogleDocId()
        );
        return true;
    }
}
