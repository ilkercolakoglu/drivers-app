package com.freenow.service.car;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

import java.util.List;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    List<CarDO> find(EngineType engineType);

    CarDO findCarChecked(Long carId) throws EntityNotFoundException;

    List<CarDO> findCarByExample(CarDO carDO) throws EntityNotFoundException;

}
