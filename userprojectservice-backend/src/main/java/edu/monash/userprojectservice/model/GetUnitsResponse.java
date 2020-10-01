package edu.monash.userprojectservice.model;


import edu.monash.userprojectservice.repository.units.UnitsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUnitsResponse {
    private List<UnitsEntity> units; // list of units
}
