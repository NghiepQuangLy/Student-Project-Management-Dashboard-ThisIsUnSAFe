package edu.monash.userprojectservice.repository.googledoc;

import edu.monash.userprojectservice.repository.git.GitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoogleDocRepository extends CrudRepository<GoogleDocEntity, String> {

    List<GoogleDocEntity> findGoogleDocEntitiesByProjectId(String projectId);
}
