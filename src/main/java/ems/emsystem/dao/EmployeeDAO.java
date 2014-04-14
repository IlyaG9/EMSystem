/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.dao;

import ems.emsystem.domain.Department;
import ems.emsystem.domain.Employee;
import java.util.List;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
public interface EmployeeDAO {

    public void addEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public void deleteEmployee(Employee employee);

    public void deleteEmployee(Long id);

    public Employee getEmployee(Long id);

    public List<Employee> findEmployees(String name, int offset, int lenght);

    public List<Employee> getEmployeesForPage(int offset, int lengh);
    
    public int countForSerach(String name);
    
    public Employee getEmployee(String firstname,String lastname,Double salary,Department department);
    
    public void editEmployee(Employee empl);
}
