package com.course.spring_hibernate_aop.controller;

import com.course.spring_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowEmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees(Model model){
        model.addAttribute("allEmployees", employeeService.getAllEmployees());

        return "show-all-employees";
    }
}
