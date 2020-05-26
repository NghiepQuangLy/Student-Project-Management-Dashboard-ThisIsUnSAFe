package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.model.CreateUserRequest;
import edu.monash.userprojectservice.repository.ProjectRepository;
import edu.monash.userprojectservice.repository.UserRepository;
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
        Optional<ProjectDetail> projectDetail = projectRepository.findbyProject(projectID);

        ProjectDetail projectResponse = projectDetail.orElse(null);
        if (projectResponse != null){
            System.out.println(projectResponse.getProject_id());
            System.out.println(projectResponse.getProject_name());
        }

        log.info("{\"message\":\"Got project\", \"project\":\"{}\"", projectID);
    }
}
