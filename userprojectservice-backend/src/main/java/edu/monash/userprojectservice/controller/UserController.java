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

    /*
     * This method is to create user
     * @param createUserRequest user details
     * @return 201 when user created successfully
     * @return 400 when email is not monash format, or user already exist
     */
    @ResponseStatus(CREATED)
    @PostMapping("/create-user")
    public GetUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    /*
     * This method is to update a user
     * @param updateUserRequest user details
     * @return 200 when user updated successfully
     * @return 400 when fields in updateUserRequest is empty
     * @return 404 user not found
     */
    @ResponseStatus(OK)
    @PostMapping("/update-user")
    public void updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
    }

    /*
     * This method is to get user by email
     * @param emailAddress The email address to be validated
     * @return 200 This returns user details
     * @return 400 when email is empty or not monash email
     * @return 404 when user is not found
     */
    @ResponseStatus(OK)
    @GetMapping("/get-user")
    public GetUserResponse getUser(@RequestParam("email") String emailAddress) {
        return userService.getUserByEmail(emailAddress);
    }
}
