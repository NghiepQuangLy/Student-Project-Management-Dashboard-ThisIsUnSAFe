package edu.monash.userprojectservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersProjectsRepository extends CrudRepository<UsersProjectsEntity, String> {

    List<UsersProjectsEntity> findUsersProjectsEntitiesByEmailAddress(String emailAddress);
}
