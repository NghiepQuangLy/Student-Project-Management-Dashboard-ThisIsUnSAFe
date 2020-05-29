package edu.monash.userprojectservice.repository.git;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class GitPrimaryKey implements Serializable {
    private String gitId;
    private String projectId;
}

