package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetIntegrationsResponse;
import edu.monash.userprojectservice.model.IntegrationObjectResponse;
import edu.monash.userprojectservice.model.RemoveGoogleDriveRequest;
import edu.monash.userprojectservice.model.SaveGoogleDriveRequest;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleDriveService {

    @Autowired
    private GoogleDriveRepository googleDriveRepository;

    @Autowired
    private ValidationHandler validationHandler;

    /*
     * This method is to get list of GoogleDrive data
     * @param emailAddress The email address to be validated
     * @param projectId The project that contains the google drive ids
     * @return GetIntegrationsResponse This returns list of google drive ids
     * @exception BadRequestException when email is empty or not monash email, when project id is empty,
     * @exception ForbiddenException when project does not belong to the email
     */
    public GetIntegrationsResponse getgoogleDrive(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting google drive data\", \"project\":\"{}\"}}", projectId);

        // Validation Check
        validationHandler.isUserOwnProject(emailAddress, projectId);

        // get from database
        List<GoogleDriveEntity> googleDriveEntities = googleDriveRepository.findGoogleDriveEntitiesByProjectId(projectId);

        // Convert to response
        List<IntegrationObjectResponse> googleDriveIntegrations = googleDriveEntities.stream().map(googleDriveEntity ->
                IntegrationObjectResponse
                        .builder()
                        .integrationId(googleDriveEntity.getGoogleDriveId())
                        .integrationName(googleDriveEntity.getGoogleDriveName())
                        .build()
        ).collect(Collectors.toList());

        log.info("{\"message\":\"Got google drive data\", \"project\":\"{}\"}, \"google drive\":\"{}\"}", projectId, googleDriveIntegrations);
        return new GetIntegrationsResponse(googleDriveIntegrations);
    }

    /*
     * This method is to store google drive data to a project
     * @param SaveGoogleDriveRequest google drive data, project id and email address
     * @exception BadRequestException when email is empty or not monash email, when project id is empty
     * @exception ForbiddenException when project does not belong to the email
     */
    public void saveGoogleDrive(SaveGoogleDriveRequest saveGoogleDriveRequest) {
        log.info("{\"message\":\"Inserting GoogleDrive data\", \"project\":\"{}\"}", saveGoogleDriveRequest);

        // Validation Check
        validationHandler.isUserOwnProject(saveGoogleDriveRequest.getEmailAddress(), saveGoogleDriveRequest.getProjectId());

        // Store into database
        googleDriveRepository.save(new GoogleDriveEntity(saveGoogleDriveRequest.getGoogleDriveId(), saveGoogleDriveRequest.getProjectId(), saveGoogleDriveRequest.getGoogleDriveName()));

        log.info("{\"message\":\"Inserted into GoogleDrive\", \"project\":\"{}\"}", saveGoogleDriveRequest);
    }

    // Remove from GoogleDrive table
    /*
     * This method is to remove google drive data from a project
     * @param removeGoogleDriveRequest google drive id, project id and email address
     * @exception BadRequestException when email is empty or not monash email, when project id is empty
     * @exception NotFoundException when google drive id is not found in the project data
     * @exception ForbiddenException when project does not belong to the email
     */
    public void removeGoogleDrive(RemoveGoogleDriveRequest removeGoogleDriveRequest) {
        log.info("{\"message\":\"Removing GoogleDrive data\", \"project\":\"{}\"}", removeGoogleDriveRequest);

        // Validation Check
        validationHandler.isUserOwnProject(removeGoogleDriveRequest.getEmailAddress(), removeGoogleDriveRequest.getProjectId());

        // Get list of google drive integrations for the project
        List<GoogleDriveEntity> googleDriveEntities = googleDriveRepository.findGoogleDriveEntitiesByProjectId(removeGoogleDriveRequest.getProjectId());

        GoogleDriveEntity googleDriveEntity = googleDriveEntities.stream()
                .filter(g -> g.getGoogleDriveId().equals(removeGoogleDriveRequest.getGoogleDriveId()))
                .findFirst()
                .orElse(null);

        if (googleDriveEntity == null) {
            log.warn("A google drive integration [{}] does not exist: ", removeGoogleDriveRequest.getGoogleDriveId());
            throw new HTTPResponseHandler.NotFoundException("Google drive id not found.");
        } else {
            // Delete google drive data from database
            googleDriveRepository.delete(googleDriveEntity);
            log.info("{\"message\":\"Removed google drive [{}] from project [{}]\"}", removeGoogleDriveRequest.getGoogleDriveId(), removeGoogleDriveRequest.getProjectId());
        }
    }
}
