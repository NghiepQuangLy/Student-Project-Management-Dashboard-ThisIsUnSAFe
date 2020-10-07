package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetTrelloResponse;
import edu.monash.userprojectservice.model.SaveTrelloRequest;
import edu.monash.userprojectservice.model.RemoveTrelloRequest;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
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

    @Autowired
    private ValidationHandler validationHandler;

    // Get from Trello table
    public GetTrelloResponse getTrello(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting Trello \", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);

        // get from database
        List<TrelloEntity> trelloEntities = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got Trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId);
        return new GetTrelloResponse(trelloEntities);
    }

    // Insert into Trello table
    public void saveTrello(SaveTrelloRequest saveTrelloRequest) {
        log.info("{\"message\":\"Inserting Trello data\", \"project\":\"{}\"}", saveTrelloRequest);

        // Validation Check
        validationHandler.isValid(saveTrelloRequest.getEmailAddress(), saveTrelloRequest.getProjectId());

        // Store into database
        trelloRepository.save(new TrelloEntity(saveTrelloRequest.getTrelloId(), saveTrelloRequest.getProjectId(), saveTrelloRequest.getTrelloName()));

        log.info("{\"message\":\"Inserted into Trello\", \"project\":\"{}\"}", saveTrelloRequest);
    }

    // Delete from Trello table
    public void removeTrello(RemoveTrelloRequest removeTrelloRequest) {
        log.info("{\"message\":\"Removing Trello data\", \"project\":\"{}\"}", removeTrelloRequest);

        // Validation Check
        validationHandler.isValid(removeTrelloRequest.getEmailAddress(), removeTrelloRequest.getProjectId());

        // Delete from database
        trelloRepository.delete(new TrelloEntity(removeTrelloRequest.getTrelloId(), removeTrelloRequest.getProjectId(), removeTrelloRequest.getTrelloName()));

        log.info("{\"message\":\"Removed from Trello\", \"project\":\"{}\"}", removeTrelloRequest);
    }
}
