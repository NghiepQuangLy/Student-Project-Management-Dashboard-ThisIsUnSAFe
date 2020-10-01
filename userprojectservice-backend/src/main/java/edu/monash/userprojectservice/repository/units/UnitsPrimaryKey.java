package edu.monash.userprojectservice.repository.units;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
public class UnitsPrimaryKey implements Serializable {
    private String unitCode; // primary key of the table
}