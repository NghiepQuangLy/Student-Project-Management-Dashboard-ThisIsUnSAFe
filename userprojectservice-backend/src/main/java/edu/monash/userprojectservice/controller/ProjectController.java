package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateProjectRequest;
import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.service.ProjectService;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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


    @ResponseStatus(CREATED)
    @PostMapping("/create-project")
    public ResponseEntity setProjectUser(@RequestBody @Valid CreateProjectRequest createProjectRequest) throws SQLException {
        return projectService.createProject(createProjectRequest.getEmailAddress(), createProjectRequest.getProjectName());
    }


}
