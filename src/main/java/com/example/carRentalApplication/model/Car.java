package com.example.carRentalApplication.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String model;
    private long loan_identifier;
    //@OneToMany
    //@JoinColumn(name="rented_car", updatable = false, insertable = true)
    //private Client client;


}
