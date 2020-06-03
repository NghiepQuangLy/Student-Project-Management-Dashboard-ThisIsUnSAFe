package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.model.GetUserResponse;
import edu.monash.userprojectservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 0)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @ResponseStatus(CREATED)
    @PostMapping("/create-user")
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }

    @ResponseStatus(OK)
    @GetMapping("/get-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public GetUserResponse getUser(@RequestParam("email") String emailAddress) {
        return userService.getUserByEmail(emailAddress);
    }
}
