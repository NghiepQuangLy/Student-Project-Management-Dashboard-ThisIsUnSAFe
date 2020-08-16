package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.*;
import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.user.UserEntity;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

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
        if (projectId.equals("")) {
            return null;
        }
        log.info("{\"message\":\"Getting projects\", \"user\":\"{}\"}", projectId);

        if (!isUserProject(emailAddress, projectId)) {
            System.out.println("Project does not belong to the user.");
            // Unauthorised error
            return new GetUserProjectsResponse (
                    null, null
            );
            // if project is null, 404 not found
        }

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
            return null;
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

    private Boolean isUserProject(String emailAddress, String projectId) {
        List<UsersProjectsEntity> userProjects = usersProjectsRepository.findUsersProjectsEntitiesByEmailAddress(emailAddress);
        System.out.println(userProjects);
        for (UsersProjectsEntity userProject : userProjects) {
            if (projectId.equals(userProject.getProjectId())) {
                return true;
            }
        }
        return false;
    }
}
