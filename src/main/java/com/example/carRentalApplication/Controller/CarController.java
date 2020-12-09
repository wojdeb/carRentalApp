package com.example.carRentalApplication.Controller;

import com.example.carRentalApplication.model.Car;
import com.example.carRentalApplication.model.Client;
import com.example.carRentalApplication.service.CarService;
import com.example.carRentalApplication.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final ClientService clientService;


    @GetMapping("/cars")
    public List<Car> getCars(){
        return carService.getCars();
    }

    @PostMapping("/cars/add")
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @PostMapping("/clients/add")
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @PostMapping("/cars/rent")
    public void rentCar(@RequestBody Car car){
        carService.rentCar(car);
    }
}
