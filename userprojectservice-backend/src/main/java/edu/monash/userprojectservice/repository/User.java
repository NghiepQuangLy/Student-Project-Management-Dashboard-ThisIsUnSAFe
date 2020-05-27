package edu.monash.userprojectservice.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String family_name;
    private String given_name;
    private String email_address;
    private String user_group;
}
