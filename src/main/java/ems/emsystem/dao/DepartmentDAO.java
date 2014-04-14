/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.dao;

import ems.emsystem.domain.Department;
import java.util.List;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
public interface DepartmentDAO {

    public void addDepartment(Department department);

    public void deleteDepartment(Department department);

    public void deleteDepartment(Long id);

    public Department getDepartment(Long id);

    public List<Department> getDepartments();

    public List<Department> findDepartments(String name, int offset, int lenght);

    public List<Department> getDepartmentsForPage(int offset, int lenght);

    public Department getDepartment(String name);

    public int countAllRows();

    public int countRows(String name);

    public void editDepartment(Department department);
}
