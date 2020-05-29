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
@Table(name = "Trello", schema = "SPMD")
@IdClass(TrelloPrimaryKey.class)
public class TrelloEntity implements Serializable {

    @Id
    @Column(name = "trello_id")
    private String trelloId;

    @Id
    @Column(name = "project_id")
    private String projectId;
}

