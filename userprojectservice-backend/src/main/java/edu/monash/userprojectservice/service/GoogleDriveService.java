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
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

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

    // Remove from GoogleDrive table
    public ResponseEntity<GetGoogleDriveResponse> removeGoogleDrive(RemoveGoogleDriveRequest removeGoogleDriveRequest) throws SQLException {
        log.info("{\"message\":\"Remove GoogleDrive data\", \"project\":\"{}\"}", removeGoogleDriveRequest);

        // Validation Check
        validationHandler.isValid(removeGoogleDriveRequest.getEmailAddress(), removeGoogleDriveRequest.getProjectId());

        // Get list of google drive integrations for the project
        List<GoogleDriveEntity> googleDriveEntity = googleDriveRepository.findGoogleDriveEntitiesByProjectId(removeGoogleDriveRequest.getProjectId());

        if (googleDriveEntity.size() > 0) {
            for (int i = 0; i < googleDriveEntity.size(); i++) {
                if (removeGoogleDriveRequest.getGoogleDriveId().equals(googleDriveEntity.get(i).getGoogleDriveId())) {
                    // Delete from database
                    googleDriveRepository.delete(googleDriveEntity.get(i));
                    log.info("{\"message\":\"Removed from GoogleDrive\", \"project\":\"{}\"}", removeGoogleDriveRequest.getGoogleDriveId());
                    return new ResponseEntity<>(
                            null, OK
                    );
                }
            }
            log.warn("A GoogleDrive integration with the googleDriveId does not exist: ", removeGoogleDriveRequest.getGoogleDriveId());
            return new ResponseEntity<>(
                    null, BAD_REQUEST
            );
        }
        else {
            log.warn("Project has no googleDrive integrations: ", removeGoogleDriveRequest.getGoogleDriveId());
            return new ResponseEntity<>(
                    null, BAD_REQUEST
            );
        }
    }
}
