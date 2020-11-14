package com.freenow.controller;

import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.service.driver.DefaultDriverService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DriverControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DriverController driverController;

    @Mock
    private DefaultDriverService driverService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
    }

    private Optional<DriverDO> createDriver() {
        DriverDO driverDO=new DriverDO("driver01","pass01");
        return Optional.of(driverDO);
    }

    private Optional<List<DriverDO>> createDriverList() {
        DriverDO driverDO=new DriverDO("driver01","pass01");
        return Optional.of(Arrays.asList(driverDO));
    }

    @Test
    public void getDriverTest() throws Exception {
        when(driverService.find(Mockito.any(Long.class))).thenReturn(createDriver().get());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/drivers/4").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void createTest() throws Exception {
        when(driverService.create(Mockito.any(DriverDO.class))).thenReturn(createDriver().get());
        String body = "{\n"
                + "    \"username\": \"driver01\",\n"
                + "    \"password\": \"12345\"\n"
                + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/drivers").accept(MediaType.APPLICATION_JSON).content(body).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/drivers/3").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void updateLocationTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/drivers/3?longitude=44&latitude=65").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void findDriversTest() throws Exception {
        when(driverService.find(Mockito.any(OnlineStatus.class))).thenReturn(createDriverList().get());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/drivers?onlineStatus=OFFLINE").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void selectCarTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/drivers/3/select_car?carId=4").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deselectCarTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/drivers/3/deselect_car?carId=4").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void findDriverListTest() throws Exception {
        when(driverService.create(Mockito.any(DriverDO.class))).thenReturn(createDriver().get());
        String body = "{\n"
                + "    \"username\": \"driver01\",\n"
                + "    \"onlineStatus\": \"ONLINE\",\n"
                + "    \"carDTO\":{ \"licensePlate\": \"34GYH72\"}\n"
                + "}";


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/drivers/search").accept(MediaType.APPLICATION_JSON).content(body).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.FOUND.value(), response.getStatus());
    }
}
