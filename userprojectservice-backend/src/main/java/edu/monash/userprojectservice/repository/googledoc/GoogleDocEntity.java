package edu.monash.userprojectservice.repository.googledoc;

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
@Table(name = "GoogleDoc", schema = "SPMD")
@IdClass(GoogleDocEntity.class)
public class GoogleDocEntity implements Serializable {

    @Id
    @Column(name = "document_id")
    private String googleDocId;

    @Id
    @Column(name = "project_id")
    private String projectId;
}

