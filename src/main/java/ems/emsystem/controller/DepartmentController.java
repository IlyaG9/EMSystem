/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.controller;

import ems.emsystem.domain.Department;
import ems.emsystem.service.DepartmentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
@Controller
@RequestMapping(value = {"/department"})
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = {"/"})
    public String showDepartments(@RequestParam(value = "page", required = false) Integer page, Model model) {
        if (page == null) {
            page = 0;
        }
        List<Department> departments = departmentService.getDepartmentsForPage(page);
        int pages = departmentService.pageCount();
        model.addAttribute("departments", departments);
        model.addAttribute("pages", pages);
        return "departments";
    }

    @RequestMapping(value = {"/search"})
    public String findDepartments(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "page", required = false) Integer page, Model model) {
        if (name == null) {
            name = "";
        }
        if (page == null) {
            page = 0;
        }
        List<Department> findDepartments = departmentService.findDepartments(name, page);
        if (findDepartments == null) {
            return "notFound";
        }
        int pages = departmentService.pageCount(name);
        model.addAttribute("name", name);
        model.addAttribute("pages", pages);
        model.addAttribute("departments", findDepartments);
        return "deparmentSearchResult";
    }

    @RequestMapping(value = {"/addForm"}, method = RequestMethod.GET)
    public String showNewDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "newDepartment";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewDepartment(@Valid Department dep, BindingResult bindingResult, Model model) {
        Department department = departmentService.getDepartment(dep.getName());
        if (department != null) {
           bindingResult.rejectValue("name", "department.exists");        
        }
        if (bindingResult.hasErrors()) {
            return "newDepartment";
        }

        long id = departmentService.addDepartment(dep);
        return "redirect: /" + id;
    }

    @RequestMapping(value = {"/{id}"})
    public String getDepartment(@PathVariable Long id, Model model) {
        Department dep = departmentService.getDepartment(id);
        if (dep == null) {
            return "notFound";
        } else {
            model.addAttribute("department", dep);
            return "department";
        }
    }

    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
    public String showEditDepartmentForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartment(id);
        model.addAttribute("department", department);
        return "editDepartment";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editDepartment(@Valid Department dep, BindingResult br) {
        if (br.hasErrors()) {
            return "editDepartment";
        }
        departmentService.editDepartment(dep);
        return "redirect: /" + dep.getId();
    }
}
