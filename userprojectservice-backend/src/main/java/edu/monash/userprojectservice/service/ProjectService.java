package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.UUID;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private GitRepository gitRepository;
    @Autowired
    private GoogleDocRepository googleDocRepository;
    @Autowired
    private TrelloRepository trelloRepository;
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<GetProjectResponse> getProject(String emailAddress, String projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        if (!isUserProject(emailAddress, projectId)) {
            System.out.println("Project does not belong to the user.");
            return new ResponseEntity<>(
                    null, OK
            );
        }

        // get from database
        Project projectDetail = projectRepository.findbyProject(projectId);

        if (projectDetail == null){

            System.out.println("Project does not exist.");
            return new ResponseEntity<>(
                    null, OK
            );
        }

        List<Git> gitDetail = gitRepository.findbyProject(projectId);
        List<GoogleDoc> googleDocDetail = googleDocRepository.findbyProject(projectId);
        List<Trello> trelloDetail = trelloRepository.findbyProject(projectId);

        System.out.println(projectDetail.getProjectId());
        System.out.println(projectDetail.getProjectName());

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectId);

        return new ResponseEntity<GetProjectResponse>(
                new GetProjectResponse(String.valueOf(projectDetail.getProjectId()), projectDetail.getProjectName(), gitDetail,
                        googleDocDetail, trelloDetail), OK
        );
    }

    private Boolean isUserProject(String emailAddress, String projectId) {
        List<ProjectShortDetail> userProjects = userRepository.findProjectByEmail(emailAddress);
        System.out.println(userProjects);
        for (ProjectShortDetail userProject : userProjects) {
            System.out.println(String.valueOf(projectId) + userProject.getProject_id());
            if (projectId.equals(userProject.getProject_id())) {
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
       if (userRepository.findUserByEmail(emailAddress)==null){
           log.warn("User doesn't exist in the Database!");
           return new ResponseEntity<>(
                   null,NOT_FOUND
           );
       }
       else {
           String projectID = UUID.randomUUID().toString();
           // check if the project is already present in the database
           while (projectRepository.findbyProject(projectID)!=null) {
               projectID = UUID.randomUUID().toString();
           }
           // insert into db when project doesnt exist in the db
           Boolean isSuccessful = projectRepository.insertProject(projectID, emailAddress, projectName);
           if (isSuccessful) {
               log.info("Project has been added!");
               return new ResponseEntity<>(
                       null, OK
               );
           }
           else{
               log.warn("Project could not be added!");
               return new ResponseEntity<>(
                       null, INTERNAL_SERVER_ERROR
               );
           }
       }
   }
}


