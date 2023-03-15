package com.spring.rest.service;

import com.spring.rest.model.Sensor;
import com.spring.rest.repository.SensorRepository;
import com.spring.rest.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name){
        Optional<Sensor> sensor = sensorRepository.findByName(name);
        if (sensor.isEmpty()){
            throw new SensorNotFoundException("Sensor with this name not found");
        }
        return sensor.get();
    }
}
