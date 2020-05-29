package edu.monash.userprojectservice.repository.googledoc;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class GoogleDocPrimaryKey implements Serializable {
    private String googleDocId;
    private String projectId;
}

