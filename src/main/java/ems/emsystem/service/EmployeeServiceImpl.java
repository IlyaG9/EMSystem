/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.service;

import ems.emsystem.dao.EmployeeDAO;
import ems.emsystem.domain.Department;
import ems.emsystem.domain.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private DepartmentService departmentService;

    @Value("${app.rows_on_page}")
    private Integer rows_on_page = 100;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();

    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDAO.deleteEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    public List<Employee> findEmployees(String name, int page) {
        int from = 0;
        if (page != 0) {
            from = page * rows_on_page;
        }
        return employeeDAO.findEmployees(convertName(name), from, rows_on_page);
    }

    @Override
    public List<Employee> getEmployeesForPage(int page) {
        int from = 0;
        if (page != 0) {
            from = page * rows_on_page;
        }
        return employeeDAO.getEmployeesForPage(from, rows_on_page);
    }

    @Override
    public int pageCount() {
        int x = getAllEmployees().size() % rows_on_page;
        int pages = getAllEmployees().size() / rows_on_page;
        if (x > 0) {
            pages++;
        }
        return pages;
    }

    @Override
    public int pageCountForSerach(String name) {
        int rows = employeeDAO.countForSerach(convertName(name));
        int x = rows % rows_on_page;
        int pages = rows / rows_on_page;
        if (x > 0) {
            pages++;
        }
        return pages;
    }

    private String convertName(String name) {
        name = name.trim();
        name = name.replace("*", "%");
        name = name.replace("?", "_");
        name = name.replace(" ", "%");
        name = "%" + name + "%";
        return name;
    }

    @Override
    public Long addNewEmployee(Employee employee) {
        long id = 0;
        Department dep = departmentService.getDepartment(employee.getDepartment().getName());
        employee.setDepartment(dep);
        employee.setFirstname(format(employee.getFirstname()));
        employee.setFirstname(format(employee.getLastname()));
        employeeDAO.addEmployee(employee);
        Employee employee1 = employeeDAO.getEmployee(employee.getFirstname(), employee.getLastname(), employee.getSalary(), employee.getDepartment());
        return employee1.getId();
    }

    @Override
    public void editEmployee(Employee editable) {
        Department dep = departmentService.getDepartment(editable.getDepartment().getName());
        editable.setDepartment(dep);
        employeeDAO.editEmployee(editable);
    }

    public String format(String name) {
        StringBuilder sb = new StringBuilder();
        char first = Character.toUpperCase(name.charAt(0));
        sb.append(first);
        String substring = name.substring(1, name.length());
        sb.append(substring);
        return sb.toString();
    }
}
