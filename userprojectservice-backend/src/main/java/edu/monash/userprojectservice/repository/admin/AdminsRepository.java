package edu.monash.userprojectservice.repository.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends CrudRepository<AdminEntity, String> {

    AdminEntity findAdminEntityByEmailAddress(String emailAddress);
}
