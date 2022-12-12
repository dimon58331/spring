package com.springcourse.rest.service;

import com.springcourse.rest.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);

    public void deleteEmployeeById(int id);
}
