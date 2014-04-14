/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.service;

import ems.emsystem.dao.DepartmentDAO;
import ems.emsystem.domain.Department;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Value("${app.rows_on_page}")
    private Integer rows_on_page = 100;

    @Override
    public long addDepartment(Department department) {
        departmentDAO.addDepartment(department);
        long id = departmentDAO.getDepartment(department.getName()).getId();
        return id;
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentDAO.deleteDepartment(department);

    }

    @Override
    public void deleteDepartment(Long id) {
        departmentDAO.deleteDepartment(id);

    }

    @Override
    public Department getDepartment(Long id) {
        return departmentDAO.getDepartment(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDAO.getDepartments();
    }

    @Override
    public List<Department> findDepartments(String name, int page) {
        int from = 0;
        if (page != 0) {
            from = page * rows_on_page;
        }
        return departmentDAO.findDepartments(convertName(name), from, rows_on_page);
    }

    @Override
    public List<Department> getDepartmentsForPage(int page) {
        int from = 0;
        if (page != 0) {
            from = page * rows_on_page;
        }
        return departmentDAO.getDepartmentsForPage(from, rows_on_page);
    }

    @Override
    public Department getDepartment(String name) {
        return departmentDAO.getDepartment(name);
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
    public int pageCount() {
        int rows = departmentDAO.countAllRows();
        int x = rows % rows_on_page;
        int pages = rows / rows_on_page;
        if (x > 0) {
            pages++;
        }
        return pages;
    }

    @Override
    public void editDepartment(Department department) {
        departmentDAO.editDepartment(department);
    }

    @Override
    public int pageCount(String name) {
        int rows = departmentDAO.countRows(name);
        int x = rows % rows_on_page;
        int pages = rows / rows_on_page;
        if (x > 0) {
            pages++;
        }
        return pages;
    }
}
