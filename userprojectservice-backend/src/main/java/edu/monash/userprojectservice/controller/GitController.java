package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetIntegrationsResponse;
import edu.monash.userprojectservice.model.RemoveGitRequest;
import edu.monash.userprojectservice.model.SaveGitRequest;
import edu.monash.userprojectservice.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GitController {

    private GitService gitService;

    /*
     * This method is to get list of git data
     * @requestParam emailAddress The email address to be validated
     * @requestParam projectId The project that contains the git ids
     * @return 200 GetIntegrationsResponse This returns list of git ids
     * @return 400 when email is empty or not monash email, when project id is empty,
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @GetMapping("/get-git")
    public GetIntegrationsResponse getGit(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return gitService.getGit(emailAddress, projectId);
    }

    /*
     * This method is to store git data to a project
     * @requestBody saveGitRequest git data, project id and email address
     * @return 201 When save git data successfully
     * @return 400 when email is empty or not monash email, when project id is empty
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(CREATED)
    @PostMapping("/save-git")
    public void insertGit(@RequestBody @Valid SaveGitRequest saveGitRequest) {
        gitService.insertGit(saveGitRequest);
    }

    /*
     * This method is to remove git data from a project
     * @requestBody removeGitRequest git id, project id and email address
     * @return 400 when email is empty or not monash email, when project id is empty
     * @return 404 when git id is not found in the project data
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @PostMapping("/remove-git")
    public void removeGit(@RequestBody @Valid RemoveGitRequest removeGitRequest) {
        gitService.removeGit(removeGitRequest);
    }
}
