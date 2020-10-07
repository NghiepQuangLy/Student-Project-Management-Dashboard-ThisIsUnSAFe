package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetGitResponse;
import edu.monash.userprojectservice.model.GetGoogleDriveResponse;
import edu.monash.userprojectservice.model.SaveGoogleDriveRequest;
import edu.monash.userprojectservice.model.RemoveGoogleDriveRequest;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import java.util.List;

@Slf4j
@Service
public class GoogleDriveService {

    @Autowired
    private GoogleDriveRepository googleDriveRepository;

    @Autowired
    private ValidationHandler validationHandler;

    // Get from GoogleDrive table
    public GetGoogleDriveResponse getgoogleDrive(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting GoogleDrive data\", \"project\":\"{}\"}, \"googleDrive\":\"{}\"}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);

        // get from database
        List<GoogleDriveEntity> googleDriveEntities = googleDriveRepository.findGoogleDriveEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got GoogleDrive data\", \"project\":\"{}\"}, \"googleDrive\":\"{}\"}", projectId);
        return new GetGoogleDriveResponse(googleDriveEntities);
    }

    // Insert into GoogleDrive table
    public void saveGoogleDrive(SaveGoogleDriveRequest saveGoogleDriveRequest) {
        log.info("{\"message\":\"Insert GoogleDrive data\", \"project\":\"{}\"}", saveGoogleDriveRequest);

        // Validation Check
        validationHandler.isValid(saveGoogleDriveRequest.getEmailAddress(), saveGoogleDriveRequest.getProjectId());

        // Store into database
        googleDriveRepository.save(new GoogleDriveEntity(saveGoogleDriveRequest.getGoogleDriveId(), saveGoogleDriveRequest.getProjectId(), saveGoogleDriveRequest.getGoogleDriveName()));

        log.info("{\"message\":\"Inserted into GoogleDrive\", \"project\":\"{}\"}", saveGoogleDriveRequest);
    }

    public void removeGoogleDrive(RemoveGoogleDriveRequest removeGoogleDriveRequest) {
        log.info("{\"message\":\"Remove GoogleDrive data\", \"project\":\"{}\"}", removeGoogleDriveRequest);

        // Validation Check
        validationHandler.isValid(removeGoogleDriveRequest.getEmailAddress(), removeGoogleDriveRequest.getProjectId());

        // Delete from database
        googleDriveRepository.delete(new GoogleDriveEntity(removeGoogleDriveRequest.getGoogleDriveId(), removeGoogleDriveRequest.getProjectId(), removeGoogleDriveRequest.getGoogleDriveName()));

        log.info("{\"message\":\"Removed from GoogleDrive\", \"project\":\"{}\"}", removeGoogleDriveRequest);
    }
}
