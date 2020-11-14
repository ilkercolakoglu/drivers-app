package com.freenow.service.driver;

import com.freenow.datatransferobject.CustomSearchDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;

import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    void selectCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    void deselectCar(long driverId, long carId) throws EntityNotFoundException, CarNotSelectedByDriverException;

    List<DriverDO> findByCustomSearchAndCar(CustomSearchDTO customSearchDTO, CarDO carDO)
            throws EntityNotFoundException, DriverNotFoundException;

}
