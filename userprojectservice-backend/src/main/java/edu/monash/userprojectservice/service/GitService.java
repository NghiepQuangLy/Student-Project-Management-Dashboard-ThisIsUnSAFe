package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.InsertGitRequest;
import edu.monash.userprojectservice.repository.git.GitEntity;
import edu.monash.userprojectservice.repository.git.GitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GitService {

    @Autowired
    private GitRepository gitRepository;

    // Get from Git table
    public void getGit(String projectId) {
        log.info("{\"message\":\"Getting git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId);

        // get from database
        List<GitEntity> gitEntities = gitRepository.findGitEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId);
    }

    // Insert into Git table
    public void insertGit(InsertGitRequest insertGitRequest) {
        log.info("{\"message\":\"Insert Git data\", \"project\":\"{}\"}", insertGitRequest);

        // Store into database
        gitRepository.save(new GitEntity(insertGitRequest.getGitId(), insertGitRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into Git\", \"project\":\"{}\"}", insertGitRequest);
    }
}
