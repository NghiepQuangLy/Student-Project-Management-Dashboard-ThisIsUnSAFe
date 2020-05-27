package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.repository.Trello;
import edu.monash.userprojectservice.repository.TrelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TrelloService {

    @Autowired
    private TrelloRepository trelloRepository;

    // Get from Trello table
    public void getTrello(int projectId) {
        log.info("{\"message\":\"Getting Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);

        // get from database
        List<Trello> trelloDetail = trelloRepository.findbyProject(projectId);

//        Trello trelloResponse = trelloDetail.orElse(null);
//        if (trelloResponse != null){
//            System.out.println(trelloResponse.getProjectId());
//            System.out.println(trelloResponse.getTrelloId());
//        }

        log.info("{\"message\":\"Got Trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);
    }
    
    // Insert into Trello table
    public void insertTrello(int projectId, int trelloId) {
        log.info("{\"message\":\"Insert Trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId, trelloId);

        // Store into database
        Boolean trelloResponse = trelloRepository.storeProject(projectId, trelloId);

        if (trelloResponse != null){
            log.info("{\"message\":\"Failed to insert into Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId, trelloId);
        }

        log.info("{\"message\":\"Inserted into Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId, trelloId);
    }
}
