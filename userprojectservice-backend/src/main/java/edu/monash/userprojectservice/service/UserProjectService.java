package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.model.*;
import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserProjectService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;


    public GetUserProjectsResponse getUsersByProject(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting projects\", \"user\":\"{}\"}", projectId);

        // Validation Check
        ValidationHandler validationHandler = new ValidationHandler();
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
}
