package com.revspeed.backend_p1.model;

import jakarta.persistence.*;

import java.util.List;

//@Entity
//public class Plan {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String description;
//    private double price;
//
//    public int duration;
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//
//
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }
//    @ElementCollection
//    @CollectionTable(name = "plan_ott_platforms", joinColumns = @JoinColumn(name = "plan_id"))
//    @Column(name = "ott_platform")
//    private List<String> ottPlatforms;
//
//
//    public Plan() {}
//
//    public Plan(String name, double price, int duration, List<String> ottPlatforms) {
//        this.name = name;
//        this.price = price;
//        this.duration = duration;
//        this.ottPlatforms = ottPlatforms;
//    }
//
//    // Getters and Setters
//}

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int duration; // in days

    @ManyToMany
    @JoinTable(
            name = "plan_ott_platforms",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "ott_platform_id")
    )
    private List<OTTPlatform> ottPlatforms;

    public Plan(String description, int duration, Long id, String name, List<OTTPlatform> ottPlatforms, double price) {
        this.description = description;
        this.duration = duration;
        this.id = id;
        this.name = name;
        this.ottPlatforms = ottPlatforms;
        this.price = price;
        this.speed=speed;
    }

    public Plan() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OTTPlatform> getOttPlatforms() {
        return ottPlatforms;
    }

    public void setOttPlatforms(List<OTTPlatform> ottPlatforms) {
        this.ottPlatforms = ottPlatforms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    // Getters and Setters
}


