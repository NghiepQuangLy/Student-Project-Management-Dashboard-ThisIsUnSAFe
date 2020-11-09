package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.model.GetUserResponse;
import edu.monash.userprojectservice.model.UpdateUserRequest;
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

    @ResponseStatus(CREATED)
    @PostMapping("/create-user")
    public GetUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @ResponseStatus(OK)
    @PostMapping("/update-user")
    public void updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
    }

    @ResponseStatus(OK)
    @GetMapping("/get-user")
    public GetUserResponse getUser(@RequestParam("email") String emailAddress) {
        return userService.getUserByEmail(emailAddress);
    }
}
