package com.freenow.service;


import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.DefaultCarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class DefaultCarServiceTest {

    @InjectMocks
    private DefaultCarService carService;

    @Mock
    private CarRepository carRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findTest() throws EntityNotFoundException {
        when(carRepository.findByIdAndDeleted(4L,false)).thenReturn(createCar());
        Assert.assertEquals(createCar().get(),carService.findCarChecked(4L));
        Assert.assertNotEquals(createCar().get(),carService.findCarChecked(5L));
    }

    @Test
    public void createTest() throws ConstraintsViolationException {
        when(carRepository.save(createCar().get())).thenReturn(createCar().get());
        Assert.assertEquals(createCar().get(),carService.create(createCar().get()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteTest() throws EntityNotFoundException {
        when(carRepository.findByIdAndDeleted(4L,false)).thenReturn(createCar());
        carService.delete(4L);
        Assert.assertTrue(true);
        carService.delete(5L);
    }

    @Test
    public void findByEngineTypeTest() {
        when(carRepository.findByEngineType(EngineType.GAS)).thenReturn(createCarList().get());
        when(carRepository.findByEngineType(EngineType.ELECTRIC)).thenReturn(null);
        Assert.assertEquals(createCarList().get(),carService.find(EngineType.GAS));
        Assert.assertNotEquals(createCarList().get(),carService.find(EngineType.ELECTRIC));
    }

    @Test(expected = EntityNotFoundException.class)
    public void findCarCheckedTest() throws EntityNotFoundException {
        when(carRepository.findByIdAndDeleted(4L,false)).thenReturn(createCar());
        Assert.assertEquals(createCar().get(),carService.findCarChecked(4L));
        Assert.assertNotEquals(createCar().get(),carService.findCarChecked(5L));
    }

    @Test
    public void findCarByExampleTest() throws EntityNotFoundException {
        Example<CarDO> example = Example.of(createCar().get());
        when(carRepository.findAll(example)).thenReturn(createCarList());
        Assert.assertEquals(createCarList().get(),carService.findCarByExample(createCar().get()));
    }

    private Optional<CarDO> createCar() {
        CarDO carDO=new CarDO("34GG9987");
        Optional<CarDO> carDOOptional= Optional.of(carDO);
        return carDOOptional;
    }

    private Optional<List<CarDO>> createCarList() {
        CarDO carDO=createCar().get();
        List<CarDO> carList = Arrays.asList(carDO);
        Optional<List<CarDO>> carDOOptionalList= Optional.of(carList);
        return carDOOptionalList;
    }

}
