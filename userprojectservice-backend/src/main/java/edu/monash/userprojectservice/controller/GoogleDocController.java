package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.InsertGoogleDocRequest;
import edu.monash.userprojectservice.model.InsertTrelloRequest;
import edu.monash.userprojectservice.service.GoogleDocService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void getGoogleDoc(@RequestParam("projectId") int projectId, @RequestParam("googleDocId") int googleDocId) {
        googleDocService.getgoogleDoc(projectId, googleDocId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/insert-googledoc")
    public void insertGoogleDoc(@RequestBody @Valid InsertGoogleDocRequest insertGoogleDocRequest) {
        googleDocService.insertGoogleDoc(insertGoogleDocRequest);
    }
}
