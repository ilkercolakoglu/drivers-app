package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<CarDO, Long> {

    Optional<List<CarDO>> findAll(Example<CarDO> carDOExample);

    List<CarDO> findByEngineType(EngineType engineType);

    Optional<CarDO> findByIdAndDeleted(Long carId, Boolean deleted);

}
