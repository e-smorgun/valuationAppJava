package com.Models;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "is_balcony")
    private boolean isBalcony;

    @Column(name = "cost_per_square_meter")
    private double costPerSquareMeter;

    @Column(name = "is_finishing_work")
    private boolean isFinishingWork;

    @Column(name = "floor")
    private int floor;

    @Column(name = "full_cost")
    private double fullCost;

    @Column(name = "material")
    private String material;

    @Column(name = "metro_distance")
    private double metroDistance;

    @Column(name = "region")
    private String region;

    @Column(name = "square")
    private double square;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "view_from_window")
    private String viewFromWindow;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBalcony(boolean b) {
        return isBalcony;
    }

    public void setBalcony(boolean balcony) {
        isBalcony = balcony;
    }

    public double getCostPerSquareMeter() {
        return costPerSquareMeter;
    }

    public void setCostPerSquareMeter(double costPerSquareMeter) {
        this.costPerSquareMeter = costPerSquareMeter;
    }

    public boolean isFinishingWork(boolean b) {
        return isFinishingWork;
    }

    public void setFinishingWork(boolean finishingWork) {
        isFinishingWork = finishingWork;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getFullCost() {
        return fullCost;
    }

    public void setFullCost(double fullCost) {
        this.fullCost = fullCost;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getMetroDistance() {
        return metroDistance;
    }

    public void setMetroDistance(double metroDistance) {
        this.metroDistance = metroDistance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getViewFromWindow() {
        return viewFromWindow;
    }

    public void setViewFromWindow(String viewFromWindow) {
        this.viewFromWindow = viewFromWindow;
    }
}

