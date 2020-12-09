package com.example.carRentalApplication.service;

import com.example.carRentalApplication.model.Car;
import com.example.carRentalApplication.model.Client;
import com.example.carRentalApplication.repository.CarRepository;
import com.example.carRentalApplication.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Transactional
    public Car rentCar(Long client_id, Long car_id) {
        Car car1 = carRepository.findById(car_id).orElseThrow();
        Client client = clientRepository.findById(client_id).orElseThrow();
        car1.setLoan_identifier(client.getClient_id());
        client.setRented_car(car1.getId());
        return car1;
    }

    @Transactional
    public Car returnCar(Long client_id, Long car_id) {
        Car car1 = carRepository.findById(car_id).orElseThrow();
        Client client = clientRepository.findById(client_id).orElseThrow();
        car1.setLoan_identifier(0);
        client.setRented_car(0);
        return car1;
    }

    @Transactional
    public Car editCar(Car car) {
        Car carEdited = carRepository.findById(car.getId()).orElseThrow();
        carEdited.setBrand(car.getBrand());
        carEdited.setModel(car.getModel());
        return carEdited;
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
