package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.SaveGitRequest;
import edu.monash.userprojectservice.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:3003"}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GitController {

    private GitService gitService;

    @ResponseStatus(OK)
    @GetMapping("/get-git")
    public void getGit(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        gitService.getGit(emailAddress, projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-git")
    public void insertGit(@RequestBody @Valid SaveGitRequest saveGitRequest) {
        gitService.insertGit(saveGitRequest);
    }
}
