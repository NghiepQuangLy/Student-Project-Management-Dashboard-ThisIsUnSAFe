package edu.monash.userprojectservice.repository.trello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrelloRepository extends CrudRepository<TrelloEntity, String> {

    List<TrelloEntity> findTrelloEntitiesByProjectId(String projectId);
}
