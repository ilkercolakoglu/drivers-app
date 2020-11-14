package com.freenow.domainobject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ManufacturerDOTest {

    private ManufacturerDO manufacturerDO;

    @Before
    public void setup() {
        manufacturerDO = new ManufacturerDO("Mercedes");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(manufacturerDO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(manufacturerDO.toString());
    }

    @Test
    public void equalsTest() {
        ManufacturerDO documentToCompare = createManufacturerDO();
        ManufacturerDO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(manufacturerDO) && manufacturerDO.equals(documentToCompare));
        assertFalse(manufacturerDO.equals(nullRequest));
        assertFalse(manufacturerDO.equals(nullObject));
    }

    private ManufacturerDO createManufacturerDO() {
        ManufacturerDO manufacturerDO = new ManufacturerDO("Mercedes");
        return manufacturerDO;
    }

}
