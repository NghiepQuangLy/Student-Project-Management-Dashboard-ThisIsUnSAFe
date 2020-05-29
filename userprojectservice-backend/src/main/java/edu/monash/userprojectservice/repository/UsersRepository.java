package edu.monash.userprojectservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, String> {

    UserEntity findUserEntityByEmailAddress(String emailAddress);
}
