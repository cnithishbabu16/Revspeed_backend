package com.revspeed.backend_p1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
//
//
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 20)
//    private ERole name;
//
//    // Constructors, getters, and setters
//    public Role() {
//    }
//
//    public Role(ERole name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public ERole getName() {
//        return name;
//    }
//
//    public void setName(ERole name) {
//        this.name = name;
//    }
//
//}

@Setter
@Getter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(){}

    public Role(ERole name) {
        this.name = name;
    }
// Constructors, getters, and setters
}
