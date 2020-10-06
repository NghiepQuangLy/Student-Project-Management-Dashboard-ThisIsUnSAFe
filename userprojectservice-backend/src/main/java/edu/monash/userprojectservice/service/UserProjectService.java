package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.model.*;
import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import edu.monash.userprojectservice.repository.AddProjectUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
public class UserProjectService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AddProjectUserRepository addProjectUserRepository;

    @Autowired
    private ValidationHandler validationHandler;


    public GetUserProjectsResponse getUsersByProject(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting projects\", \"user\":\"{}\"}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);


        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(projectId);

        if (projectEntity != null) {
            GetUserProjectsResponse getUserProjectsResponse = GetUserProjectsResponse.builder()
                    .build();

            ProjectListResponse projectListResponse = ProjectListResponse.builder()
                    .projectId(projectEntity.getProjectId())
                    .projectName(projectEntity.getProjectName())
                    .build();

            getUserProjectsResponse.setProjects(projectListResponse);

            List<UsersProjectsEntity> usersProjectsEntities = usersProjectsRepository.findUsersProjectsEntitiesByProjectId(projectId);

            log.info("{\"message\":\"Got projects\", \"user\":\"{}\"", projectId);

            List<GetUserResponse> getUserResponse = usersProjectsEntities.stream()
                    .map(this::convertToGetUserResponse)
                    .collect(Collectors.toList());

            log.info("{\"message\":\"Got projects\", \"project list\":\"{}\"}", getUserResponse);

            getUserProjectsResponse.setUsers(getUserResponse);

            return getUserProjectsResponse;
        } else {
            // show return 404 not found
            throw new HTTPResponseHandler.NotFoundException();
        }
    }

    private GetUserResponse convertToGetUserResponse(UsersProjectsEntity projectEntity) {
        return GetUserResponse.builder()
                .firstName(projectEntity.getUserEntity().getGivenName())
                .lastName(projectEntity.getUserEntity().getFamilyName())
                .emailAddress(projectEntity.getUserEntity().getEmailAddress())
                .userGroup(projectEntity.getUserEntity().getUserGroup())
                .projects(null)
                .build();
    }

    // edit a project
    // check for the user first, if it doesnt exist new responseEntity and return not found
    // if he exists then, return OK
    public ResponseEntity<GetUserProjectsResponse> addProjectUser(AddProjectUserRequest addProjectUserRequest) throws SQLException {
        System.out.println("Test123");
        // check if the project exists in the database
        if (projectsRepository.findProjectEntityByProjectId(addProjectUserRequest.getProjectId()) == null) {
            log.warn("Project not found!");
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }

        for (int i = 0; i < addProjectUserRequest.getEmailAddress().size(); i++) {
            // check if new member exists in database
            if (usersRepository.findUserEntityByEmailAddress(addProjectUserRequest.getEmailAddress().get(i)) == null) {
                log.warn(addProjectUserRequest.getEmailAddress().get(i)+" not found!");
                return new ResponseEntity<>(
                        null, INTERNAL_SERVER_ERROR
                );
            }
        }

        Boolean hasAddedAllUsers=true;
        for (int i = 0; i < addProjectUserRequest.getEmailAddress().size(); i++) {
                // edit in db when project and user exist
            System.out.println(addProjectUserRequest.getEmailAddress().get(i));
            Boolean isSuccessful = addProjectUserRepository.save(addProjectUserRequest.getProjectId(), addProjectUserRequest.getEmailAddress().get(i));
            if (!(isSuccessful)) {
                log.info(addProjectUserRequest.getEmailAddress().get(i)+" could not be added!");
                hasAddedAllUsers=false;
            }
            if (isSuccessful) {
                log.info(addProjectUserRequest.getEmailAddress().get(i) + " has been added!");

            }
        }
        if (hasAddedAllUsers) {
            return new ResponseEntity<>(
                    null, OK
            );
        }
        else{
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }

    }
}



