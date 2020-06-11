package edu.monash.userprojectservice.repository.googleDrive;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoogleDriveRepository extends CrudRepository<GoogleDriveEntity, String> {

    List<GoogleDriveEntity> findGoogleDriveEntitiesByProjectId(String projectId);
}
