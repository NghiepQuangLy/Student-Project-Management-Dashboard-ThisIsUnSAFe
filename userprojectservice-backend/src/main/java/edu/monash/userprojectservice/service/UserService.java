package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.model.GetUserResponse;
import edu.monash.userprojectservice.model.ProjectListResponse;
import edu.monash.userprojectservice.model.UpdateUserRequest;
import edu.monash.userprojectservice.repository.user.UserEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.service.usergroup.UserGroupHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private ValidationHandler validationHandler;

    @Autowired
    private UserGroupHelper userGroupHelper;

    public void createUser(CreateUserRequest createUserRequest) {
        log.info("{\"message\":\"Creating user\", \"user\":\"{}\"}", createUserRequest);

        String userGroup = userGroupHelper.getUserGroupByEmail(createUserRequest.getEmailAddress()).name();

        // save to database
        UserEntity userEntity = usersRepository.findUserEntityByEmailAddress(createUserRequest.getEmailAddress());
        if (userEntity == null) {
            usersRepository.save(new UserEntity(
                    createUserRequest.getEmailAddress(),
                    createUserRequest.getFamilyName(),
                    createUserRequest.getGivenName(),
                    userGroup
            ));
            // Created 201
            log.info("{\"message\":\"Saved user\"}");
        } else {
            // Bad Request 400
            log.warn("{\"message\":\"User already exist\"}");
            throw new HTTPResponseHandler.BadRequestException("User already exist");
        }
    }

    public void updateUser(UpdateUserRequest updateUserRequest) {
        log.info("{\"message\":\"Updating user\", \"user\":\"{}\"}", updateUserRequest);

        // save to database
        UserEntity userEntity = usersRepository.findUserEntityByEmailAddress(updateUserRequest.getEmailAddress());

        if (userEntity == null) {
            log.warn("{\"message\":\"Saved user\"}");
            throw new HTTPResponseHandler.NotFoundException("User not found.");
        } else {
            boolean hasSameFamilyName = userEntity.getFamilyName().equals(updateUserRequest.getFamilyName());
            boolean hasSameGivenName = userEntity.getGivenName().equals(updateUserRequest.getGivenName());
            if (!hasSameFamilyName || !hasSameGivenName){
                UserEntity newUserEntity = new UserEntity(
                        userEntity.getEmailAddress(),
                        updateUserRequest.getFamilyName(),
                        updateUserRequest.getGivenName(),
                        userEntity.getUserGroup()
                );
                usersRepository.save(newUserEntity);
                log.info("{\"message\":\"User updated\"}");
            } else {
                log.info("{\"message\":\"Same user information, no user updated\"}");
            }
        }
    }

    public GetUserResponse getUserByEmail(String emailAddress) {
        if (emailAddress.equals("")) {
            return null;
        }
        log.info("{\"message\":\"Getting user\", \"user\":\"{}\"}", emailAddress);

        UserEntity userEntity = usersRepository.findUserEntityByEmailAddress(emailAddress);

        if (userEntity != null) {
            GetUserResponse getUserResponse = GetUserResponse.builder()
                    .emailAddress(userEntity.getEmailAddress())
                    .firstName(userEntity.getGivenName())
                    .lastName(userEntity.getFamilyName())
                    .userGroup(userEntity.getUserGroup())
                    .build();

            log.info("{\"message\":\"Got user\", \"user\":\"{}\"", userEntity);
            log.info("{\"message\":\"Getting projects list\", \"user\":\"{}\"}", emailAddress);

            List<UsersProjectsEntity> usersProjectsEntities = usersProjectsRepository.findUsersProjectsEntitiesByEmailAddress(emailAddress);

            List<ProjectListResponse> projectListResponses = usersProjectsEntities.stream()
                    .map(this::convertToProjectListResponse)
                    .collect(Collectors.toList());

            log.info("{\"message\":\"Got projects\", \"project list\":\"{}\"}", projectListResponses);

            getUserResponse.setProjects(projectListResponses);

            return getUserResponse;
        } else {
            // show return 404 not found
            throw new HTTPResponseHandler.NotFoundException("User Not Found");
        }
    }

    private ProjectListResponse convertToProjectListResponse(UsersProjectsEntity projectEntity) {
        return ProjectListResponse.builder()
                .projectId(projectEntity.getProjectId())
                .projectName(projectEntity.getProjectEntity().getProjectName())
                .projectUnitCode(projectEntity.getProjectEntity().getUnitCode())
                .projectYear(projectEntity.getProjectEntity().getProjectYear())
                .projectSemester(projectEntity.getProjectEntity().getProjectSemester())
                .build();
    }
}
