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

    @PostMapping("/cars/rent/{client_id}/{car_id}")
    public Car rentCar(@PathVariable Long client_id, @PathVariable Long car_id){
        return carService.rentCar(client_id, car_id);
    }

    @PostMapping("/cars/return/{client_id}/{car_id}")
    public Car returnCar(@PathVariable Long client_id, @PathVariable Long car_id){
        return carService.returnCar(client_id, car_id);
    }

    @PutMapping("/cars/edit")
    public Car editCar(@RequestBody Car car) {
        return carService.editCar(car);
    }

    @DeleteMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable long id){
        carService.deleteCar(id);
        return "Deleted car: " + id;
    }
}
