package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.InsertGitRequest;
import edu.monash.userprojectservice.model.InsertGoogleDocRequest;
import edu.monash.userprojectservice.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GitController {

    private GitService gitService;

    @ResponseStatus(OK)
    @GetMapping("/get-git")
    public void getGit(@RequestParam("projectId") int projectId, @RequestParam("gitId") int gitId) {
        gitService.getGit(projectId, gitId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/insert-git")
    public void insertGit(@RequestBody @Valid InsertGitRequest insertGitRequest) {
        gitService.insertGit(insertGitRequest);
    }
}
