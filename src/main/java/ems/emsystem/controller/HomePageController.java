/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.controller;

import ems.emsystem.domain.Employee;
import ems.emsystem.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
@Controller
public class HomePageController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"/", "/home"})
    public String showMainPage(@RequestParam(value = "page", required = false) Integer page,Model model) {
        if (page == null) {
            page = 0;
        }
        List<Employee> emplList = employeeService.getEmployeesForPage(page);
        int pages=employeeService.pageCount();
        model.addAttribute("pages", pages);
        model.addAttribute("employeeList", emplList);
        return "home";
    }

}
