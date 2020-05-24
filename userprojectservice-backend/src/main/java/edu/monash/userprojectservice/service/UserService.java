package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public void createUser(UserRequest userRequest) {
        log.info("{\"message\":\"Creating user\", \"user\":\"{}\"}", userRequest);

        // save to database

        // log.info("{\"message\":\"Saved user\", \"user\":\"{}\"", saved);
    }
}
