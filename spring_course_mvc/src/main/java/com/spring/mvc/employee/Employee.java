package com.spring.mvc.employee;

import com.spring.mvc.validator.CheckEmail;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;

public class Employee {
    @Size(min = 2, message = "min symbols for name = 2")
    @NotBlank(message = "name is required field")
    private String name;

    @Size(min = 2, message = "min symbols for surname = 2")
    @NotBlank(message = "surname is required field")
    private String surname;

    @Min(value = 0, message = "your salary should be more than 0")
    @Max(value = 3500, message = "your salary should be less than 3500")
    private int salary;

    private String department;
    private Map<String, String> departments;

    @NotEmpty(message = "car brand is required field")
    private String carBrand;
    private Map<String, String> carBrands;

    @NotEmpty(message = "foreign languages is required field")
    private String[] foreignLanguages;
    private Map<String, String> foreignLanguagesMap;

    @Pattern(regexp = "\\d{2}-\\d{3}-\\d{2}-\\d{2}", message = "please use pattern XX-XXX-XX-XX")
    private String phoneNumber;

    @CheckEmail(value = "bsu.by", message = "email must ends with bsu.by")
    private String email;

    public Employee() {
        departments = new HashMap<>();
        departments.put("Information Technologies", "IT");
        departments.put("InStat hockey analyzer", "HOCKEY");
        departments.put("Student", "STUDENT");

        carBrands = new HashMap<>();
        carBrands.put("Mercedes-Benz", "MB");
        carBrands.put("Audi", "AUDI");
        carBrands.put("BMW", "BMW");

        foreignLanguagesMap = new HashMap<>();
        foreignLanguagesMap.put("English", "EN");
        foreignLanguagesMap.put("German", "DE");
        foreignLanguagesMap.put("French", "FR");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Map<String, String> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Map<String, String> getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(Map<String, String> carBrands) {
        this.carBrands = carBrands;
    }

    public String[] getForeignLanguages() {
        return foreignLanguages;
    }

    public void setForeignLanguages(String[] foreignLanguages) {
        this.foreignLanguages = foreignLanguages;
    }

    public Map<String, String> getForeignLanguagesMap() {
        return foreignLanguagesMap;
    }

    public void setForeignLanguagesMap(Map<String, String> foreignLanguagesMap) {
        this.foreignLanguagesMap = foreignLanguagesMap;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
