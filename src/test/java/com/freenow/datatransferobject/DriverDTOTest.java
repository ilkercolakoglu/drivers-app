package com.freenow.datatransferobject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverDTOTest {
    private DriverDTO driverDTO;

    @Before
    public void setup() {

        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
                .setId(1L)
                .setPassword("12345")
                .setUsername("user");
        driverDTO = driverDTOBuilder.createDriverDTO();
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(driverDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(driverDTO.toString());
    }

    @Test
    public void equalsTest() {
        DriverDTO documentToCompare = createDriverDTO();
        DriverDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(driverDTO) && driverDTO.equals(documentToCompare));
        assertFalse(driverDTO.equals(nullRequest));
        assertFalse(driverDTO.equals(nullObject));
    }

    private DriverDTO createDriverDTO() {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
                .setId(1L)
                .setPassword("12345")
                .setUsername("user");
        return driverDTOBuilder.createDriverDTO();
    }



}
