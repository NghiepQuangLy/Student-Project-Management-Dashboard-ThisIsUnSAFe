package edu.monash.userprojectservice.repository.units;

import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitsRepository extends CrudRepository<UnitsEntity, String> { // not sure if we need String in this case
    List<UnitsEntity> findAll();
}
