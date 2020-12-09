package com.example.carRentalApplication.service;

import com.example.carRentalApplication.model.Car;
import com.example.carRentalApplication.model.Client;
import com.example.carRentalApplication.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public String rentCar(Car car) {
        return "not implemented yet";
    }
}
