package edu.monash.userprojectservice;

import edu.monash.userprojectservice.repository.admin.AdminEntity;
import edu.monash.userprojectservice.repository.admin.AdminsRepository;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsEntity;
import edu.monash.userprojectservice.repository.userproject.UsersProjectsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ValidationHandler {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private AdminsRepository adminsRepository;

    public Boolean isValid(String emailAddress, String projectId) {

        // Blank Check
        isNotBlank(emailAddress, projectId);

        // Unauthorised check
        isUserProject(emailAddress, projectId);

        return true;
    }

    private Boolean isUserProject(String emailAddress, String projectId) {
        List<UsersProjectsEntity> userProjects = usersProjectsRepository.findUsersProjectsEntitiesByEmailAddress(emailAddress);

        for (UsersProjectsEntity userProject : userProjects) {

            if (projectId.equals(userProject.getProjectId())) {
                return true;
            }
        }
        System.out.println("Project does not belong to the user.");
        throw new HTTPResponseHandler.ForbiddenException();
    }

    private Boolean isNotBlank(String emailAddress, String projectId) {
        if (projectId.equals("")) {
            throw new HTTPResponseHandler.BadRequestException("Project id can not be empty");
        }
        if (emailAddress.equals("")) {
            throw new HTTPResponseHandler.BadRequestException("Email address can not be empty");
        }
        return true;
    }

    public Boolean isUserAdmin(String emailAddress) {

        if (emailAddress.equals("")) {
            throw new HTTPResponseHandler.BadRequestException("Email address can not be empty");
        }

        AdminEntity adminEntity = adminsRepository.findAdminEntityByEmailAddress(emailAddress);

        if (adminEntity != null) {
            return true;
        }

        System.out.println("User is not admin.");
        throw new HTTPResponseHandler.ForbiddenException();
    }

    public void isMonashEmail(String emailAddress) {
        if (!emailAddress.endsWith("@student.monash.edu") && !emailAddress.endsWith("@monash.edu")) {
            log.warn("{\"message\":\"Must provide monash email.\"}");
            throw new HTTPResponseHandler.BadRequestException("Must provide monash email.");
        }
    }

    /*
     * This method is used to check if a given email is blank
     * @param emailAddress The email address to be validated
     * @exception BadRequestException when email is empty
     */
    public void isEmailNotBlank(String emailAddress) {
        if (emailAddress.equals("")) {
            throw new HTTPResponseHandler.BadRequestException("Email address must not be blank");
        }
    }
}
