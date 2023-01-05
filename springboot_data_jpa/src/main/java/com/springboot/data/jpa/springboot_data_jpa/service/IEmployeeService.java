package com.springboot.data.jpa.springboot_data_jpa.service;

import com.springboot.data.jpa.springboot_data_jpa.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);

    public void deleteEmployeeById(int id);
}
