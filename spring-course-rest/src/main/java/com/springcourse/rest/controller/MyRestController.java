 package com.springcourse.rest.controller;

import com.springcourse.rest.entity.Employee;
import com.springcourse.rest.exception_handling.NoSuchEmployeeException;
import com.springcourse.rest.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){

        if(employeeService.getEmployeeById(id) == null){
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database");
        }

        employeeService.deleteEmployeeById(id);

        return "Employee with ID = " + id + " was deleted";
    }
}
