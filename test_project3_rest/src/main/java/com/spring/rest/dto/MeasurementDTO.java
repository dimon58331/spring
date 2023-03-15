package com.spring.rest.dto;

import com.spring.rest.model.Sensor;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public class MeasurementDTO {
    @DecimalMin(value = "-100")
    @DecimalMax(value = "100")
    private double temperature;

    private boolean raining;

    private SensorDTO sensorDTO;

    public MeasurementDTO() {
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "temperature=" + temperature +
                ", raining=" + raining +
                ", sensorDTO=" + sensorDTO +
                '}';
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

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
