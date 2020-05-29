package edu.monash.userprojectservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository<ProjectEntity, String> {

    ProjectEntity findProjectEntityByProjectId(String projectId);
}
