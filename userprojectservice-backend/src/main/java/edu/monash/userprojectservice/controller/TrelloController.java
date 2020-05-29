package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.InsertTrelloRequest;
import edu.monash.userprojectservice.service.TrelloService;
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
public class TrelloController {

    private TrelloService trelloService;

    @ResponseStatus(OK)
    @GetMapping("/get-trello")
    public void getTrello(@RequestParam("projectId") String projectId) {
        trelloService.getTrello(projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-trello")
    public void saveTrello(@RequestBody @Valid InsertTrelloRequest insertTrelloRequest) {
        trelloService.saveTrello(insertTrelloRequest);
    }
}
