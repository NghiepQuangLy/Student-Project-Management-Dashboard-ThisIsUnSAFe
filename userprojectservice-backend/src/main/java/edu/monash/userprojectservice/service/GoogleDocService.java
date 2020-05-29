package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.SaveGoogleDocRequest;
import edu.monash.userprojectservice.repository.GoogleDocTemRepository;
import edu.monash.userprojectservice.repository.googledoc.GoogleDocEntity;
import edu.monash.userprojectservice.repository.googledoc.GoogleDocRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoogleDocService {

    @Autowired
    private GoogleDocTemRepository googleDocTemRepository;

    @Autowired
    private GoogleDocRepository googleDocRepository;

    // Get from GoogleDoc table
    public void getgoogleDoc(String projectId) {
        log.info("{\"message\":\"Getting GoogleDoc data\", \"project\":\"{}\"}, \"googleDoc\":\"{}\"}", projectId);

        // get from database
        List<GoogleDocEntity> googleDocEntities = googleDocRepository.findGoogleDocEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got GoogleDoc data\", \"project\":\"{}\"}, \"googleDoc\":\"{}\"}", projectId);
    }

    // Insert into GoogleDoc table
    public void saveGoogleDoc(SaveGoogleDocRequest saveGoogleDocRequest) {
        log.info("{\"message\":\"Insert GoogleDoc data\", \"project\":\"{}\"}", saveGoogleDocRequest);

        // Store into database
        googleDocRepository.save(new GoogleDocEntity(saveGoogleDocRequest.getGoogleDocId(), saveGoogleDocRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into GoogleDoc\", \"project\":\"{}\"}", saveGoogleDocRequest);
    }
}
