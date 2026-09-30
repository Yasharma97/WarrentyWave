package com.abes.warrentyWave.dto;

import java.io.Serializable;

public class VehicleResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String vin;
    private String make;
    private String model;
    private Integer year;
    private String licensePlate;
    private Double mileage;
    private Long customerId;
    private String ownerName;

    public VehicleResponseDTO() {
    }

    public VehicleResponseDTO(Long id, String vin, String make, String model,
                              Integer year, String licensePlate, Double mileage,
                              Long customerId, String ownerName) {
        this.id = id;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.customerId = customerId;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "VehicleResponseDTO{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                ", mileage=" + mileage +
                ", customerId=" + customerId +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
