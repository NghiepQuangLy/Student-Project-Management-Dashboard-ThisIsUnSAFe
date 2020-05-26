package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.service.*;
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
public class ProjectController {

    private ProjectService projectService;
    private GitService gitService;
    private GoogleDocService googleDocService;
    private TrelloService trelloService;

    @ResponseStatus(OK)
    @GetMapping("/get-project")
    public void getProject(@RequestParam("projectId") int projectId) {
        projectService.getProject(projectId);
    }

    @ResponseStatus(OK)
        @GetMapping("/get-git")
    public void getGit(@RequestParam("projectId") int projectId, @RequestParam("gitId") int gitId) {
        gitService.getGit(projectId, gitId);
    }

    @ResponseStatus(OK)
        @GetMapping("/get-googledoc")
    public void getGoogleDoc(@RequestParam("projectId") int projectId, @RequestParam("googleDocId") int googleDocId) {
        googleDocService.getgoogleDoc(projectId, googleDocId);
    }

    @ResponseStatus(OK)
    @GetMapping("/get-trello")
    public void getTrello(@RequestParam("projectId") int projectId, @RequestParam("trelloId") int trelloId) {
        trelloService.getTrello(projectId, trelloId);
    }

    @ResponseStatus(CREATED)
    @GetMapping("/insert-git")
    public void insertGit(@RequestParam("projectId") int projectId, @RequestParam("gitId") int gitId) {
        gitService.insertGit(projectId, gitId);
    }

    @ResponseStatus(CREATED)
    @GetMapping("/insert-googledoc")
    public void insertGoogleDoc(@RequestParam("projectId") int projectId, @RequestParam("googleDocId") int googleDocId) {
        googleDocService.insertGoogleDoc(projectId, googleDocId);
    }

    @ResponseStatus(CREATED)
    @GetMapping("/insert-trello")
    public void insertTrello(@RequestParam("projectId") int projectId, @RequestParam("trelloId") int trelloId) {
        trelloService.insertTrello(projectId, trelloId);
    }
}
