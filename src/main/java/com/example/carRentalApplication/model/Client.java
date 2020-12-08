package com.example.carRentalApplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client {
    @Id
    private long client_id;
    private String name;
    private String lastName;


}
