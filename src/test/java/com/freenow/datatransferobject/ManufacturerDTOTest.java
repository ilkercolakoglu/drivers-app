package com.freenow.datatransferobject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ManufacturerDTOTest {
    private ManufacturerDTO manufacturerDTO;

    @Before
    public void setup() {

        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder = ManufacturerDTO.newBuilder()
                .setId(9L)
                .setBrandName("BMW")
                .setWarrantyPeriod(2);

        manufacturerDTO = manufacturerDTOBuilder.createManufacturerDTO();
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(manufacturerDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(manufacturerDTO.toString());
    }

    @Test
    public void equalsTest() {
        ManufacturerDTO documentToCompare = createManufacturerDTO();
        ManufacturerDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(manufacturerDTO) && manufacturerDTO.equals(documentToCompare));
        assertFalse(manufacturerDTO.equals(nullRequest));
        assertFalse(manufacturerDTO.equals(nullObject));
    }

    private ManufacturerDTO createManufacturerDTO() {
        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder = ManufacturerDTO.newBuilder()
                .setId(9L)
                .setBrandName("BMW")
                .setWarrantyPeriod(2);

        return manufacturerDTOBuilder.createManufacturerDTO();
    }

}
