package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.*;
import edu.monash.userprojectservice.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

//@CrossOrigin(origins = {
//        "http://localhost:3000",
//        "http://localhost:3001",
//        "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com",
//        "http://spmd-admin-frontend.s3-website-ap-southeast-2.amazonaws.com",
//        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com",
//        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com/",
//        "http://localhost:3002",
//        "http://ancient-springs-72265.herokuapp.com",
//        "http://ancient-springs-72265.herokuapp.com/",
//        "http://localhost:3003",
//        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com",
//        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com/",
//        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com",
//        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com/",
//        "http://d21emc0xlr7tto.cloudfront.net"
//}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class ProjectController {

    private ProjectService projectService;

    @ResponseStatus(OK)
    @GetMapping("/get-project")
    public ResponseEntity<GetProjectDetailsResponse> getProject(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return projectService.getProject(emailAddress, projectId);
    }

    @ResponseStatus(OK)
    @GetMapping("/get-all-projects")
    public GetAllProjectsResponse getAllProjects(@RequestParam("requestorEmail") String emailAddress) {
        return projectService.getAllProjects(emailAddress);
    }

    @ResponseStatus(OK)
    @GetMapping("/get-timesheet")
    public GetTimesheetResponse getTimesheet(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return projectService.getTimesheet(emailAddress, projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/create-project")
    public ResponseEntity setProjectUser(@RequestBody @Valid CreateProjectRequest createProjectRequest) throws SQLException {
        return projectService.createProject(createProjectRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/remove-project")
    public ResponseEntity removeProject(@RequestBody @Valid RemoveProjectRequest removeProjectRequest) throws SQLException {
        return projectService.removeProject(removeProjectRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/edit-project")
    public ResponseEntity setProjectName(@RequestBody @Valid EditProjectRequest editProjectRequest) throws SQLException {
        return projectService.editProject(editProjectRequest);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-timesheet")
    public void saveTimesheet(@RequestBody @Valid SaveTimesheetRequest saveTimesheetRequest) {
        projectService.saveTimesheet(saveTimesheetRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/remove-timesheet")
    public ResponseEntity removeTimesheet(@RequestBody @Valid RemoveTimesheetRequest removeTimesheetRequest) throws SQLException {
        return projectService.removeTimesheet(removeTimesheetRequest);
    }
}
