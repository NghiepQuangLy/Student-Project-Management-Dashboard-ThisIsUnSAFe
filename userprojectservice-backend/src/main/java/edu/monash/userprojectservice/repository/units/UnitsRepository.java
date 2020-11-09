package edu.monash.userprojectservice.repository.units;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitsRepository extends CrudRepository<UnitEntity, String> {
    List<UnitEntity> findAll();
}
