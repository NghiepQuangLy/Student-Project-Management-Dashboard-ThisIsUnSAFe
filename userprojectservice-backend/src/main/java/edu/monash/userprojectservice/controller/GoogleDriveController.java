package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.SaveGoogleDriveRequest;
import edu.monash.userprojectservice.service.GoogleDriveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:3003"}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GoogleDriveController {

    private GoogleDriveService googleDriveService;

    @ResponseStatus(OK)
    @GetMapping("/get-googledrive")
    public void getGoogleDrive(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        googleDriveService.getgoogleDrive(emailAddress, projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-googledrive")
    public void saveGoogleDrive(@RequestBody @Valid SaveGoogleDriveRequest saveGoogleDriveRequest) {
        googleDriveService.saveGoogleDrive(saveGoogleDriveRequest);
    }
}
