package com.example.carRentalApplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Car {
    @Id
    private long id;
    private String brand;
    private String model;
    private long loan_identifier;

}
