package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String ifLoginIsSuccess() { return "redirect:/employee-info"; }

    @GetMapping("/employee-info")
    public String getInfoForAllEmployees(){
        return "for-all-employees";
    }

    @GetMapping("/manager-info")
    public String getInfoForAllManagers() { return "manager-info"; }

    @GetMapping("/hr-info")
    public String getInfoForAllHR() { return "hr-info"; }
}
