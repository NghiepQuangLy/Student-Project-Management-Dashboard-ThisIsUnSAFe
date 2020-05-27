package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.model.GetUserResponse;
import edu.monash.userprojectservice.model.ProjectListResponse;
import edu.monash.userprojectservice.repository.ProjectShortDetail;
import edu.monash.userprojectservice.repository.User;
import edu.monash.userprojectservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(CreateUserRequest createUserRequest) {
        log.info("{\"message\":\"Creating user\", \"user\":\"{}\"}", createUserRequest);

        // save to database
        userRepository.create(createUserRequest);

        log.info("{\"message\":\"Saved user\"}");
    }

    public GetUserResponse getUserByEmail(String emailAddress) {
        if (emailAddress.equals("")) {
            return null;
        }
        log.info("{\"message\":\"Getting user\", \"user\":\"{}\"}", emailAddress);

        User user = userRepository.findUserByEmail(emailAddress).orElse(null);

        if (user != null) {
            GetUserResponse getUserResponse = GetUserResponse.builder()
                    .emailAddress(user.getEmail_address())
                    .firstName(user.getGiven_name())
                    .lastName(user.getFamily_name())
                    .userGroup(user.getUser_group())
                    .build();

            log.info("{\"message\":\"Got user\", \"user\":\"{}\"", user);
            log.info("{\"message\":\"Getting projects list\", \"user\":\"{}\"}", emailAddress);

            List<ProjectShortDetail> projectShortDetails = userRepository.findProjectByEmail(emailAddress);
            List<ProjectListResponse> projectListResponses = projectShortDetails.stream()
                    .map(this::convertToProjectListResponse)
                    .collect(Collectors.toList());

            log.info("{\"message\":\"Got projects\", \"project list\":\"{}\"}", projectListResponses);

            getUserResponse.setProjects(projectListResponses);

            return getUserResponse;
        } else {
            return null;
        }
    }

    private ProjectListResponse convertToProjectListResponse(ProjectShortDetail projectShortDetail) {
        return ProjectListResponse.builder()
                .projectId(projectShortDetail.getProject_id())
                .projectName(projectShortDetail.getProject_name())
                .build();
    }
}
