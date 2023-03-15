package com.spring.rest.client.model;

public class MeasurementDTO {
    private double temperature;
    private boolean raining;
    private SensorDTO sensorDTO;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double temperature, boolean raining, SensorDTO sensorDTO) {
        this.temperature = temperature;
        this.raining = raining;
        this.sensorDTO = sensorDTO;
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
