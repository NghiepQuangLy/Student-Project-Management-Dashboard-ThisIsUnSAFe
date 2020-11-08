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

    /*
     * This method is used to check if a given email has access permission to a given project belongs
     * @param emailAddress The email address to be validated
     * @param projectId The project id to be validated
     * @exception BadRequestException when email is empty or not monash email, when project id is empty,
     * @exception ForbiddenException when project does not belong to the email
     */
    public void isUserOwnProject(String emailAddress, String projectId) {

        // Validation Check
        isProjectNotBlank(projectId);
        isEmailNotBlank(emailAddress);
        isMonashEmail(emailAddress);

        // Authorisation check
        List<UsersProjectsEntity> userProjects = usersProjectsRepository.findUsersProjectsEntitiesByEmailAddress(emailAddress);
        UsersProjectsEntity usersProject = userProjects.stream()
                .filter(usersProjectsEntity -> usersProjectsEntity.getProjectId().equals(projectId))
                .findFirst()
                .orElse(null);

        if (usersProject == null) {
            log.warn("{\"message\":\"User [{}] does not have permission to access the project [{}].\"}", emailAddress, projectId);
            throw new HTTPResponseHandler.ForbiddenException("User does not have permission to access the project");
        }
    }

    /*
     * This method is used to check if a given email is an admin
     * @param emailAddress The email address to be validated
     * @exception BadRequestException when email is empty
     * @exception ForbiddenException when email is not admin
     */
    public void isUserAdmin(String emailAddress) {

        // Validation Check
        isEmailNotBlank(emailAddress);

        // Authorisation check
        AdminEntity adminEntity = adminsRepository.findAdminEntityByEmailAddress(emailAddress);
        if (adminEntity == null) {
            log.warn("{\"message\":\"User [{}] is not admin.\"}", emailAddress);
            throw new HTTPResponseHandler.ForbiddenException("User is not admin.");
        }
    }

    /*
     * This method is used to check if a given email is monash email
     * @param emailAddress The email address to be validated
     * @exception BadRequestException when email is not monash email
     */
    public void isMonashEmail(String emailAddress) {
        if (!emailAddress.endsWith("@student.monash.edu") && !emailAddress.endsWith("@monash.edu")) {
            log.warn("{\"message\":\"{} is not monash email.\"}", emailAddress);
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
            log.warn("{\"message\":\"Email address must not be empty.\"}");
            throw new HTTPResponseHandler.BadRequestException("Email address must not be empty.");
        }
    }

    /*
     * This method is used to check if a given project id is blank
     * @param projectId The project id to be validated
     * @exception BadRequestException when project id is empty
     */
    private void isProjectNotBlank(String projectId) {
        if (projectId.equals("")) {
            log.warn("{\"message\":\"Project id must not be empty.\"}");
            throw new HTTPResponseHandler.BadRequestException("Project id must not be empty.");
        }
    }
}
