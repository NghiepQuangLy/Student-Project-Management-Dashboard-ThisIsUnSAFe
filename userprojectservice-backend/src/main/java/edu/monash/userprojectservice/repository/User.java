package edu.monash.userprojectservice.repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    private String family_Name;
    private String given_name;
    private String email_address;
    private String user_group;
}
