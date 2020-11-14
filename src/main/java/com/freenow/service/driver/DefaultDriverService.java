package com.freenow.service.driver;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.datatransferobject.CustomSearchDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;

import java.util.ArrayList;
import java.util.List;

import com.freenow.service.car.CarService;
import com.freenow.util.Consts;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    private final CarService carService;


    public DefaultDriverService(final DriverRepository driverRepository, final CarService carService) {
        this.driverRepository = driverRepository;
        this.carService = carService;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException {
        DriverDO driver;
        try {
            driver = driverRepository.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus) {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    /**
     * Select car which has carId for driver who has driverId
     *
     * @param driverId
     * @param carId
     */
    @Override
    @Transactional
    public void selectCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException {
        DriverDO driverDO = findDriverChecked(driverId);
        if (driverDO.getCarDO() != null) {
            driverDO.getCarDO().setDriverDO(null); //if driver has a car, car should be released.
        }
        CarDO carDO = carService.findCarChecked(carId);
        if (carDO.getDriverDO() != null) {
            throw new CarAlreadyInUseException(Consts.CAR_ALREADY_IN_USE_EXCEPTION);
        }
        driverDO.setCarDO(carDO);
        carDO.setDriverDO(driverDO);
    }

    /**
     * Deselect car for driver who has driverId
     *
     * @param driverId
     */
    @Override
    @Transactional
    public void deselectCar(long driverId, long carId) throws EntityNotFoundException, CarNotSelectedByDriverException {
        DriverDO driverDO = findDriverChecked(driverId);
        CarDO carChecked = carService.findCarChecked(carId);
        if (carChecked == null) {
            throw new EntityNotFoundException(Consts.CAR_NOT_AVAILABLE_EXCEPTION);
        }
        CarDO carDO = driverDO.getCarDO();
        if (carDO == null || carId != carDO.getId()) {
            throw new CarNotSelectedByDriverException(Consts.CAR_NOT_SELECTED_EXCEPTION);
        }
        driverDO.setCarDO(null);
        carDO.setDriverDO(null);
    }

    /**
     * @param customSearchDTO
     * @param carDO
     * @return drivers who are matched with example instance
     * @throws EntityNotFoundException
     */
    @Override
    public List<DriverDO> findByCustomSearchAndCar(CustomSearchDTO customSearchDTO,
                                                   CarDO carDO) throws EntityNotFoundException, DriverNotFoundException {
        List<DriverDO> driverList = new ArrayList<>();
        DriverDO driverDO = new DriverDO(customSearchDTO.getUsername(),
                customSearchDTO.getOnlineStatus());
        // if car instance is available
        if (carDO != null) {
            List<CarDO> carList = carService.findCarByExample(carDO);
            for (CarDO car : carList) {
                driverDO.setCarDO(car);
                car.setDriverDO(null); // from root DriverDO must not span a cyclic property reference
                Example<DriverDO> example = Example.of(driverDO);
                List<DriverDO> matchedDrivers = driverRepository.findAll(example).orElseThrow(()
                        -> new DriverNotFoundException(Consts.COULD_NOT_FIND_ANY_DRIVER));
                driverList.addAll(matchedDrivers);
            }
        } else { // if any car instance is not available
            Example<DriverDO> example = Example.of(driverDO);
            List<DriverDO> matchedDrivers = driverRepository.findAll(example).orElseThrow(()
                    -> new DriverNotFoundException(Consts.COULD_NOT_FIND_ANY_DRIVER));
            driverList.addAll(matchedDrivers);
        }

        return driverList;
    }


    /**
     * @param driverId
     * @return driver who is given driverId and not deleted.
     * @throws EntityNotFoundException
     */
    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException {
        return driverRepository.findByIdAndDeleted(driverId, false)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
