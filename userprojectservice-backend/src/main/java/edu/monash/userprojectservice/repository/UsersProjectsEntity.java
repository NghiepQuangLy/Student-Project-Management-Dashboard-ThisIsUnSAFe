package edu.monash.userprojectservice.repository;

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
@Table(name = "Users_Projects", schema = "SPMD")
@IdClass(UsersProjectsId.class)
public class UsersProjectsEntity implements Serializable {

    @Id
    @Column(name = "email_address")
    private String emailAddress;

    @Id
    @Column(name = "project_id")
    private String projectId;

    @ManyToOne
    @JoinColumn(name = "email_address", updatable = false, insertable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "project_id", updatable = false, insertable = false)
    private ProjectEntity projectEntity;

    public String getEmailAddress() {
        return userEntity.getEmailAddress();
    }

    public String getProjectId() {
        return projectEntity.getProjectId();
    }
}

