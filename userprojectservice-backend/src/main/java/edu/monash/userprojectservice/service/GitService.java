package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.SaveGitRequest;
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
    public void insertGit(SaveGitRequest saveGitRequest) {
        log.info("{\"message\":\"Insert Git data\", \"project\":\"{}\"}", saveGitRequest);

        // Store into database
        gitRepository.save(new GitEntity(saveGitRequest.getGitId(), saveGitRequest.getProjectId()));

        log.info("{\"message\":\"Inserted into Git\", \"project\":\"{}\"}", saveGitRequest);
    }
}
