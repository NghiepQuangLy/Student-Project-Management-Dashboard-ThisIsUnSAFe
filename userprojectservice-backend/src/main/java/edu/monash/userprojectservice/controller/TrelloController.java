package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.model.InsertTrelloRequest;
import edu.monash.userprojectservice.service.TrelloService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/insert-trello")
    public void insertTrello(@RequestBody @Valid InsertTrelloRequest insertTrelloRequest) {
        trelloService.insertTrello(insertTrelloRequest);
    }
}
