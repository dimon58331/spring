package com.springboot.data.rest.springboot_data_rest.dao;


import com.springboot.data.rest.springboot_data_rest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}
