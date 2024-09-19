package com.revspeed.backend_p1.dto;

import java.util.List;

public class PlanDTO {
    private String name;
    private String description;
    private double price;
    private int duration;
    private int speed;
    private List<Long> ottPlatforms; // This should match the JSON field name

    // Constructors, Getters, and Setters
    public PlanDTO() {}

    public PlanDTO(String name, String description, int speed, double price, int duration, List<Long> ottPlatforms) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.ottPlatforms = ottPlatforms;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Long> getOttPlatforms() {
        return ottPlatforms;
    }

    public void setOttPlatforms(List<Long> ottPlatforms) {
        this.ottPlatforms = ottPlatforms;
    }

    @Override
    public String toString() {
        return "PlanDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", speed=" + speed +
                ", ottPlatforms=" + ottPlatforms +
                '}';
    }
}