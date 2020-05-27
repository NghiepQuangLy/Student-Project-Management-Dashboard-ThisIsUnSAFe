package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GitController {

    private GitService gitService;

    @ResponseStatus(OK)
        @GetMapping("/get-git")
    public void getGit(@RequestParam("projectId") int projectId) {
        gitService.getGit(projectId);
    }

    @ResponseStatus(CREATED)
    @GetMapping("/insert-git")
    public void insertGit(@RequestParam("projectId") int projectId, @RequestParam("gitId") int gitId) {
        gitService.insertGit(projectId, gitId);
    }
}
