package com.spring.rest.controller;

import com.spring.rest.dto.SensorDTO;
import com.spring.rest.service.SensorService;
import com.spring.rest.util.SensorErrorResponse;
import com.spring.rest.util.SensorNotCreatedException;
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

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors){
                stringBuilder.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(stringBuilder.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> sensorErrorResponseEntity(SensorNotCreatedException exception){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(exception.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private com.spring.rest.model.Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, com.spring.rest.model.Sensor.class);
    }
}
