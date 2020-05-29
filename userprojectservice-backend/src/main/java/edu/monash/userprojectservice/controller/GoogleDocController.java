package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.SaveGoogleDocRequest;
import edu.monash.userprojectservice.service.GoogleDocService;
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
public class GoogleDocController {

    private GoogleDocService googleDocService;

    @ResponseStatus(OK)
    @GetMapping("/get-googledoc")
    public void getGoogleDoc(@RequestParam("projectId") String projectId) {
        googleDocService.getgoogleDoc(projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-googledoc")
    public void saveGoogleDoc(@RequestBody @Valid SaveGoogleDocRequest saveGoogleDocRequest) {
        googleDocService.saveGoogleDoc(saveGoogleDocRequest);
    }
}
