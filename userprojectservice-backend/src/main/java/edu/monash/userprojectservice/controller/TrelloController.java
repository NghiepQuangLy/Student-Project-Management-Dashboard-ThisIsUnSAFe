package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.service.TrelloService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class TrelloController {

    private TrelloService trelloService;

    @ResponseStatus(OK)
    @GetMapping("/get-trello")
    public void getTrello(@RequestParam("projectId") int projectId) {
        trelloService.getTrello(projectId);
    }

    @ResponseStatus(CREATED)
    @GetMapping("/insert-trello")
    public void insertTrello(@RequestParam("projectId") int projectId, @RequestParam("trelloId") int trelloId) {
        trelloService.insertTrello(projectId, trelloId);
    }
}
