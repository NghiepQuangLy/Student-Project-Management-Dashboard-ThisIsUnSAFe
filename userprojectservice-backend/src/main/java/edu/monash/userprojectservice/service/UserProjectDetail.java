package edu.monash.userprojectservice.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class UserProjectDetail {
    private String emailAddress;
    private Integer projectId;
}