package edu.monash.userprojectservice.repository.units;

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
@Table(name = "UNITS", schema = "SPMD")
@IdClass(UnitsPrimaryKey.class)
public class UnitsEntity implements Serializable {

    @Id
    @Column(name = "unit_code")
    private String unitCode; // primary key of the table

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_moodle")
    private String unitMoodle;
}