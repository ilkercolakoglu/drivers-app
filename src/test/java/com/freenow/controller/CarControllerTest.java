package com.freenow.controller;

import com.freenow.domainobject.CarDO;
import com.freenow.service.car.DefaultCarService;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CarControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CarController carController;

    @Mock
    private DefaultCarService carService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    private Optional<CarDO> createCar() {
        CarDO carDO=new CarDO("34BC9987");
        Optional<CarDO> carDOOptional= Optional.of(carDO);
        return carDOOptional;
    }

    @Test
    public void getCarTest() throws Exception {
        when(carService.find(Mockito.any(Long.class))).thenReturn(createCar().get());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/cars/4").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void createTest() throws Exception {
        when(carService.create(Mockito.any(CarDO.class))).thenReturn(createCar().get());
        String body = "{\n"
                + "    \"licensePlate\": \"car01\",\n"
                + "    \"seatCount\": 2,\n"
                + "    \"engineType\": \"GAS\",\n"
                + "    \"manufacturerDTO\":{ \n"
                + "    \"brandName\": \"BMW\"}\n"
                + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/cars").accept(MediaType.APPLICATION_JSON).content(body).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/cars/3").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }



}
