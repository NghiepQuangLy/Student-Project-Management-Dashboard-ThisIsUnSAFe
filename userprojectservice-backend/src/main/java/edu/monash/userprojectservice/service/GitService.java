package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetIntegrationsResponse;
import edu.monash.userprojectservice.model.IntegrationObjectResponse;
import edu.monash.userprojectservice.model.RemoveGitRequest;
import edu.monash.userprojectservice.model.SaveGitRequest;
import edu.monash.userprojectservice.repository.git.GitEntity;
import edu.monash.userprojectservice.repository.git.GitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GitService {

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private ValidationHandler validationHandler;

    /*
     * This method is to get list of git data
     * @param emailAddress The email address to be validated
     * @param projectId The project that contains the git ids
     * @return GetIntegrationsResponse This returns list of git ids
     * @exception BadRequestException when email is empty or not monash email, when project id is empty,
     * @exception ForbiddenException when project does not belong to the email
     */
    public GetIntegrationsResponse getGit(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting git data\", \"project\":\"{}\"}}", projectId);

        // Validation Check
        validationHandler.isUserOwnProject(emailAddress, projectId);

        // Get list of git entities from database by project id
        List<GitEntity> gitEntities = gitRepository.findGitEntitiesByProjectId(projectId);

        // Convert to response
        List<IntegrationObjectResponse> gitIntegrations = gitEntities.stream().map(gitEntity ->
                IntegrationObjectResponse
                        .builder()
                        .integrationId(gitEntity.getGitId())
                        .integrationName(gitEntity.getGitName())
                        .build()
        ).collect(Collectors.toList());

        log.info("{\"message\":\"Got git data\", \"project\":\"{}\"}, \"git\":\"{}\"}", projectId, gitIntegrations);
        return new GetIntegrationsResponse(gitIntegrations);
    }

    /*
     * This method is to store git data to a project
     * @param saveGitRequest git data, project id and email address
     * @exception BadRequestException when email is empty or not monash email, when project id is empty
     * @exception ForbiddenException when project does not belong to the email
     */
    public void insertGit(SaveGitRequest saveGitRequest) {
        log.info("{\"message\":\"Inserting Git data\", \"project\":\"{}\"}", saveGitRequest);

        // Validation Check
        validationHandler.isUserOwnProject(saveGitRequest.getEmailAddress(), saveGitRequest.getProjectId());

        // Store into database
        gitRepository.save(new GitEntity(saveGitRequest.getGitId(), saveGitRequest.getProjectId(), saveGitRequest.getGitName()));

        log.info("{\"message\":\"Inserted into Git\", \"project\":\"{}\"}", saveGitRequest);
    }

    /*
     * This method is to remove git data from a project
     * @param removeGitRequest git id, project id and email address
     * @exception BadRequestException when email is empty or not monash email, when project id is empty
     * @exception NotFoundException when git id is not found in the project data
     * @exception ForbiddenException when project does not belong to the email
     */
    public void removeGit(RemoveGitRequest removeGitRequest) {
        log.info("{\"message\":\"Removing Git data\", \"project\":\"{}\"}", removeGitRequest);

        // Validation Check
        validationHandler.isUserOwnProject(removeGitRequest.getEmailAddress(), removeGitRequest.getProjectId());

        // Get list of git integrations for the project
        List<GitEntity> gitEntities = gitRepository.findGitEntitiesByProjectId(removeGitRequest.getProjectId());

        GitEntity gitEntity = gitEntities.stream()
                .filter(g -> g.getGitId().equals(removeGitRequest.getGitId()))
                .findFirst()
                .orElse(null);

        if (gitEntity == null) {
            log.warn("A git integration [{}] does not exist: ", removeGitRequest.getGitId());
            throw new HTTPResponseHandler.NotFoundException("Git id not found.");
        } else {
            // Delete git data from database
            gitRepository.delete(gitEntity);
            log.info("{\"message\":\"Removed git [{}] from project [{}]\"}", removeGitRequest.getGitId(), removeGitRequest.getProjectId());
        }
    }
}
