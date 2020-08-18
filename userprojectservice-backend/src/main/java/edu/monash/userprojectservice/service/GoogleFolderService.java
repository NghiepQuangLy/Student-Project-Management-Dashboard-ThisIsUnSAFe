package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.SaveGoogleFolderRequest;
import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderEntity;
import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoogleFolderService {

    @Autowired
    private GoogleFolderRepository googleFolderRepository;

    // Get from GoogleFolder table
    public void getgoogleFolder(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting GoogleFolder data\", \"project\":\"{}\"}, \"googleFolder\":\"{}\"}", projectId);

        // Validation Check
        ValidationHandler validationHandler = new ValidationHandler();
        validationHandler.isValid(emailAddress, projectId);

        // get from database
        List<GoogleFolderEntity> googleFolderEntities = googleFolderRepository.findGoogleFolderEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got GoogleFolder data\", \"project\":\"{}\"}, \"googleFolder\":\"{}\"}", projectId);
    }

    // Insert into GoogleFolder table
    public void saveGoogleFolder(SaveGoogleFolderRequest saveGoogleFolderRequest) {
        log.info("{\"message\":\"Insert GoogleFolder data\", \"project\":\"{}\"}", saveGoogleFolderRequest);

        // Validation Check
        ValidationHandler validationHandler = new ValidationHandler();
        validationHandler.isValid(saveGoogleFolderRequest.getEmailAddress(), saveGoogleFolderRequest.getProjectId());

        // Store into database
        googleFolderRepository.save(new GoogleFolderEntity(saveGoogleFolderRequest.getGoogleFolderId(), saveGoogleFolderRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into GoogleFolder\", \"project\":\"{}\"}", saveGoogleFolderRequest);
    }
}
