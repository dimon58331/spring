package com.springboot.data.jpa.springboot_data_jpa.service;


import com.springboot.data.jpa.springboot_data_jpa.dao.IEmployeeRepository;
import com.springboot.data.jpa.springboot_data_jpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = null;
        if (employeeOptional.isPresent()){
            employee = employeeOptional.get();
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
}
