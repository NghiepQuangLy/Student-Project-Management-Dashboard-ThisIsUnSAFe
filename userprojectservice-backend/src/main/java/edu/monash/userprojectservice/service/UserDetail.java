package edu.monash.userprojectservice.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetail {
    private String family_name;
    private String given_name;
    private String email_address;
    private String user_group;
}
