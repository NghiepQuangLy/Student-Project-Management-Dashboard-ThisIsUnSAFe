package edu.monash.userprojectservice.repository.git;

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
@Table(name = "GIT", schema = "SPMD")
@IdClass(GitPrimaryKey.class)
public class GitEntity implements Serializable {

    @Id
    @Column(name = "git_id")
    private String gitId;

    @Id
    @Column(name = "project_id")
    private String projectId;

    @Id
    @Column(name = "git_name")
    private String gitName;
}

