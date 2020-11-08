package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.model.GetUserResponse;
import edu.monash.userprojectservice.model.UpdateUserRequest;
import edu.monash.userprojectservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-admin-frontend.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com",
        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com/",
        "http://localhost:3002",
        "http://ancient-springs-72265.herokuapp.com",
        "http://ancient-springs-72265.herokuapp.com/",
        "http://localhost:3003",
        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com/",
        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com/",
        "http://d21emc0xlr7tto.cloudfront.net"
}, maxAge = 0)
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
