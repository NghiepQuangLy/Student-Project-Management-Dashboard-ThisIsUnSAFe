package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateProjectRequest;
import edu.monash.userprojectservice.model.EditProjectRequest;
import edu.monash.userprojectservice.model.GetAllProjectsResponse;
import edu.monash.userprojectservice.model.GetProjectDetailsResponse;
import edu.monash.userprojectservice.model.GetTimesheetResponse;
import edu.monash.userprojectservice.model.RemoveProjectRequest;
import edu.monash.userprojectservice.model.RemoveTimesheetRequest;
import edu.monash.userprojectservice.model.SaveTimesheetRequest;
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

    /*
     * This method is to get project by project id
     * @requestParam emailAddress The email address to be validated
     * @requestParam projectId The project id
     * @return 200 This returns project details
     * @exception 400 when email is empty or not monash email, when project id is empty,
     * @exception 404 when project is not found
     * @exception 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @GetMapping("/get-project")
    public GetProjectDetailsResponse getProject(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId, @RequestParam(value = "idToken", required = false
    ) String idToken) {
        return projectService.getProject(emailAddress, projectId, idToken);
    }

    /*
     * This method is to create project
     * @param createProjectRequest requestor email and project details
     * @return 400 when email is empty or not monash email, when project id is empty,
     * @return 403 when requestor is not admin
     */
    @ResponseStatus(CREATED)
    @PostMapping("/create-project")
    public ResponseEntity setProjectUser(@RequestBody @Valid CreateProjectRequest createProjectRequest) throws SQLException {
        return projectService.createProject(createProjectRequest);
    }

    /*
     * This method is to remove a project
     * @requestBody createProjectRequest requestor email and project details
     * @return 400 when email is empty or fileds in removeProjectRequest is empty
     * @return 403 when requestor is not admin
     * @return 404 when project is not found
     */
    @ResponseStatus(OK)
    @PostMapping("/remove-project")
    public ResponseEntity removeProject(@RequestBody @Valid RemoveProjectRequest removeProjectRequest) throws SQLException {
        return projectService.removeProject(removeProjectRequest);
    }

    /*
     * This method is to edit a project
     * @param editProjectRequest requestor email and project details
     * @return 400 when email is empty or fileds in editProjectRequest is empty
     * @return 403 when requestor is not admin
     * @return 404 when project is not found
     */
    @ResponseStatus(OK)
    @PostMapping("/edit-project")
    public ResponseEntity setProjectName(@RequestBody @Valid EditProjectRequest editProjectRequest) throws SQLException {
        return projectService.editProject(editProjectRequest);
    }

    /*
     * This method is to get a project timesheet
     * @param emailAddress The requestor email address
     * @param projectId The project that contains the timesheet
     * @return 200 This returns timesheet
     * @return 400 when email is empty
     * @return 403 when requestor is not admin
     * @return 404 when project is not found
     */
    @ResponseStatus(OK)
    @GetMapping("/get-timesheet")
    public GetTimesheetResponse getTimesheet(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return projectService.getTimesheet(emailAddress, projectId);
    }

    /*
     * This method is to save timesheet to a project
     * @param saveTimesheetRequest requestor email, timesheet id and project id
     * @return 400 when email is empty or fields in saveTimesheetRequest is empty
     * @return 403 project does not belong to the email
     * @return 404 when project is not found
     */
    @ResponseStatus(CREATED)
    @PostMapping("/save-timesheet")
    public void saveTimesheet(@RequestBody @Valid SaveTimesheetRequest saveTimesheetRequest) {
        projectService.saveTimesheet(saveTimesheetRequest);
    }

    /*
     * This method is to remove timesheet from a project
     * @param removeTimesheetRequest requestor email and project id
     * @excepreturntion 400 when email is empty or fields in removeTimesheetRequest is empty
     * @return 403 project does not belong to the email
     * @return 404 when project is not found
     */
    @ResponseStatus(OK)
    @PostMapping("/remove-timesheet")
    public ResponseEntity removeTimesheet(@RequestBody @Valid RemoveTimesheetRequest removeTimesheetRequest) throws SQLException {
        return projectService.removeTimesheet(removeTimesheetRequest);
    }

    /*
     * This method is to get all projects
     * @param emailAddress The email address to be validated
     * @return 200 This returns all projects' details
     * @return 400 when email is empty
     * @return 403 when requestor is not admin
     */
    @ResponseStatus(OK)
    @GetMapping("/get-all-projects")
    public GetAllProjectsResponse getAllProjects(@RequestParam("requestorEmail") String emailAddress) {
        return projectService.getAllProjects(emailAddress);
    }
}
