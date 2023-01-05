package com.sping.springboot.spring_springboot.dao;


import com.sping.springboot.spring_springboot.entity.Employee;

import java.util.List;

public interface IEmployeeDAO {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);

    public void deleteEmployeeById(int id);
}
