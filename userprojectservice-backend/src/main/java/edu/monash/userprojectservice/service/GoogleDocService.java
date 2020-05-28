package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.InsertGoogleDocRequest;
import edu.monash.userprojectservice.repository.GoogleDoc;
import edu.monash.userprojectservice.repository.GoogleDocRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GoogleDocService {

    @Autowired
    private GoogleDocRepository googleDocRepository;

    // Get from GoogleDoc table
    public void getgoogleDoc(String projectId) {
        log.info("{\"message\":\"Getting GoogleDoc data\", \"project\":\"{}\"}, \"googleDoc\":\"{}\"}", projectId);

        // get from database
        List<GoogleDoc> googleDocDetail = googleDocRepository.findbyProject(projectId);

//        GoogleDoc googleDocResponse = googleDocDetail.orElse(null);
//        if (googleDocResponse != null){
//            System.out.println(googleDocResponse.getProjectId());
//            System.out.println(googleDocResponse.getGoogleDocId());
//        }

        log.info("{\"message\":\"Got GoogleDoc data\", \"project\":\"{}\"}, \"googleDoc\":\"{}\"}", projectId);
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
