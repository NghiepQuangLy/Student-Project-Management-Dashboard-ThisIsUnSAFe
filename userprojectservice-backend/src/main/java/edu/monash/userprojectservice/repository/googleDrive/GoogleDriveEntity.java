package edu.monash.userprojectservice.repository.googleDrive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOOGLEDRIVE", schema = "SPMD")
@IdClass(GoogleDriveEntity.class)
public class GoogleDriveEntity implements Serializable {

    @Id
    @Column(name = "drive_id")
    private String googleDriveId;

    @Id
    @Column(name = "project_id")
    private String projectId;

    @Id
    @Column(name = "drive_name")
    private String googleDriveName;
}

