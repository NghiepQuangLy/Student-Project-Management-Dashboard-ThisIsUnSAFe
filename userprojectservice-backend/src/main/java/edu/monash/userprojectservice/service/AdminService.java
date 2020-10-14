package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.HTTPResponseHandler;
import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.CheckAdminResponse;
import edu.monash.userprojectservice.repository.admin.AdminEntity;
import edu.monash.userprojectservice.repository.admin.AdminsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminsRepository adminsRepository;


    @Autowired
    private ValidationHandler validationHandler;

    public CheckAdminResponse checkAdminByEmail(String emailAddress) {
        if (emailAddress.equals("")) {
            return new CheckAdminResponse(false);
        }

        log.info("{\"message\":\"Checking admin\", \"admin\":\"{}\"}", emailAddress);

        AdminEntity adminEntity = adminsRepository.findAdminEntityByEmailAddress(emailAddress);

        if (adminEntity != null) {
            log.info("{\"message\":\"Got admin\", \"admin\":\"{}\"}", emailAddress);
            return new CheckAdminResponse(true);
        }

        log.info("{\"message\":\"Got no admin\", \"admin\":\"{}\"}", emailAddress);
        return new CheckAdminResponse(false);
    }
}
