package edu.monash.userprojectservice.repository.googleFolder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOOGLEFOLDER", schema = "SPMD")
@IdClass(GoogleFolderEntity.class)
public class GoogleFolderEntity implements Serializable {

    @Id
    @Column(name = "folder_id")
    private String googleFolderId;

    @Id
    @Column(name = "project_id")
    private String projectId;
}

