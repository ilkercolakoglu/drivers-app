package com.freenow.service.car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.driver.DefaultDriverService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCarService implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final CarRepository carRepository;

    public DefaultCarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        return findCarChecked(carId);
    }

    /**
     * Creates a new car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given licensePlate, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        CarDO car;
        try {
            car = carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }

    /**
     * Deletes an existing driver by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
    }

    /**
     * Find all drivers by online state.
     *
     * @param engineType
     */
    @Override
    public List<CarDO> find(EngineType engineType) {
        return carRepository.findByEngineType(engineType);
    }


    /**
     * @param carId
     * @return The car which has been given carId has already in use and can not be deleted.
     * @throws EntityNotFoundException
     */
    @Override
    public CarDO findCarChecked(Long carId) throws EntityNotFoundException {
        return carRepository.findByIdAndDeleted(carId, false)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

    /**
     * @param carDO
     * @return car who are metched with example instance
     * @throws EntityNotFoundException
     */
    @Override
    public List<CarDO> findCarByExample(CarDO carDO) throws EntityNotFoundException {
        carDO.setDateCreated(null);// this field shouldn't be affected on find by example method.
        if (carDO.getManufacturerDO() != null) {
            carDO.getManufacturerDO().setDateCreated(null);// this field shouldn't be affected on find by example method.
        }
        Example<CarDO> example = Example.of(carDO);
        return carRepository.findAll(example)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Could not find entity with id: " + carDO));
    }

}
