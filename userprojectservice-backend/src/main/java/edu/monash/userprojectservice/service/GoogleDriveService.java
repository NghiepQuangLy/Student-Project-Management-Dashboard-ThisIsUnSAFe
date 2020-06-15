package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.SaveGoogleDriveRequest;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoogleDriveService {

    @Autowired
    private GoogleDriveRepository googleDriveRepository;

    // Get from GoogleDrive table
    public void getgoogleDrive(String projectId) {
        log.info("{\"message\":\"Getting GoogleDrive data\", \"project\":\"{}\"}, \"googleDrive\":\"{}\"}", projectId);

        // get from database
        List<GoogleDriveEntity> googleDriveEntities = googleDriveRepository.findGoogleDriveEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got GoogleDrive data\", \"project\":\"{}\"}, \"googleDrive\":\"{}\"}", projectId);
    }

    // Insert into GoogleDrive table
    public void saveGoogleDrive(SaveGoogleDriveRequest saveGoogleDriveRequest) {
        log.info("{\"message\":\"Insert GoogleDrive data\", \"project\":\"{}\"}", saveGoogleDriveRequest);

        // Store into database
        googleDriveRepository.save(new GoogleDriveEntity(saveGoogleDriveRequest.getGoogleDriveId(), saveGoogleDriveRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into GoogleDrive\", \"project\":\"{}\"}", saveGoogleDriveRequest);
    }
}