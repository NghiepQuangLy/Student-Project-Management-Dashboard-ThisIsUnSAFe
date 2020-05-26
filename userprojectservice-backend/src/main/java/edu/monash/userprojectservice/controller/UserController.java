package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.service.ProjectService;
import edu.monash.userprojectservice.service.UserService;
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
public class UserController {

    private UserService userService;
    //private ProjectService projectService;

    @ResponseStatus(CREATED)
    @PostMapping("/create-user")
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }

    @ResponseStatus(OK)
    @GetMapping("/get-user")
    public void getUser(@RequestParam("email") String emailAddress) {
        userService.getUserByEmail(emailAddress);
    }
}
