package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.InsertTrelloRequest;
import edu.monash.userprojectservice.repository.TrelloEntity;
import edu.monash.userprojectservice.repository.TrelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrelloService {

    @Autowired
    private TrelloRepository trelloRepository;

    // Get from Trello table
    public void getTrello(String projectId) {
        log.info("{\"message\":\"Getting Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);

        // get from database
        List<TrelloEntity> trelloEntities = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got Trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);
    }

    // Insert into Trello table
    public void saveTrello(InsertTrelloRequest insertTrelloRequest) {
        log.info("{\"message\":\"Inserting Trello data\", \"project\":\"{}\"}", insertTrelloRequest);

        // Store into database
        trelloRepository.save(new TrelloEntity(insertTrelloRequest.getTrelloId(), insertTrelloRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into Trello\", \"project\":\"{}\"}", insertTrelloRequest);
    }
}
