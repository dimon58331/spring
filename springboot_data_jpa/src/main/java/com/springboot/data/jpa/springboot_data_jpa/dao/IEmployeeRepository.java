package com.springboot.data.jpa.springboot_data_jpa.dao;


import com.springboot.data.jpa.springboot_data_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}
