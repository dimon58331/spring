package com.spring.rest.service;

import com.spring.rest.model.Measurement;
import com.spring.rest.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    public Map<String, Integer> findAllRainyDays(){
        List<Measurement> measurements = measurementRepository.findAllByRainingIsTrue();
        return new HashMap<>(Map.of("count of rainy days", measurements.isEmpty() ? 0 : measurements.size()));
    }

    @Transactional
    public void save(Measurement measurement){
        measurement.setDateOfCreated(new Date());
        measurementRepository.save(measurement);
    }
}
