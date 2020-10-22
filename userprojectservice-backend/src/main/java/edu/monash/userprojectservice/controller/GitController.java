package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetGitResponse;
import edu.monash.userprojectservice.model.SaveGitRequest;
import edu.monash.userprojectservice.model.RemoveGitRequest;
import edu.monash.userprojectservice.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com/",
        "http://localhost:3002",
        "http://localhost:3003"
}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GitController {

    private GitService gitService;

    @ResponseStatus(OK)
    @GetMapping("/get-git")
    public GetGitResponse getGit(@RequestParam("requestorEmail") String emailAddress, @RequestParam("projectId") String projectId) {
        return gitService.getGit(emailAddress, projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-git")
    public void insertGit(@RequestBody @Valid SaveGitRequest saveGitRequest) {
        gitService.insertGit(saveGitRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/remove-git")
    public ResponseEntity removeGit(@RequestBody @Valid RemoveGitRequest removeGitRequest) throws SQLException {
        return gitService.removeGit(removeGitRequest);
    }
}
