package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.AddProjectUserRequest;
import edu.monash.userprojectservice.model.GetUserProjectsResponse;
import edu.monash.userprojectservice.service.UserProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:3003"}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class UserProjectsController {

    private UserProjectService userProjectService;

    @ResponseStatus(OK)
    @GetMapping("/get-projectusers")
    public GetUserProjectsResponse getUserProjects(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return userProjectService.getUsersByProject(emailAddress, projectId);
    }

    @ResponseStatus(OK)
    @PostMapping("/add-projectuser")
    public ResponseEntity addProjectUser(@RequestBody @Valid AddProjectUserRequest addProjectUserRequest) throws SQLException {
        return userProjectService.addProjectUser(addProjectUserRequest);
    }

}
