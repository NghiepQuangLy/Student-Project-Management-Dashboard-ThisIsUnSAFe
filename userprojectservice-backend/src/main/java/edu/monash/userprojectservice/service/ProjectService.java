package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.repository.Git;
import edu.monash.userprojectservice.repository.GitRepository;
import edu.monash.userprojectservice.repository.GoogleDoc;
import edu.monash.userprojectservice.repository.GoogleDocRepository;
import edu.monash.userprojectservice.repository.ProjectEntity;
import edu.monash.userprojectservice.repository.ProjectRepository;
import edu.monash.userprojectservice.repository.ProjectsRepository;
import edu.monash.userprojectservice.repository.TrelloEntity;
import edu.monash.userprojectservice.repository.TrelloRepository;
import edu.monash.userprojectservice.repository.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.UsersProjectsRepository;
import edu.monash.userprojectservice.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private GoogleDocRepository googleDocRepository;

    @Autowired
    private TrelloRepository trelloRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    public ResponseEntity<GetProjectResponse> getProject(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        if (!isUserProject(emailAddress, projectId)) {
            System.out.println("Project does not belong to the user.");
            // Unauthorised error
            return new ResponseEntity<>(
                    null, OK
            );
            // if project is null, 404 not found
        }

        // get from database
        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(projectId);

        if (projectEntity == null) {

            System.out.println("Project does not exist.");
            return new ResponseEntity<>(
                    null, OK
            );
        }

        List<Git> gitDetail = gitRepository.findbyProject(projectId);
        List<GoogleDoc> googleDocDetail = googleDocRepository.findbyProject(projectId);
        List<TrelloEntity> trelloDetail = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        System.out.println(projectEntity.getProjectId());
        System.out.println(projectEntity.getProjectName());

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectId);

        return new ResponseEntity<GetProjectResponse>(
                new GetProjectResponse(
                        String.valueOf(projectEntity.getProjectId()),
                        projectEntity.getProjectName(),
                        gitDetail,
                        googleDocDetail,
                        trelloDetail.stream().map(TrelloEntity::getTrelloId).collect(Collectors.toList())
                ), OK
        );
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


    // create a method
    // check for the user first, if it doesnt exist new responseEntity and return not found
    // if he exists then, return OK
    public ResponseEntity<GetProjectResponse> addUserProject(String emailAddress, String projectName) throws SQLException {
        //check if the user is present in the system
        if (usersRepository.findUserEntityByEmailAddress(emailAddress) == null) {
            log.warn("User doesn't exist in the Database!");
            return new ResponseEntity<>(
                    null, NOT_FOUND
            );
        } else {
            String projectId = UUID.randomUUID().toString();
            // check if the project is already present in the database
            while (projectsRepository.findProjectEntityByProjectId(projectId) != null) {
                projectId = UUID.randomUUID().toString();
            }
            // insert into db when project doesnt exist in the db
            Boolean isSuccessful = projectRepository.insertProject(projectId, emailAddress, projectName);
            if (isSuccessful) {
                log.info("Project has been added!");
                return new ResponseEntity<>(
                        null, OK
                );
            } else {
                log.warn("Project could not be added!");
                return new ResponseEntity<>(
                        null, INTERNAL_SERVER_ERROR
                );
            }
        }
    }
}


