 package com.springcourse.rest.controller;

import com.springcourse.rest.entity.Employee;
import com.springcourse.rest.exception_handling.EmployeeInCorrectData;
import com.springcourse.rest.exception_handling.NoSuchEmployeeException;
import com.springcourse.rest.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id = " + id
                    + " in Database");
        }

        return employee;
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeInCorrectData> handleException(NoSuchEmployeeException exception){
        EmployeeInCorrectData data = new EmployeeInCorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeInCorrectData> handleException(Exception exception){
        EmployeeInCorrectData data = new EmployeeInCorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}