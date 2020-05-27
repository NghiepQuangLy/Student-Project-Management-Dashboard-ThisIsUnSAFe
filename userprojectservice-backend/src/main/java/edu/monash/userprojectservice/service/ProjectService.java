package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<GetProjectResponse> getProject(String emailAddress, int projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        if (!isUserProject(emailAddress, projectId)) {
            System.out.println("Project does not belong to the user.");
            return new ResponseEntity<>(
                    null, OK
            );
        }

        // get from database
        Optional<Project> projectDetail = projectRepository.findbyProject(projectId);

        Project projectResponse = projectDetail.orElse(null);
        if (projectResponse == null){

            System.out.println("Project does not exist.");
            return new ResponseEntity<>(
                    null, OK
            );
        }

        List<Git> gitDetail = gitRepository.findbyProject(projectId);
        List<GoogleDoc> googleDocDetail = googleDocRepository.findbyProject(projectId);
        List<Trello> trelloDetail = trelloRepository.findbyProject(projectId);

        System.out.println(projectResponse.getProjectId());
        System.out.println(projectResponse.getProjectName());

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectId);

        return new ResponseEntity<GetProjectResponse>(
                new GetProjectResponse(String.valueOf(projectResponse.getProjectId()), projectResponse.getProjectName(), gitDetail,
                        googleDocDetail, trelloDetail), OK
        );
    }

    private Boolean isUserProject(String emailAddress, int projectId) {
        List<ProjectShortDetail> userProjects = userRepository.findProjectByEmail(emailAddress);
        System.out.println(userProjects);
        for (ProjectShortDetail userProject : userProjects) {
            System.out.println(String.valueOf(projectId) + userProject.getProject_id());
            if (projectId == Integer.parseInt(userProject.getProject_id())) {
                return true;
            }
        }
        return false;
    }
}
