package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.SaveTrelloRequest;
import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import edu.monash.userprojectservice.repository.trello.TrelloRepository;
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
    public void getTrello(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);

        // Validation Check
        ValidationHandler validationHandler = new ValidationHandler();
        validationHandler.isValid(emailAddress, projectId);

        // get from database
        List<TrelloEntity> trelloEntities = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got Trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);
    }

    // Insert into Trello table
    public void saveTrello(SaveTrelloRequest saveTrelloRequest) {
        log.info("{\"message\":\"Inserting Trello data\", \"project\":\"{}\"}", saveTrelloRequest);

        // Validation Check
        ValidationHandler validationHandler = new ValidationHandler();
        validationHandler.isValid(saveTrelloRequest.getEmailAddress(), saveTrelloRequest.getProjectId());

        // Store into database
        trelloRepository.save(new TrelloEntity(saveTrelloRequest.getTrelloId(), saveTrelloRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into Trello\", \"project\":\"{}\"}", saveTrelloRequest);
    }
}
