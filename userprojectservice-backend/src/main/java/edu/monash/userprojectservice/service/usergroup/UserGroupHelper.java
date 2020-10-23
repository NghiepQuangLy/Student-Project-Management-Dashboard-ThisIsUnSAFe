package edu.monash.userprojectservice.service.usergroup;

import edu.monash.userprojectservice.HTTPResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserGroupHelper {

    public UserGroupEnum getUserGroupByEmail(String emailAddress) {

        if (emailAddress.endsWith("@student.monash.edu")){
            return UserGroupEnum.STUDENT;
        } else if (emailAddress.endsWith("@monash.edu")){
            return UserGroupEnum.TUTOR;
        }
        log.warn("{\"message\":\"Must provide monash email.\"}");
        throw new HTTPResponseHandler.BadRequestException("Must provide monash email.");
    }
}
