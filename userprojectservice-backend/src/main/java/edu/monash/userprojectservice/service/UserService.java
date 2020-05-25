package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(CreateUserRequest createUserRequest) {
        log.info("{\"message\":\"Creating user\", \"user\":\"{}\"}", createUserRequest);

        // save to database

        // log.info("{\"message\":\"Saved user\", \"user\":\"{}\"", saved);
    }

    public void getUserByEmail(String emailAddress) {
        log.info("{\"message\":\"Getting user\", \"user\":\"{}\"}", emailAddress);

        // get from database
        Optional<UserDetail> userDetail = userRepository.findByEmail(emailAddress);

        UserDetail userResponse = userDetail.orElse(null);
        if (userResponse != null){
            System.out.println(userResponse.getEmail_address());
            System.out.println(userResponse.getGiven_name());
            System.out.println(userResponse.getFamily_name());
            System.out.println(userResponse.getUser_group());

        }


        // log.info("{\"message\":\"Got user\", \"user\":\"{}\"", user);
    }
}
