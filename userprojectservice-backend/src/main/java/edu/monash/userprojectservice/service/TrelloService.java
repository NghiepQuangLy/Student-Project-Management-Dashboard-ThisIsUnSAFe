package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.InsertTrelloRequest;
import edu.monash.userprojectservice.repository.Trello;
import edu.monash.userprojectservice.repository.TrelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TrelloService {

    @Autowired
    private TrelloRepository trelloRepository;

    // Get from Trello table
    public void getTrello(int projectId, int trelloId) {
        log.info("{\"message\":\"Getting Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId, trelloId);

        // get from database
        Optional<Trello> trelloDetail = trelloRepository.findbyProject(projectId, trelloId);

        Trello trelloResponse = trelloDetail.orElse(null);
        if (trelloResponse != null){
            System.out.println(trelloResponse.getProjectId());
            System.out.println(trelloResponse.getTrelloId());
        }

        log.info("{\"message\":\"Got Trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId, trelloId);
    }
    
    // Insert into Trello table
    public void insertTrello(InsertTrelloRequest insertTrelloRequest) {
        log.info("{\"message\":\"Insert Trello data\", \"project\":\"{}\"}", insertTrelloRequest);

        // Store into database
        Boolean trelloResponse = trelloRepository.storeProject(insertTrelloRequest);

        if (trelloResponse == null){
            log.info("{\"message\":\"Failed to insert into Trello\", \"project\":\"{}\"}", insertTrelloRequest);
        }

        log.info("{\"message\":\"Inserted into Trello\", \"project\":\"{}\"}", insertTrelloRequest);
    }
}
