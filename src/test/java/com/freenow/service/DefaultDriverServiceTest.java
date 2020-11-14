package com.freenow.service;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.datatransferobject.CustomSearchDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;
import com.freenow.service.car.CarService;
import com.freenow.service.driver.DefaultDriverService;
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

public class DefaultDriverServiceTest {

    @InjectMocks
    private DefaultDriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private CarService carService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findTest() throws EntityNotFoundException {
        when(driverRepository.findByIdAndDeleted(4L,false)).thenReturn(createDriver());
        Assert.assertEquals(createDriver().get(),driverService.find(4L));
        Assert.assertNotEquals(createDriver().get(),driverService.find(5L));
    }

    @Test
    public void createTest() throws ConstraintsViolationException {
        when(driverRepository.save(createDriver().get())).thenReturn(createDriver().get());
        Assert.assertEquals(createDriver().get(),driverService.create(createDriver().get()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteTest() throws EntityNotFoundException {
        when(driverRepository.findByIdAndDeleted(4L,false)).thenReturn(createDriver());
        driverService.delete(4L);
        Assert.assertTrue(true);
        driverService.delete(5L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateLocationTest() throws EntityNotFoundException{
        when(driverRepository.findByIdAndDeleted(4L,false)).thenReturn(createDriver());
        driverService.updateLocation(4L,34.78,-23.95);
        Assert.assertTrue(true);
        driverService.updateLocation(5L,24.18,-63.55);
    }

    @Test
    public void findByEngineTypeTest() {
        when(driverRepository.findByOnlineStatus(OnlineStatus.ONLINE)).thenReturn(createDriverList().get());
        when(driverRepository.findByOnlineStatus(OnlineStatus.OFFLINE)).thenReturn(null);
        Assert.assertEquals(createDriverList().get(),driverService.find(OnlineStatus.ONLINE));
        Assert.assertNotEquals(createDriverList().get(),driverService.find(OnlineStatus.OFFLINE));
    }


    @Test(expected = CarAlreadyInUseException.class)
    public void selectCarTest() throws EntityNotFoundException, CarAlreadyInUseException {
        Optional<DriverDO> driver01 = createDriver();
        when(driverRepository.findByIdAndDeleted(3L,false)).thenReturn(driver01);
        when(carService.findCarChecked(9L)).thenReturn(createCar().get());
        driverService.selectCar(3L,9L);
        Assert.assertTrue(true);
        driver01.get().setId(7L);
        when(driverRepository.findByIdAndDeleted(7L,false)).thenReturn(driver01);
        when(carService.findCarChecked(3L)).thenReturn(createCarInUse().get());
        driverService.selectCar(7L,3L);
    }

    @Test(expected = CarNotSelectedByDriverException.class)
    public void deselectCarTest() throws EntityNotFoundException, CarNotSelectedByDriverException, CarAlreadyInUseException {
        when(driverRepository.findByIdAndDeleted(4L,false)).thenReturn(createDriver());
        when(carService.findCarChecked(4L)).thenReturn(createCar().get());
        when(carService.findCarChecked(5L)).thenReturn(createCar().get());
        driverService.selectCar(4L,4L);
        driverService.deselectCar(4,4);
        Assert.assertTrue(true);
        driverService.deselectCar(4,5);
    }

    @Test(expected = DriverNotFoundException.class)
    public void findByCustomSearchAndCarTest() throws EntityNotFoundException, DriverNotFoundException {
        when(carService.findCarByExample(createCar().get())).thenReturn(createCarList().get());
        DriverDO driver=createDriver().get();
        Example<DriverDO> example=Example.of(driver);
        when(driverRepository.findAll(example)).thenReturn(createDriverList());
        CustomSearchDTO customSearchDTO=new CustomSearchDTO();
        customSearchDTO.setUsername("driver01");
        driverService.findByCustomSearchAndCar(customSearchDTO,createCar().get());
        driverService.findByCustomSearchAndCar(customSearchDTO,null);
        when(driverRepository.findAll(example)).thenReturn(Optional.empty());
        driverService.findByCustomSearchAndCar(customSearchDTO,null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findDriverChecked() throws EntityNotFoundException{
        when(driverRepository.findByIdAndDeleted(6L,false)).thenReturn(createDriver());
        Assert.assertEquals(createDriver().get(),driverService.find(6L));
        Assert.assertNotEquals(createDriver().get(),driverService.find(11L));
    }






    private Optional<DriverDO> createDriver() {
        DriverDO driverDO=new DriverDO("driver01","pass01");
        return Optional.of(driverDO);
    }
    private Optional<List<DriverDO>> createDriverList() {
        DriverDO driverDO=new DriverDO("driver01","pass01");
        return Optional.of(Arrays.asList(driverDO));
    }

    private Optional<CarDO> createCar() {
        CarDO carDO=new CarDO("34GG9987");
        carDO.setId(2L);
        Optional<CarDO> carDOOptional= Optional.of(carDO);
        return carDOOptional;
    }

    private Optional<CarDO> createCarInUse() {
        CarDO carDO=new CarDO("34GG9987");
        carDO.setDriverDO(createDriver().get());
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
