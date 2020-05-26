package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.repository.Project;
import edu.monash.userprojectservice.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void getProject(int projectID) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectID);

        // get from database
        Optional<Project> projectDetail = projectRepository.findbyProject(projectID);

        Project projectResponse = projectDetail.orElse(null);
        if (projectResponse != null){
            System.out.println(projectResponse.getProject_id());
            System.out.println(projectResponse.getProject_name());
        }

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectID);
    }
}
