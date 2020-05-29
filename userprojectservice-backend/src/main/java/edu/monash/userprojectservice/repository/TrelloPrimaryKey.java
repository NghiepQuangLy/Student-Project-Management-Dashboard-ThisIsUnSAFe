package edu.monash.userprojectservice.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class TrelloPrimaryKey implements Serializable {
    private String trelloId;
    private String projectId;
}

