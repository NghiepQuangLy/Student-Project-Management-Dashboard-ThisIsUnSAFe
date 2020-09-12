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

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:3003"}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class ProjectController {

    private ProjectService projectService;

    @ResponseStatus(OK)
    @GetMapping("/get-project")
    public ResponseEntity<GetProjectResponse> getProject(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return projectService.getProject(emailAddress, projectId);
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

    @ResponseStatus(CREATED)
    @PostMapping("/save-timesheet")
    public void saveTimesheet(@RequestBody @Valid SaveTimesheetRequest saveTimesheetRequest) {
        projectService.saveTimesheet(saveTimesheetRequest);
    }
}
