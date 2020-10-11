package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.model.*;
import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
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
    private ProjectsRepository projectsRepository;

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

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
                    .projectUnitCode(projectEntity.getUnitCode())
                    .projectYear(projectEntity.getProjectYear())
                    .projectSemester(projectEntity.getProjectSemester())
                    .build();

            getUserProjectsResponse.setProjects(projectListResponse);

            List<UsersProjectsEntity> usersProjectsEntities = usersProjectsRepository.findUsersProjectsEntitiesByProjectId(projectId);

            log.info("{\"message\":\"Got projects\", \"user\":\"{}\"", projectId);

            List<ProjectUsers> projectUsers = usersProjectsEntities.stream()
                    .map(this::convertToProjectUsers)
                    .collect(Collectors.toList());

            log.info("{\"message\":\"Got projects\", \"project list\":\"{}\"}", projectUsers);

            getUserProjectsResponse.setUsers(projectUsers);

            return getUserProjectsResponse;
        } else {
            // show return 404 not founduserProjectService
            throw new HTTPResponseHandler.NotFoundException();
        }
    }

    // Remove link between user email and project in userproject table
    // Useful in removing the userproject foreign key from a project
    public void removeUsersByProject(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting projects\", \"user\":\"{}\"}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);

        List<UsersProjectsEntity> usersProjectEntity = usersProjectsRepository.findUsersProjectsEntitiesByProjectId(projectId);

        if (usersProjectEntity != null) {
            for (int i=0; i < usersProjectEntity.size(); i++){
                usersProjectsRepository.delete(usersProjectEntity.get(i));
            }

            log.info("{\"message\":\"Remove user project\", \"projectId\":\"{}\"}", projectId);
        }
    }

    private ProjectUsers convertToProjectUsers(UsersProjectsEntity projectEntity) {
        return ProjectUsers.builder()
                .firstName(projectEntity.getUserEntity().getGivenName())
                .lastName(projectEntity.getUserEntity().getFamilyName())
                .emailAddress(projectEntity.getUserEntity().getEmailAddress())
                .userGroup(projectEntity.getUserEntity().getUserGroup())
                .build();
    }
}
