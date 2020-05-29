package edu.monash.userprojectservice.controller;

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

    @ResponseStatus(OK)
    @GetMapping("/get-git")
    public void getGit(@RequestParam("projectId") String projectId) {
        gitService.getGit(projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-git")
    public void insertGit(@RequestBody @Valid SaveGitRequest saveGitRequest) {
        gitService.insertGit(saveGitRequest);
    }
}
