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
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    public ResponseEntity<GetTrelloResponse> removeTrello(RemoveTrelloRequest removeTrelloRequest) throws SQLException {
        log.info("{\"message\":\"Removing Trello data\", \"project\":\"{}\"}", removeTrelloRequest);

        // Validation Check
        validationHandler.isValid(removeTrelloRequest.getEmailAddress(), removeTrelloRequest.getProjectId());

        // Get list of trello integrations for the project
        List<TrelloEntity> trelloEntity = trelloRepository.findTrelloEntitiesByProjectId(removeTrelloRequest.getProjectId());

        if (trelloEntity.size() > 0) {
            for (int i = 0; i < trelloEntity.size(); i++) {
                if (removeTrelloRequest.getTrelloId().equals(trelloEntity.get(i).getTrelloId())) {
                    // Delete from database
                    trelloRepository.delete(trelloEntity.get(i));
                    log.info("{\"message\":\"Removed from Trello\", \"project\":\"{}\"}", removeTrelloRequest.getTrelloId());
                    return new ResponseEntity<>(
                            null, OK
                    );
                }
            }
            log.warn("A Trello integration with the trelloId does not exist: ", removeTrelloRequest.getTrelloId());
            return new ResponseEntity<>(
                    null, NOT_FOUND
            );
        }
        else {
            log.warn("Project has no trello integrations: ", removeTrelloRequest.getTrelloId());
            return new ResponseEntity<>(
                    null, BAD_REQUEST
            );
        }
    }
}
