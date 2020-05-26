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

    public void getProject(int projectId) {
        log.info("{\"message\":\"Getting project\", \"project\":\"{}\"}", projectId);

        // get from database
        Optional<Project> projectDetail = projectRepository.findbyProject(projectId);

        Project projectResponse = projectDetail.orElse(null);
        if (projectResponse != null){
            System.out.println(projectResponse.getProjectId());
            System.out.println(projectResponse.getProjectName());
        }

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectId);
    }
}
