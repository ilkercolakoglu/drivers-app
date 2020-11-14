package com.freenow.domainobject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CarDOTest {

    private CarDO carDO;

    @Before
    public void setup() {
        carDO = new CarDO("34HY8754");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(carDO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(carDO.toString());
    }

    @Test
    public void equalsTest() {
        CarDO documentToCompare = createCarDO();
        CarDO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(carDO) && carDO.equals(documentToCompare));
        assertFalse(carDO.equals(nullRequest));
        assertFalse(carDO.equals(nullObject));
    }

    private CarDO createCarDO() {
        CarDO carDO = new CarDO("34HY8754");
        return carDO;
    }

}
