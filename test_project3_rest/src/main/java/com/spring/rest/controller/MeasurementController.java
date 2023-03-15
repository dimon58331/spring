package com.spring.rest.controller;

import com.spring.rest.dto.MeasurementDTO;
import com.spring.rest.dto.SensorDTO;
import com.spring.rest.model.Measurement;
import com.spring.rest.model.Sensor;
import com.spring.rest.service.MeasurementService;
import com.spring.rest.service.SensorService;
import com.spring.rest.util.MeasurementErrorResponse;
import com.spring.rest.util.MeasurementNotAddedException;
import com.spring.rest.util.SensorErrorResponse;
import com.spring.rest.util.SensorNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements(){
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Map<String, Integer> getCountRainyDays(){
        return measurementService.findAllRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors){
                stringBuilder.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage());
            }

            throw new MeasurementNotAddedException(stringBuilder.toString());
        }

        measurementService.save(convertToMeasurement(measurementDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> measurementErrorResponse(MeasurementNotAddedException e){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(), LocalDateTime.now()
        );

        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<SensorErrorResponse> sensorErrorResponseEntity(SensorNotFoundException exception){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(exception.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);

        Sensor sensor = sensorService.findByName(measurementDTO.getSensorDTO().getName());
        measurement.setSensor(sensor);

        return measurement;
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensorDTO(convertToSensorDTO(measurement.getSensor()));
        return measurementDTO;
    }

    private SensorDTO convertToSensorDTO(com.spring.rest.model.Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
