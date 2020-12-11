package com.example.carRentalApplication.Controller;

import com.example.carRentalApplication.model.Car;
import com.example.carRentalApplication.model.Client;
import com.example.carRentalApplication.repository.CarRepository;
import com.example.carRentalApplication.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private CarController carController;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Transactional
    void testGetCars() throws Exception {
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");
        carRepository.save(car);

        MvcResult result = mvc.perform(get("/cars"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Car[] car1 = objectMapper.readValue(result.getResponse().getContentAsString(), Car[].class);
        assertThat(car1).isNotNull();
    }

    @Test
    void testAddCar() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/cars/add")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"brand\":\"testBrand\",\"model\":\"testModel\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

        Car car = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);
        assertThat(car).isNotNull();
    }

    @Test
    @Transactional
    void getClients() throws Exception {
        Client client = new Client();
        client.setName("TestName");
        client.setLastName("TestLastName");
        clientRepository.save(client);

        MvcResult result = mvc.perform(get("/clients"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Client[] clients = objectMapper.readValue(result.getResponse().getContentAsString(), Client[].class);
    }

    @Test
    void addClient() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/clients/add")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"testName\",\"lastName\":\"testLastName\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

        Client client = objectMapper.readValue(result.getResponse().getContentAsString(), Client.class);
        assertThat(client).isNotNull();
    }

    @Test
    @Transactional
    void rentCar() throws Exception {

        Client client = new Client();
        client.setName("TestName");
        client.setLastName("TestLastName");
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");

        clientRepository.save(client);
        carRepository.save(car);


        RequestBuilder request = MockMvcRequestBuilders
                .post("/cars/rent/{client_id}/{car_id}/", client.getClientId(), car.getId());

        MvcResult result = mvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

        Car car1 = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);
        assertThat(car1).isNotNull();
        assertTrue(car1.getRentedByClient()==client.getRentedCar());

    }

    @Test
    @Transactional
    void returnCar() throws Exception {
        Client client = new Client();
        client.setName("TestName");
        client.setLastName("TestLastName");
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");

        car.setRentedByClient(client.getClientId());
        client.setRentedCar(car.getRentedByClient());

        clientRepository.save(client);
        carRepository.save(car);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/cars/return/{client_id}/{car_id}/", client.getClientId(), car.getId());

        MvcResult result = mvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

        Car car1 = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);
        assertThat(car1).isNotNull();
        assertTrue(car1.getRentedByClient()==0);
        assertTrue(client.getRentedCar()==0);
    }

    @Test
    @Transactional
    void editCar() throws Exception {
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");
        carRepository.save(car);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/cars/edit/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"brand\":\"modifiedBrand\",\"model\":\"modifiedModel\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

        assertThat(car).isNotNull();
        assertTrue(car.getBrand().equals("modifiedBrand"));
        assertTrue(car.getModel().equals("modifiedModel"));
    }

    @Test
    void deleteCar() throws Exception {
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");
        carRepository.save(car);
        String expected = "Deleted car: " + car.getId();

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/cars/delete/{car_id}/", car.getId());

        MvcResult result = mvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

        assertThat(result).isNotNull();
    }
}