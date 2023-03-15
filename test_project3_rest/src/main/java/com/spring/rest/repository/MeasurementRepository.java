package com.spring.rest.repository;

import com.spring.rest.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    public List<Measurement> findAllByRainingIsTrue();
}
