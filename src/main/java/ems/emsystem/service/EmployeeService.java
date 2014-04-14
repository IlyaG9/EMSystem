/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.service;

import ems.emsystem.domain.Employee;
import java.util.List;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
public interface EmployeeService {

    public int pageCount();

    public int pageCountForSerach(String name);

    public List<Employee> getAllEmployees();

    public void deleteEmployee(Employee employee);

    public void deleteEmployee(Long id);

    public Employee getEmployee(Long id);

    public List<Employee> findEmployees(String name, int page);

    public List<Employee> getEmployeesForPage(int page);

    public Long addNewEmployee(Employee employee);
    
    public void editEmployee(Employee editable);
}
