package edu.monash.userprojectservice.repository.git;

import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GitRepository extends CrudRepository<GitEntity, String> {

    List<GitEntity> findGitEntitiesByProjectId(String projectId);
}
