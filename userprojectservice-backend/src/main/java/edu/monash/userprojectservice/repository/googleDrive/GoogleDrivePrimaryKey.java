package edu.monash.userprojectservice.repository.googleDrive;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class GoogleDrivePrimaryKey implements Serializable {
    private String googleDriveId;
    private String projectId;
}

