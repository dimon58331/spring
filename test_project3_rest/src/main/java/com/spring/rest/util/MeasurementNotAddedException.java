package com.spring.rest.util;

import com.spring.rest.model.Measurement;

public class MeasurementNotAddedException extends RuntimeException{
    public MeasurementNotAddedException(String message){
        super(message);
    }
}
