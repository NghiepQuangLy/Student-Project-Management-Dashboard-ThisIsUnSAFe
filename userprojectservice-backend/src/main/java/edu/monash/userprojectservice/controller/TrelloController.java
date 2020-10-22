package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetTrelloResponse;
import edu.monash.userprojectservice.model.SaveTrelloRequest;
import edu.monash.userprojectservice.model.RemoveTrelloRequest;
import edu.monash.userprojectservice.service.TrelloService;
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

    @ResponseStatus(OK)
    @PostMapping("/remove-trello")
    public ResponseEntity removeTrello(@RequestBody @Valid RemoveTrelloRequest removeTrelloRequest) throws SQLException {
        return trelloService.removeTrello(removeTrelloRequest);
    }
}
