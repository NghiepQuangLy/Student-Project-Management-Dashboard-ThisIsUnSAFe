package edu.monash.userprojectservice.service;

import edu.monash.userprojectservice.HTTPResponseHandler;
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
public class UserGroupHelper {

    public UserGroupEnum getUserGroupByEmail(String emailAddress) {

        if (emailAddress.endsWith("@student.monash.edu")){
            return UserGroupEnum.STUDENT;
        } else if (emailAddress.endsWith("@monash.edu")){
            return UserGroupEnum.TUTOR;
        }
        throw new HTTPResponseHandler.BadRequestException("Must provide monash email.");
    }
}
