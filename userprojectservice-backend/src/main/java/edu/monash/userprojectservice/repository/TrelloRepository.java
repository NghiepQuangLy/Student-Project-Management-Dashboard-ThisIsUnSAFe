package edu.monash.userprojectservice.repository;

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
    public List<Trello> findbyProject(int projectId){
        log.info("{\"message\":\"Querying Trello table\", \"project\":\"{}\"}", projectId);

        return jdbcTemplate.query(
                "SELECT * FROM Trello WHERE project_id = ?",
                new Object[]{projectId},
                (ResultSet rs, int rowNum) -> new Trello(
                                rs.getInt("trello_id"),
                                rs.getInt("project_id")
                        )
        );
    };

    // Insert data into Trello table
    public boolean storeProject(int projectId, int trelloId) {
        log.info("{\"message\":\"Storing into Trello table\", \"project\":\"{}\"}", projectId);

        jdbcTemplate.update(
                "INSERT INTO Trello (project_id, trello_id) VALUES (?,?)", projectId, trelloId
        );
        return true;
    }
}
