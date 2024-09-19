package com.revspeed.backend_p1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OTTPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Constructors, Getters, and Setters
    public OTTPlatform() {}

    public OTTPlatform(String name) {
        this.name = name;
    }

}
