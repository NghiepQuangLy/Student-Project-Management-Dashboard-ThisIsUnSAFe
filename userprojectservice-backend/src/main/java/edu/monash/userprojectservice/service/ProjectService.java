package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.CreateProjectRequest;
import edu.monash.userprojectservice.model.EditProjectRequest;
import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.model.GetTimesheetResponse;
import edu.monash.userprojectservice.model.IntegrationObjectResponse;
import edu.monash.userprojectservice.model.IntegrationTableObjectResponse;
import edu.monash.userprojectservice.model.RemoveTimesheetRequest;
import edu.monash.userprojectservice.model.SaveTimesheetRequest;
import edu.monash.userprojectservice.repository.CreateProjectRepository;
import edu.monash.userprojectservice.repository.EditProjectRepository;
import edu.monash.userprojectservice.repository.git.GitEntity;
import edu.monash.userprojectservice.repository.git.GitRepository;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveRepository;
import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderEntity;
import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderRepository;
import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.project.ProjectsRepository;
import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import edu.monash.userprojectservice.repository.trello.TrelloRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import edu.monash.userprojectservice.serviceclient.GitIntegrationTableServiceClient;
import edu.monash.userprojectservice.serviceclient.IntegrationTableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private CreateProjectRepository createProjectRepository;

    @Autowired
    private EditProjectRepository editProjectRepository;

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
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private ValidationHandler validationHandler;

    @Autowired
    private GitIntegrationTableServiceClient gitIntegrationTableServiceClient;

    public ResponseEntity<GetProjectResponse> getProject(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);

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
        List<TrelloEntity> trelloEntities = trelloRepository.findTrelloEntitiesByProjectId(projectId);

        // get all the users of the project
        List<UsersProjectsEntity> usersProjectsEntities = usersProjectsRepository.findUsersProjectsEntitiesByProjectId(projectId);

        // get list of emails that user group is belong to student
        List<String> emails = usersProjectsEntities.stream()
                .filter(usersProjectsEntity -> usersProjectsEntity.getUserEntity().getUserGroup().equals("STUDENT"))
                .map(UsersProjectsEntity::getEmailAddress).collect(Collectors.toList());

        // get git activity tracker table data
        List<IntegrationTableResponse> gitIntegrationTableData = gitIntegrationTableServiceClient.getGitIntegrationTable(
                emails,
                gitEntities.stream().map(GitEntity::getGitId).collect(Collectors.toList())
        );


        log.info("{\"message\":\"Got project\", \"project\":\"{}\"}", projectId);

        return new ResponseEntity(
                new GetProjectResponse(
                        String.valueOf(projectEntity.getProjectId()),
                        projectEntity.getProjectName(),
                        projectEntity.getUnitCode(),
                        projectEntity.getProjectYear(),
                        projectEntity.getProjectSemester(),
                        projectEntity.getProjectTimesheet(),
                        gitEntities.stream()
                                .map(gitEntity -> new IntegrationObjectResponse(gitEntity.getGitId(), gitEntity.getGitName()))
                                .collect(Collectors.toList()),
                        googleDriveEntities.stream()
                                .map(googleDriveEntity -> new IntegrationObjectResponse(googleDriveEntity.getGoogleDriveId(), googleDriveEntity.getGoogleDriveName()))
                                .collect(Collectors.toList()),
                        googleFolderEntities.stream()
                                .map(GoogleFolderEntity::getGoogleFolderId)
                                .collect(Collectors.toList()),
                        trelloEntities.stream()
                                .map(trelloEntity -> new IntegrationObjectResponse(trelloEntity.getTrelloId(), trelloEntity.getTrelloName()))
                                .collect(Collectors.toList()),
                        emails.stream()
                                .map(email -> createIntegrationTableObjectResponse(email, gitIntegrationTableData, null, null))
                                .collect(Collectors.toList())
                ), OK
        );
    }

    private IntegrationTableObjectResponse createIntegrationTableObjectResponse(
            String email,
            List<IntegrationTableResponse> gitIntegrationTableData,
            List<IntegrationTableResponse> googleDriveIntegrationTableData,
            List<IntegrationTableResponse> trelloIntegrationTableData
    ) {
        return IntegrationTableObjectResponse.builder()
                .emailAddress(email)
                .gitIntegrationLastModified(getIntegrationLastModifiedString(email, gitIntegrationTableData))
                .googleDriveIntegrationLastModified(getIntegrationLastModifiedString(email, googleDriveIntegrationTableData))
                .trelloIntegrationLastModified(getIntegrationLastModifiedString(email, trelloIntegrationTableData))
                .build();
    }

    private String getIntegrationLastModifiedString(String email, List<IntegrationTableResponse> integrationTableData) {
        if (integrationTableData == null) {
            return "N/A";
        }
        LocalDateTime lastModifiedDate = integrationTableData.stream()
                .filter(data -> data.getEmailAddress().equals(email))
                .map(IntegrationTableResponse::getLastModified)
                .findFirst()
                .orElse(null);
        if (lastModifiedDate == null) {
            return "Unavailable";
        }

        LocalDateTime today = LocalDateTime.now();
        long diff = DAYS.between(lastModifiedDate, today);

        String diffDaysString = diff > 1 ? " days ago" : " day ago";

        return diff + diffDaysString;
    }

    // create a method
    // check for the user first, if it doesnt exist new responseEntity and return not found
    // if he exists then, return OK
    public ResponseEntity<GetProjectResponse> createProject(CreateProjectRequest createProjectRequest) throws SQLException {
        //check if the user is present in the system

        String projectId = UUID.randomUUID().toString();
        // check if the project is already present in the database
        while (projectsRepository.findProjectEntityByProjectId(projectId) != null) {
            projectId = UUID.randomUUID().toString();
        }
        // insert into db when project doesnt exist in the db
        Boolean isSuccessful = createProjectRepository.save(projectId, createProjectRequest.getEmailAddress(), createProjectRequest.getProjectName(), createProjectRequest.getProjectUnit(), createProjectRequest.getProjectYear(), createProjectRequest.getProjectSemester());
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

    // edit a project
    // check for the user first, if it doesnt exist new responseEntity and return not found
    // if he exists then, return OK
    public ResponseEntity<GetProjectResponse> editProject(EditProjectRequest editProjectRequest) throws SQLException {

        // check if the project is already present in the database
        if (projectsRepository.findProjectEntityByProjectId(editProjectRequest.getProjectId()) == null) {
            log.warn("Project not found!");
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }

        // edit in db when project exists
        Boolean isSuccessful = editProjectRepository.save(editProjectRequest.getProjectId(), editProjectRequest.getNewProjectName());
        if (isSuccessful) {
            log.info("Project has been edited!");
            return new ResponseEntity<>(
                    null, OK
            );
        } else {
            log.warn("Project could not be edited!");
            return new ResponseEntity<>(
                    null, INTERNAL_SERVER_ERROR
            );
        }
    }

    public GetTimesheetResponse getTimesheet(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        // Validation Check
        validationHandler.isValid(emailAddress, projectId);

        // get from database
        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(projectId);

        if (projectEntity == null) {

            System.out.println("Project does not exist.");
            return null;
        }

        return new GetTimesheetResponse(projectEntity.getProjectTimesheet());
    }

    public void saveTimesheet(SaveTimesheetRequest saveTimesheetRequest) {
        log.info("{\"message\":\"Inserting timesheet\", \"project\":\"{}\"}", saveTimesheetRequest);

        // Validation Check
        validationHandler.isValid(saveTimesheetRequest.getEmailAddress(), saveTimesheetRequest.getProjectId());

        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(saveTimesheetRequest.getProjectId());
        projectEntity.setTimesheet(saveTimesheetRequest.getTimesheet());

        // Store into database
        projectsRepository.save(projectEntity);

        log.info("{\"message\":\"Inserted timesheet\", \"project\":\"{}\"}", saveTimesheetRequest);
    }

    public void removeTimesheet(RemoveTimesheetRequest removeTimesheetRequest) {
        log.info("{\"message\":\"Removing timesheet\", \"project\":\"{}\"}", removeTimesheetRequest);

        // Validation Check
        validationHandler.isValid(removeTimesheetRequest.getEmailAddress(), removeTimesheetRequest.getProjectId());

        ProjectEntity projectEntity = projectsRepository.findProjectEntityByProjectId(removeTimesheetRequest.getProjectId());
        projectEntity.removeTimesheet(removeTimesheetRequest.getTimesheet());

        // Delete from database
        projectsRepository.save(projectEntity);
        log.info("{\"message\":\"Removed timesheet\", \"project\":\"{}\"}", removeTimesheetRequest);
    }
}


