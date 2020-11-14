package com.freenow.datatransferobject;

import com.freenow.domainvalue.OnlineStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomSearchDTOTest {

    private CustomSearchDTO customSearchDTO;

    @Before
    public void setup() {

        customSearchDTO=new CustomSearchDTO();
        customSearchDTO.setOnlineStatus(OnlineStatus.ONLINE);
        customSearchDTO.setUsername("driver05");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(customSearchDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(customSearchDTO.toString());
    }

    @Test
    public void equalsTest() {
        CustomSearchDTO documentToCompare = createCustomSearchDTO();
        CustomSearchDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(customSearchDTO) && customSearchDTO.equals(documentToCompare));
        assertFalse(customSearchDTO.equals(nullRequest));
        assertFalse(customSearchDTO.equals(nullObject));
    }

    private CustomSearchDTO createCustomSearchDTO() {
        CustomSearchDTO customSearchDTO=new CustomSearchDTO();
        customSearchDTO.setOnlineStatus(OnlineStatus.ONLINE);
        customSearchDTO.setUsername("driver05");
        return customSearchDTO;
    }
}
