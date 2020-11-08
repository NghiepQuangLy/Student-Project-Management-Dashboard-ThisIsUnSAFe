package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetGitResponse;
import edu.monash.userprojectservice.model.SaveGitRequest;
import edu.monash.userprojectservice.model.RemoveGitRequest;
import edu.monash.userprojectservice.repository.git.GitEntity;
import edu.monash.userprojectservice.repository.git.GitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
public class GitService {

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private ValidationHandler validationHandler;

    // Get from Git table
    public GetGitResponse getGit(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting git data\", \"project\":\"{}\"}}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);

        // get from database
        List<GitEntity> gitEntities = gitRepository.findGitEntitiesByProjectId(projectId);

        log.info("{\"message\":\"Got git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId, gitEntities);
        return new GetGitResponse(gitEntities);
    }

    // Insert into Git table
    public void insertGit(SaveGitRequest saveGitRequest) {
        log.info("{\"message\":\"Insert Git data\", \"project\":\"{}\"}", saveGitRequest);

        // Validation Check
        validationHandler.isValid(saveGitRequest.getEmailAddress(), saveGitRequest.getProjectId());

        // Store into database
        gitRepository.save(new GitEntity(saveGitRequest.getGitId(), saveGitRequest.getProjectId(), saveGitRequest.getGitName()));

        log.info("{\"message\":\"Inserted into Git\", \"project\":\"{}\"}", saveGitRequest);
    }

    // Remove from Git table
    public ResponseEntity<GetGitResponse> removeGit(RemoveGitRequest removeGitRequest) throws SQLException {
        log.info("{\"message\":\"Remove Git data\", \"project\":\"{}\"}", removeGitRequest);

        // Validation Check
        validationHandler.isValid(removeGitRequest.getEmailAddress(), removeGitRequest.getProjectId());

        // Get list of git integrations for the project
        List<GitEntity> gitEntity = gitRepository.findGitEntitiesByProjectId(removeGitRequest.getProjectId());

        if (gitEntity.size() > 0) {
            for (int i = 0; i < gitEntity.size(); i++) {
                if (removeGitRequest.getGitId().equals(gitEntity.get(i).getGitId())) {
                    // Delete from database
                    gitRepository.delete(gitEntity.get(i));
                    log.info("{\"message\":\"Removed from Git\", \"project\":\"{}\"}", removeGitRequest.getGitId());
                    return new ResponseEntity<>(
                            null, OK
                    );
                }
            }
            log.warn("A Git integration with the gitId does not exist: ", removeGitRequest.getGitId());
            return new ResponseEntity<>(
                    null, NOT_FOUND
            );
        }
        else {
            log.warn("Project has no git integrations: ", removeGitRequest.getGitId());
            return new ResponseEntity<>(
                    null, BAD_REQUEST
            );
        }
    }
}
