package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetIntegrationsResponse;
import edu.monash.userprojectservice.model.IntegrationObjectResponse;
import edu.monash.userprojectservice.model.RemoveTrelloRequest;
import edu.monash.userprojectservice.model.SaveTrelloRequest;
import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import edu.monash.userprojectservice.repository.trello.TrelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrelloService {

    @Autowired
    private TrelloRepository trelloRepository;

    @Autowired
    private ValidationHandler validationHandler;

    /*
     * This method is to get list of trello data
     * @param emailAddress The email address to be validated
     * @param projectId The project that contains the trello ids
     * @return GetIntegrationsResponse This returns list of trello ids
     * @exception BadRequestException when email is empty or not monash email, when project id is empty,
     * @exception ForbiddenException when project does not belong to the email
     */
    public GetIntegrationsResponse getTrello(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting trello data\", \"project\":\"{}\"}}", projectId);

        // Validation Check
        validationHandler.isUserOwnProject(emailAddress, projectId);

        // get from database
        List<TrelloEntity> trelloEntities = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        // Convert to response
        List<IntegrationObjectResponse> trelloIntegrations = trelloEntities.stream().map(trelloEntity ->
                IntegrationObjectResponse
                        .builder()
                        .integrationId(trelloEntity.getTrelloId())
                        .integrationName(trelloEntity.getTrelloName())
                        .build()
        ).collect(Collectors.toList());

        log.info("{\"message\":\"Got trello data\", \"project\":\"{}\"}, \"trello\":\"{}\"}", projectId, trelloIntegrations);
        return new GetIntegrationsResponse(trelloIntegrations);
    }

    /*
     * This method is to store trello data to a project
     * @param saveTrelloRequest trello data, project id and email address
     * @exception BadRequestException when email is empty or not monash email, when project id is empty
     * @exception ForbiddenException when project does not belong to the email
     */
    public void saveTrello(SaveTrelloRequest saveTrelloRequest) {
        log.info("{\"message\":\"Inserting Trello data\", \"project\":\"{}\"}", saveTrelloRequest);

        // Validation Check
        validationHandler.isUserOwnProject(saveTrelloRequest.getEmailAddress(), saveTrelloRequest.getProjectId());

        // Store into database
        trelloRepository.save(new TrelloEntity(saveTrelloRequest.getTrelloId(), saveTrelloRequest.getProjectId(), saveTrelloRequest.getTrelloName()));

        log.info("{\"message\":\"Inserted into Trello\", \"project\":\"{}\"}", saveTrelloRequest);
    }

    /*
     * This method is to remove trello data from a project
     * @param removeTrelloRequest trello id, project id and email address
     * @exception BadRequestException when email is empty or not monash email, when project id is empty
     * @exception NotFoundException when trello id is not found in the project data
     * @exception ForbiddenException when project does not belong to the email
     */
    public void removeTrello(RemoveTrelloRequest removeTrelloRequest) {
        log.info("{\"message\":\"Removing Trello data\", \"project\":\"{}\"}", removeTrelloRequest);

        // Validation Check
        validationHandler.isUserOwnProject(removeTrelloRequest.getEmailAddress(), removeTrelloRequest.getProjectId());

        // Get list of trello integrations for the project
        List<TrelloEntity> trelloEntities = trelloRepository.findTrelloEntitiesByProjectId(removeTrelloRequest.getProjectId());

        TrelloEntity trelloEntity = trelloEntities.stream()
                .filter(t -> t.getTrelloId().equals(removeTrelloRequest.getTrelloId()))
                .findFirst()
                .orElse(null);

        if (trelloEntity == null) {
            log.warn("A trello integration [{}] does not exist: ", removeTrelloRequest.getTrelloId());
            throw new HTTPResponseHandler.NotFoundException("Trello id not found.");
        } else {
            // Delete trello data from database
            trelloRepository.delete(trelloEntity);
            log.info("{\"message\":\"Removed trello [{}] from project [{}]\"}", removeTrelloRequest.getTrelloId(), removeTrelloRequest.getProjectId());
        }
    }
}
