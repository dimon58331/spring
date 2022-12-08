package com.course.spring_hibernate_aop.controller;

import com.course.spring_hibernate_aop.entity.Employee;
import com.course.spring_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees(Model model){
        model.addAttribute("allEmployees", employeeService.getAllEmployees());

        return "show-all-employees";
    }

    @RequestMapping("/addNewEmployee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());

        return "employee-info";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model){

        model.addAttribute("employee", employeeService.getEmployee(id));

        return "employee-info";
    }

}
