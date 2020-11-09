package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.AddProjectUserRequest;
import edu.monash.userprojectservice.model.GetUserProjectsResponse;
import edu.monash.userprojectservice.model.RemoveProjectUserRequest;
import edu.monash.userprojectservice.service.UserProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class UserProjectsController {

    private UserProjectService userProjectService;

    @ResponseStatus(OK)
    @GetMapping("/get-projectusers")
    public GetUserProjectsResponse getUserProjects(@RequestParam("requestorEmail") String emailAddress, @RequestParam("projectId") String projectId) {
        return userProjectService.getUsersByProject(emailAddress, projectId);
    }

    @PostMapping("/add-projectuser")
    public ResponseEntity addProjectUser(@RequestBody @Valid AddProjectUserRequest addProjectUserRequest) throws SQLException {
        return userProjectService.addProjectUser(addProjectUserRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/remove-projectuser")
    public ResponseEntity removeProjectUser(@RequestBody @Valid RemoveProjectUserRequest removeProjectUserRequest) throws SQLException {
        return userProjectService.removeProjectUser(removeProjectUserRequest);
    }
}
