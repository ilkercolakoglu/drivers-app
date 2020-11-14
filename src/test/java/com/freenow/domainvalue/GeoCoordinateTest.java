package com.freenow.domainvalue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GeoCoordinateTest {
    private GeoCoordinate geoCoordinate;

    @Before
    public void setup() {

        geoCoordinate=new GeoCoordinate(5.2,4.1);
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(geoCoordinate.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(geoCoordinate.toString());
    }

    @Test
    public void equalsTest() {
        GeoCoordinate documentToCompare = createGeoCoordinate();
        GeoCoordinate nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(geoCoordinate) && geoCoordinate.equals(documentToCompare));
        assertFalse(geoCoordinate.equals(nullRequest));
        assertFalse(geoCoordinate.equals(nullObject));
    }

    private GeoCoordinate createGeoCoordinate() {
        GeoCoordinate geoCoordinate=new GeoCoordinate(5.2,4.1);
        return geoCoordinate;
    }

}
