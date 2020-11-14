package com.freenow.domainobject;

import com.freenow.domainvalue.OnlineStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverDOTest {

    private DriverDO driverDO;

    @Before
    public void setup() {
        driverDO = new DriverDO("driver01",OnlineStatus.ONLINE);
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(driverDO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(driverDO.toString());
    }

    @Test
    public void equalsTest() {
        DriverDO documentToCompare = createDriverDO();
        DriverDO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(driverDO) && driverDO.equals(documentToCompare));
        assertFalse(driverDO.equals(nullRequest));
        assertFalse(driverDO.equals(nullObject));
    }

    private DriverDO createDriverDO() {
        DriverDO driverDO = new DriverDO("driver01",OnlineStatus.ONLINE);
        return driverDO;
    }

}
