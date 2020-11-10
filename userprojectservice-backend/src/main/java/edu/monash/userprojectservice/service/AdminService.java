package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.CheckAdminResponse;
import edu.monash.userprojectservice.repository.admin.AdminEntity;
import edu.monash.userprojectservice.repository.admin.AdminsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminsRepository adminsRepository;


    @Autowired
    private ValidationHandler validationHandler;

    /*
     * This method is used to check if a given email is admin
     * @param emailAddress The email address to be validated
     * @return CheckAdminResponse This returns isAdmin = true if the email is admin, else return isAdmin = false
     * @exception BadRequestException when email is empty
     */
    public CheckAdminResponse checkAdminByEmail(String emailAddress) {
        // check if email is empty
        validationHandler.isEmailNotBlank(emailAddress);

        log.info("{\"message\":\"Checking admin [{}]\"}", emailAddress);

        // get admin entity from database by email address
        AdminEntity adminEntity = adminsRepository.findAdminEntityByEmailAddress(emailAddress);

        // return true if admin entity exist, else return false
        if (adminEntity != null) {
            log.info("{\"message\":\"Got admin [{}]\"}", emailAddress);
            return new CheckAdminResponse(true);
        } else {
            log.info("{\"message\":\"Did not get admin [{}]\"}", emailAddress);
            return new CheckAdminResponse(false);
        }
    }
}
