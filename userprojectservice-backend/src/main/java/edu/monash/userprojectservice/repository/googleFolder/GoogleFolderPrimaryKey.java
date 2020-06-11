package edu.monash.userprojectservice.repository.googleFolder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class GoogleFolderPrimaryKey implements Serializable {
    private String googleFolderId;
    private String projectId;
}

