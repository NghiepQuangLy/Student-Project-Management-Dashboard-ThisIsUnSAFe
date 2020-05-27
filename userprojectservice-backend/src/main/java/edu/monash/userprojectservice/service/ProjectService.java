package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.GetProjectResponse;
import edu.monash.userprojectservice.repository.Project;
import edu.monash.userprojectservice.repository.ProjectRepository;
import edu.monash.userprojectservice.repository.GitRepository;
import edu.monash.userprojectservice.repository.GoogleDocRepository;
import edu.monash.userprojectservice.repository.TrelloRepository
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<GetProjectResponse> getProject(int projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        // get from database
        Optional<Project> projectDetail = projectRepository.findbyProject(projectId);

        Project projectResponse = projectDetail.orElse(null);
        if (projectResponse == null){

            System.out.println("Project does not exist.");
            return new ResponseEntity<>(
                    null, OK
            );
        }

        System.out.println(projectResponse.getProjectId());
        System.out.println(projectResponse.getProjectName());
        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectId);

        return new ResponseEntity<GetProjectResponse>(
                new GetProjectResponse(projectResponse.getProjectId(), projectResponse.getProjectName()), OK
        );
    }
}
