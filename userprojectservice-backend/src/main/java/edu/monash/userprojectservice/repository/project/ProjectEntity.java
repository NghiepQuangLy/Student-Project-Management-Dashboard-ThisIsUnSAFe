package edu.monash.userprojectservice.repository.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PROJECTS", schema = "SPMD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity implements Serializable {
    @Id
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "timesheet")
    private String timesheet;

    @Column(name = "unit_code")
    private String unitCode;

    @Column(name = "project_year")
    private String projectYear;

    @Column(name = "project_semester")
    private String projectSemester;

    public void setTimesheet(String timesheet) {
        this.timesheet = timesheet;
    }
}
