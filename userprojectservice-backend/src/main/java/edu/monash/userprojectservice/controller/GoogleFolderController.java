package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.SaveGoogleFolderRequest;
import edu.monash.userprojectservice.service.GoogleFolderService;
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
public class GoogleFolderController {

    private GoogleFolderService googleFolderService;

    @ResponseStatus(OK)
    @GetMapping("/get-googlefolder")
    public void getGoogleFolder(@RequestParam("projectId") String projectId) {
        googleFolderService.getgoogleFolder(projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-googlefolder")
    public void saveGoogleFolder(@RequestBody @Valid SaveGoogleFolderRequest saveGoogleFolderRequest) {
        googleFolderService.saveGoogleFolder(saveGoogleFolderRequest);
    }
}
