package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.model.*;
import edu.monash.userprojectservice.repository.RemoveProjectUserRepository;
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
    private RemoveProjectUserRepository removeProjectUserRepository;

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

        // check if the project exists in the database
        if (projectsRepository.findProjectEntityByProjectId(addProjectUserRequest.getProjectId()) == null) {
            log.warn( addProjectUserRequest.getProjectId() +"not found!");
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }

        // To handle case when an empty list with no users is passed
        if (addProjectUserRequest.getEmailAddress().size() == 0) {
            log.warn( "There are no users to add to Project ID: "+ addProjectUserRequest.getProjectId());
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

        //flag to check if all the users in the list have been added
        Boolean hasAddedAllUsers=true;
        for (int i = 0; i < addProjectUserRequest.getEmailAddress().size(); i++) {
            // edit in db when project and user exist
            System.out.println(addProjectUserRequest.getEmailAddress().get(i));
            Boolean isSuccessful = addProjectUserRepository.save(addProjectUserRequest.getProjectId(), addProjectUserRequest.getEmailAddress().get(i));
            if (!(isSuccessful)) {
                log.info(addProjectUserRequest.getEmailAddress().get(i)+" could not be added!");
                hasAddedAllUsers=false; // in the case that a user cannot be added, this will be set to false to indicate a user from the list of users could not be added.
            }
            if (isSuccessful) {
                log.info(addProjectUserRequest.getEmailAddress().get(i) + " has been added!");

            }
        }
        // if all the users in the list have been added return status 200, else return internal server error
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

    // method is called to remove a list of users from a project
    public ResponseEntity<GetUserProjectsResponse> removeProjectUser(RemoveProjectUserRequest removeProjectUserRequest) throws SQLException {

        // check if the project exists in the database
        if (projectsRepository.findProjectEntityByProjectId(removeProjectUserRequest.getProjectId()) == null) {
            log.warn( removeProjectUserRequest.getProjectId() +"not found!");
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }

        // To handle case when an empty list with no users is passed
        if (removeProjectUserRequest.getEmailAddress().size() == 0) {
            // log an warning that there are no users in the list
            log.warn( "There are no users to add to Project ID: "+ removeProjectUserRequest.getProjectId());
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }


        for (int i = 0; i < removeProjectUserRequest.getEmailAddress().size(); i++) {
            // check if new member exists in database
            if (usersRepository.findUserEntityByEmailAddress(removeProjectUserRequest.getEmailAddress().get(i)) == null) {
                log.warn(removeProjectUserRequest.getEmailAddress().get(i)+" not found!");
                return new ResponseEntity<>(
                        null, INTERNAL_SERVER_ERROR
                );
            }
        }

        //flag to check if all the users in the list have been removed
        Boolean hasRemovedAllUsers=true;
        for (int i = 0; i < removeProjectUserRequest.getEmailAddress().size(); i++) {
            // edit in db when project and user exist
            Boolean isSuccessful = removeProjectUserRepository.delete(removeProjectUserRequest.getProjectId(), removeProjectUserRequest.getEmailAddress().get(i));
            if (!(isSuccessful)) {
                log.info(removeProjectUserRequest.getEmailAddress().get(i)+" could not be removed!");
                hasRemovedAllUsers=false; // in the case that a user cannot be removed, this will be set to false to indicate a user from the list of users could not be removed.
            }
            if (isSuccessful) {
                log.info(removeProjectUserRequest.getEmailAddress().get(i) + " has been removed!");
            }
        }
        // if all the users in the list have been removed, return status 200; else return internal server error
        if (hasRemovedAllUsers) {
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



