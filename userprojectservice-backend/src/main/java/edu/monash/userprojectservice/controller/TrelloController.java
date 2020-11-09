package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.GetIntegrationsResponse;
import edu.monash.userprojectservice.model.RemoveTrelloRequest;
import edu.monash.userprojectservice.model.SaveTrelloRequest;
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
import java.sql.SQLException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class TrelloController {

    private TrelloService trelloService;

    /*
     * This method is to get list of trello data
     * @requestParam emailAddress The email address to be validated
     * @requestParam projectId The project that contains the trello ids
     * @return 200 GetIntegrationsResponse This returns list of trello ids
     * @return 400 when email is empty or not monash email, when project id is empty,
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @GetMapping("/get-trello")
    public GetIntegrationsResponse getTrello(@RequestParam("email") String emailAddress, @RequestParam("projectId") String projectId) {
        return trelloService.getTrello(emailAddress, projectId);
    }

    /*
     * This method is to store trello data to a project
     * @requestBody saveTrelloRequest trello data, project id and email address
     * @return 201 When save trello data successfully
     * @return 400 when email is empty or not monash email, when project id is empty
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(CREATED)
    @PostMapping("/save-trello")
    public void saveTrello(@RequestBody @Valid SaveTrelloRequest saveTrelloRequest) {
        trelloService.saveTrello(saveTrelloRequest);
    }

    /*
     * This method is to remove trello data from a project
     * @requestBody removeTrelloRequest trello id, project id and email address
     * @return 400 when email is empty or not monash email, when project id is empty
     * @return 404 when trello id is not found in the project data
     * @return 403 when project does not belong to the email
     */
    @ResponseStatus(OK)
    @PostMapping("/remove-trello")
    public void removeTrello(@RequestBody @Valid RemoveTrelloRequest removeTrelloRequest) throws SQLException {
        trelloService.removeTrello(removeTrelloRequest);
    }
}
