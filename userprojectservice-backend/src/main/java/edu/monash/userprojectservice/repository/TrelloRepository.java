package edu.monash.userprojectservice.repository;

import edu.monash.userprojectservice.model.InsertTrelloRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.sql.ResultSet;
import java.util.List;

@Slf4j
@Repository
public class TrelloRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Gets data from Trello table
    public List<Trello> findbyProject(String projectId){
        log.info("{\"message\":\"Querying Trello table\", \"project\":\"{}\"}", projectId);

        return jdbcTemplate.query(
                "SELECT * FROM Trello WHERE project_id = ?",
                new Object[]{projectId},
                (ResultSet rs, int rowNum) -> new Trello(
                                rs.getString("trello_id"),
                                rs.getString("project_id")
                        )
        );
    };

    // Insert data into Trello table
    public boolean storeProject(InsertTrelloRequest request) {
        log.info("{\"message\":\"Storing into Trello table\", \"project\":\"{}\"}", request.getProjectId());

        jdbcTemplate.update(
                "INSERT INTO Trello (project_id, trello_id) VALUES (?,?)",
                request.getProjectId(), request.getTrelloId()
        );
        return true;
    }
}
