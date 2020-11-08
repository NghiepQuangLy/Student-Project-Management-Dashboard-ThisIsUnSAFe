package edu.monash.userprojectservice.repository.userproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersProjectsRepository extends CrudRepository<UsersProjectsEntity, String> {

    List<UsersProjectsEntity> findUsersProjectsEntitiesByEmailAddress(String emailAddress);
    List<UsersProjectsEntity> findUsersProjectsEntitiesByProjectId(String projectId);
    void deleteUsersProjectsEntityByEmailAddressAndProjectId(String emailAddress, String projectId);
}
