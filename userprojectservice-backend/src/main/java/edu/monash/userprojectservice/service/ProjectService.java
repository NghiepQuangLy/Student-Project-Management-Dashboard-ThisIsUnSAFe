package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.model.GetTimesheet;
import edu.monash.userprojectservice.model.SaveTimesheetRequest;
import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderEntity;
import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderRepository;
import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.CreateProjectRepository;
import edu.monash.userprojectservice.repository.git.GitEntity;
import edu.monash.userprojectservice.repository.git.GitRepository;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveRepository;
import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import edu.monash.userprojectservice.repository.trello.TrelloRepository;
import edu.monash.userprojectservice.repository.user.UsersRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private CreateProjectRepository createProjectRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private GoogleDriveRepository googleDriveRepository;

    @Autowired
    private GoogleFolderRepository googleFolderRepository;

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

        List<GitEntity> gitEntities = gitRepository.findGitEntitiesByProjectId(projectId);
        List<GoogleDriveEntity> googleDriveEntities = googleDriveRepository.findGoogleDriveEntitiesByProjectId(projectId);
        List<GoogleFolderEntity> googleFolderEntities = googleFolderRepository.findGoogleFolderEntitiesByProjectId(projectId);
        List<TrelloEntity> trelloDetail = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        System.out.println(projectEntity.getProjectId());
        System.out.println(projectEntity.getProjectName());

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"}", projectId);

        return new ResponseEntity<GetProjectResponse>(
                new GetProjectResponse(
                        String.valueOf(projectEntity.getProjectId()),
                        projectEntity.getProjectName(),
                        gitEntities.stream().map(GitEntity::getGitId).collect(Collectors.toList()),
                        googleDriveEntities.stream().map(GoogleDriveEntity::getGoogleDriveId).collect(Collectors.toList()),
                        googleFolderEntities.stream().map(GoogleFolderEntity::getGoogleFolderId).collect(Collectors.toList()),
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
    public ResponseEntity<GetProjectResponse> createProject(List<String> emailAddress, String projectName) throws SQLException {
        //check if the user is present in the system
        if (usersRepository.findUserEntityByEmailAddress(emailAddress.get(0)) == null) {
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
            Boolean isSuccessful = createProjectRepository.save(projectId, emailAddress, projectName);
            if (isSuccessful) {
                log.info("Project has been added!");
                return new ResponseEntity<>(
                        null, CREATED
                );
            } else {
                log.warn("Project could not be added!");
                return new ResponseEntity<>(
                        null, INTERNAL_SERVER_ERROR
                );
            }
        }
    }

    public GetTimesheet getTimesheet(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        if (!isUserProject(emailAddress, projectId)) {
            System.out.println("Project does not belong to the user.");
            // Unauthorised error
            return null;
            // if project is null, 404 not found
        }

        // get from database
        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(projectId);

        if (projectEntity == null) {

            System.out.println("Project does not exist.");
            return null;
        }

        return new GetTimesheet(projectEntity.getTimesheet());
    }

    public void saveTimesheet(SaveTimesheetRequest saveTimesheetRequest) {
        log.info("{\"message\":\"Inserting timesheet\", \"project\":\"{}\"}", saveTimesheetRequest);

        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(saveTimesheetRequest.getProjectId());
        projectEntity.setTimesheet(saveTimesheetRequest.getTimesheet());

        // Store into database
        projectsRepository.save(projectEntity);

        log.info("{\"message\":\"Inserted timesheet\", \"project\":\"{}\"}", saveTimesheetRequest);
    }
}


