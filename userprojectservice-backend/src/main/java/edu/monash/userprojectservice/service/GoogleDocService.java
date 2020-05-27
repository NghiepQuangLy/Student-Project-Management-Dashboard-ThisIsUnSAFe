package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.InsertGoogleDocRequest;
import edu.monash.userprojectservice.repository.GoogleDoc;
import edu.monash.userprojectservice.repository.GoogleDocRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class GoogleDocService {

    @Autowired
    private GoogleDocRepository googleDocRepository;

    // Get from GoogleDoc table
    public void getgoogleDoc(int projectId, int googleDocId) {
        log.info("{\"message\":\"Getting GoogleDoc data\", \"project\":\"{}\"}, \"googleDoc\":\"{}\"}", projectId, googleDocId);

        // get from database
        Optional<GoogleDoc> googleDocDetail = googleDocRepository.findbyProject(projectId, googleDocId);

        GoogleDoc googleDocResponse = googleDocDetail.orElse(null);
        if (googleDocResponse != null){
            System.out.println(googleDocResponse.getProjectId());
            System.out.println(googleDocResponse.getGoogleDocId());
        }

        log.info("{\"message\":\"Got GoogleDoc data\", \"project\":\"{}\"}, \"googleDoc\":\"{}\"}", projectId, googleDocId);
    }

    // Insert into GoogleDoc table
    public void insertGoogleDoc(InsertGoogleDocRequest insertGoogleDocRequest) {
        log.info("{\"message\":\"Insert GoogleDoc data\", \"project\":\"{}\"}", insertGoogleDocRequest);

        // Store into database
        Boolean googleDocResponse = googleDocRepository.storeProject(insertGoogleDocRequest);

        if (googleDocResponse == null){
            log.info("{\"message\":\"Failed to insert into GoogleDoc\", \"project\":\"{}\"}", insertGoogleDocRequest);
        }

        log.info("{\"message\":\"Inserted into GoogleDoc\", \"project\":\"{}\"}", insertGoogleDocRequest);
    }
}
