package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.UserRequest;
import edu.monash.userprojectservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @ResponseStatus(CREATED)
    @PostMapping("/create-user")
    public void createUser(@RequestBody @Valid UserRequest userRequest) {
        userService.createUser(userRequest);
    }
}
