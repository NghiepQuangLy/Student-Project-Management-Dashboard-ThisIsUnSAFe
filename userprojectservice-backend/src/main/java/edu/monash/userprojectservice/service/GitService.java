package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.repository.Git;
import edu.monash.userprojectservice.repository.GitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GitService {

    @Autowired
    private GitRepository gitRepository;

    // Get from Git table
    public void getGit(int projectId) {
        log.info("{\"message\":\"Getting git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId);

        // get from database
        List<Git> gitDetail = gitRepository.findbyProject(projectId);

//        Git gitResponse = gitDetail.orElse(null);
//        if (gitResponse != null){
//            System.out.println(gitResponse.getProjectId());
//            System.out.println(gitResponse.getGitId());
//        }

        log.info("{\"message\":\"Got git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId);
    }

    // Insert into Git table
    public void insertGit(int projectId, int gitId) {
        log.info("{\"message\":\"Insert Git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId, gitId);

        // Store into database
        Boolean gitResponse = gitRepository.storeProject(projectId, gitId);

        if (gitResponse != null){
            log.info("{\"message\":\"Failed to insert into Git \", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId, gitId);
        }

        log.info("{\"message\":\"Inserted into Git \", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId, gitId);
    }
}
