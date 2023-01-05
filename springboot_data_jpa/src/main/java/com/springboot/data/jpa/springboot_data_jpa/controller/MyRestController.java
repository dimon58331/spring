 package com.springboot.data.jpa.springboot_data_jpa.controller;



import com.springboot.data.jpa.springboot_data_jpa.entity.Employee;
import com.springboot.data.jpa.springboot_data_jpa.service.IEmployeeService;
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
        employeeService.deleteEmployeeById(id);

        return "Employee with ID = " + id + " was deleted";
    }
}
