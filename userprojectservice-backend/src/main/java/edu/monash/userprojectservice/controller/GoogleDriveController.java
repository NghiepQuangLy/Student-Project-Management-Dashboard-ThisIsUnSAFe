package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetGoogleDriveResponse;
import edu.monash.userprojectservice.model.SaveGoogleDriveRequest;
import edu.monash.userprojectservice.model.RemoveGoogleDriveRequest;
import edu.monash.userprojectservice.service.GoogleDriveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com/",
        "http://localhost:3002",
        "http://localhost:3003"
}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class GoogleDriveController {

    private GoogleDriveService googleDriveService;

    @ResponseStatus(OK)
    @GetMapping("/get-googledrive")
    public GetGoogleDriveResponse getGoogleDrive(@RequestParam("requestorEmail") String emailAddress, @RequestParam("projectId") String projectId) {
        return googleDriveService.getgoogleDrive(emailAddress, projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-googledrive")
    public void saveGoogleDrive(@RequestBody @Valid SaveGoogleDriveRequest saveGoogleDriveRequest) {
        googleDriveService.saveGoogleDrive(saveGoogleDriveRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/remove-googledrive")
    public ResponseEntity removeGoogleDrive(@RequestBody @Valid RemoveGoogleDriveRequest removeGoogleDriveRequest) throws SQLException {
        return googleDriveService.removeGoogleDrive(removeGoogleDriveRequest);
    }
}
