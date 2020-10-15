package edu.monash.userprojectservice.repository.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends CrudRepository<ProjectEntity, String> {

    ProjectEntity findProjectEntityByProjectId(String projectId);

    List<ProjectEntity> findAll();
}
