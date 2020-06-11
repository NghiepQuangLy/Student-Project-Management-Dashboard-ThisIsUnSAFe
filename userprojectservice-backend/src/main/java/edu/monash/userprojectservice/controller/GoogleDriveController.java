package edu.monash.userprojectservice.controller;

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

    @ResponseStatus(OK)
    @GetMapping("/get-googledrive")
    public void getGoogleDrive(@RequestParam("projectId") String projectId) {
        googleDriveService.getgoogleDrive(projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-googledrive")
    public void saveGoogleDrive(@RequestBody @Valid SaveGoogleDriveRequest saveGoogleDriveRequest) {
        googleDriveService.saveGoogleDrive(saveGoogleDriveRequest);
    }
}
