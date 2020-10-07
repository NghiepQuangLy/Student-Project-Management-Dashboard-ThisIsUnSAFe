package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetTrelloResponse;
import edu.monash.userprojectservice.model.SaveTrelloRequest;
import edu.monash.userprojectservice.model.RemoveTrelloRequest;
import edu.monash.userprojectservice.service.TrelloService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:3003"}, maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class TrelloController {

    private TrelloService trelloService;

    @ResponseStatus(OK)
    @GetMapping("/get-trello")
    public GetTrelloResponse getTrello(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return trelloService.getTrello(emailAddress, projectId);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save-trello")
    public void saveTrello(@RequestBody @Valid SaveTrelloRequest saveTrelloRequest) {
        trelloService.saveTrello(saveTrelloRequest);
    }

    @ResponseStatus(ACCEPTED)
    @PostMapping("/remove-trello")
    public void removeTrello(@RequestBody @Valid RemoveTrelloRequest removeTrelloRequest) {
        trelloService.removeTrello(removeTrelloRequest);
    }
}
