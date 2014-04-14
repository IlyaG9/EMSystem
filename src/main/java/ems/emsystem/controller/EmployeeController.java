/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.controller;

import ems.emsystem.domain.Department;
import ems.emsystem.domain.Employee;
import ems.emsystem.service.DepartmentService;
import ems.emsystem.service.EmployeeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            return "notFound";
        } else {
            model.addAttribute("employee", employee);
            return "employee";
        }
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "page", required = false) Integer page, Model model) {
        if (name == null) {
            name = "";
        }
        if (page == null) {
            page = 0;
        }

        List<Employee> el = employeeService.findEmployees(name, page);
        int pages = employeeService.pageCountForSerach(name);
        if (el == null) {
            return "notFound";
        }
        model.addAttribute("name", name);
        model.addAttribute("pages", pages);
        model.addAttribute("employeeList", el);
        return "searchresult";
    }

    @RequestMapping(value = {"/addForm"}, method = RequestMethod.GET)
    public String showNewEmployeeForm(Model model) {
        List<Department> depList = departmentService.getDepartments();
        Employee emp = new Employee();
        emp.setDepartment(depList.get(0));
        model.addAttribute(emp);
        model.addAttribute("departments", depList);
        return "newEmployee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewEmployee(@Valid Employee emp, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<Department> depList = departmentService.getDepartments();
            model.addAttribute("departments", depList);
            return "newEmployee";

        }
        long id = employeeService.addNewEmployee(emp);
        return "redirect: /" + id;
    }

    @RequestMapping(value = {"/edit/{id}"})
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        List<Department> depList = departmentService.getDepartments();
        model.addAttribute("departments", depList);
        return "editEmployee";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editEmployee(@Valid Employee emp, BindingResult bindingResult, Model model) {
           if (bindingResult.hasErrors()) {
            List<Department> depList = departmentService.getDepartments();
            model.addAttribute("departments", depList);
            return "editEmployee";
        }
        employeeService.editEmployee(emp);
        return "redirect: /" + emp.getId();
    }

}
