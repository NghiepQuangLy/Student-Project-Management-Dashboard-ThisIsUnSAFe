package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.model.SetUserProjectRequest;
import edu.monash.userprojectservice.service.ProjectService;
import edu.monash.userprojectservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @ResponseStatus(OK)
    @PostMapping("/create-project")
    public ResponseEntity<GetProjectResponse> setProjectUser(@RequestBody @Valid SetUserProjectRequest param) throws SQLException {
        return projectService.addUserProject(param.getEmailAddress(),param.getProjectName());
    }


}
