package com.spring.rest.client.main;

import com.spring.rest.client.model.MeasurementDTO;
import com.spring.rest.client.model.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        SensorDTO sensorDTO = new SensorDTO("second_sensor");
        MeasurementDTO measurementDTO = new MeasurementDTO(30, true, sensorDTO);

        //convert to Json
        HttpEntity<MeasurementDTO> request = new HttpEntity<>(measurementDTO);

        String url = "http://localhost:8080/measurements/add";

        //Request to server and response
        String response = restTemplate.postForObject(url, request, String.class);
    }

}
