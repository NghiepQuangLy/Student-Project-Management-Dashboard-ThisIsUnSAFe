package edu.monash.userprojectservice.repository.googleFolder;

import edu.monash.userprojectservice.repository.googleFolder.GoogleFolderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoogleFolderRepository extends CrudRepository<GoogleFolderEntity, String> {

    List<GoogleFolderEntity> findGoogleFolderEntitiesByProjectId(String projectId);
}
