package com.spring.rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "temperature")
    @DecimalMin(value = "-100")
    @DecimalMax(value = "100")
    private double temperature;

    @Column(name = "raining")
    private boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_created")
    private Date dateOfCreated;

    public Measurement() {
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }

    public Date getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(Date dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
