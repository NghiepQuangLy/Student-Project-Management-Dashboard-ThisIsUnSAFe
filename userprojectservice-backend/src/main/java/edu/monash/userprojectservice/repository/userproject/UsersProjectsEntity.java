package edu.monash.userprojectservice.repository.userproject;

import edu.monash.userprojectservice.repository.project.ProjectEntity;
import edu.monash.userprojectservice.repository.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS_PROJECTS", schema = "SPMD")
@IdClass(UsersProjectsPrimaryKey.class)
public class UsersProjectsEntity implements Serializable {

    @Id
    @Column(name = "email_address")
    private String emailAddress;

    @Id
    @Column(name = "project_id")
    private String projectId;

    @ManyToOne
    @JoinColumn(name = "project_id", updatable = false, insertable = false)
    private ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn(name = "email_address", updatable = false, insertable = false)
    private UserEntity userEntity;
}

