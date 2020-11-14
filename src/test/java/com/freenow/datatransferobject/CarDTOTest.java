package com.freenow.datatransferobject;

import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.GearType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CarDTOTest {
    private CarDTO carDTO;

    @Before
    public void setup() {

        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(9L)
                .setLicensePlate("34DD5555")
                .setSeatCount(2)
                .setEngineType(EngineType.GAS)
                .setHp(1.5)
                .setColor("RED")
                .setModelYear("2018")
                .setGearType(GearType.AUTOMATIC);

        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder=ManufacturerDTO.newBuilder()
                .setId(1L)
                .setBrandName("BMW")
                .setWarrantyPeriod(3);
        ManufacturerDTO manufacturerDTO = manufacturerDTOBuilder.createManufacturerDTO();

        carDTOBuilder.setConvertible(false).setManufacturerDTO(manufacturerDTO).setRating(2.3);
        carDTO = carDTOBuilder.createCarDTO();
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(carDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(carDTO.toString());
    }

    @Test
    public void equalsTest() {
        CarDTO documentToCompare = createCarDTO();
        CarDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(carDTO) && carDTO.equals(documentToCompare));
        assertFalse(carDTO.equals(nullRequest));
        assertFalse(carDTO.equals(nullObject));
    }

    private CarDTO createCarDTO() {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(9L)
                .setLicensePlate("34DD5555")
                .setSeatCount(2)
                .setEngineType(EngineType.GAS)
                .setHp(1.5)
                .setColor("RED")
                .setModelYear("2018")
                .setGearType(GearType.AUTOMATIC);

        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder=ManufacturerDTO.newBuilder()
                .setId(1L)
                .setBrandName("BMW")
                .setWarrantyPeriod(3);
        ManufacturerDTO manufacturerDTO = manufacturerDTOBuilder.createManufacturerDTO();

        carDTOBuilder.setConvertible(false).setManufacturerDTO(manufacturerDTO).setRating(2.3);
        return carDTOBuilder.createCarDTO();
    }

}
