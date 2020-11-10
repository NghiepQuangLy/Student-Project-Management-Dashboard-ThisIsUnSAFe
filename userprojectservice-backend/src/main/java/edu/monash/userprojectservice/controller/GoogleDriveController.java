package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetIntegrationsResponse;
import edu.monash.userprojectservice.model.RemoveGoogleDriveRequest;
import edu.monash.userprojectservice.model.SaveGoogleDriveRequest;
import edu.monash.userprojectservice.service.GoogleDriveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GoogleDriveController {

    private GoogleDriveService googleDriveService;

    /*
     * This method is to get list of google drive data
     * @requestParam emailAddress The email address to be validated
     * @requestParam projectId The project that contains the google drive ids
     * @return 200 GetIntegrationsResponse This returns list of google drive ids
     * @return 400 when email is empty or not monash email, when project id is empty,
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @GetMapping("/get-googledrive")
    public GetIntegrationsResponse getGoogleDrive(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return googleDriveService.getgoogleDrive(emailAddress, projectId);
    }

    /*
     * This method is to store google drive data to a project
     * @requestBody saveGoogleDriveRequest google drive data, project id and email address
     * @return 201 When save google drive data successfully
     * @return 400 when email is empty or not monash email, when project id is empty
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(CREATED)
    @PostMapping("/save-googledrive")
    public void saveGoogleDrive(@RequestBody @Valid SaveGoogleDriveRequest saveGoogleDriveRequest) {
        googleDriveService.saveGoogleDrive(saveGoogleDriveRequest);
    }

    /*
     * This method is to remove google drive data from a project
     * @requestBody removeGoogleDriveRequest google drive id, project id and email address
     * @return 400 when email is empty or not monash email, when project id is empty
     * @return 404 when google drive id is not found in the project data
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @PostMapping("/remove-googledrive")
    public void removeGoogleDrive(@RequestBody @Valid RemoveGoogleDriveRequest removeGoogleDriveRequest) {
        googleDriveService.removeGoogleDrive(removeGoogleDriveRequest);
    }
}
