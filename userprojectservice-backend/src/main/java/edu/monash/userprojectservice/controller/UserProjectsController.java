package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.AddProjectUserRequest;
import edu.monash.userprojectservice.model.GetUserProjectsResponse;
import edu.monash.userprojectservice.model.RemoveProjectUserRequest;
import edu.monash.userprojectservice.service.UserProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-admin-frontend.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com",
        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com/",
        "http://localhost:3002",
        "http://ancient-springs-72265.herokuapp.com",
        "http://ancient-springs-72265.herokuapp.com/",
        "http://localhost:3003",
        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com/",
        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com/",
        "http://d21emc0xlr7tto.cloudfront.net"
}, maxAge = 0)
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
