/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.dao;

import ems.emsystem.domain.Department;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ILYA_GOLOVACHEV
 */
public class JDBCDepartmentDAOImpl implements DepartmentDAO {

    private static final String SQL_INSERT_DEPARTMENT = "INSERT INTO DEPARTMENTS(NAME,DESCRIPTION) VALUES(?,?)";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM DEPARTMENTS WHERE ID = ?";
    private static final String SQL_SELECT_DEPARTMENT = "SELECT * FROM DEPARTMENTS WHERE ID = ?";
    private static final String SQL_SELECT_DEPARTMENTS = "SELECT * FROM DEPARTMENTS";
    private static final String SQL_FIND_DEPARTMENTS_CONCAT = "SELECT * FROM DEPARTMENTS WHERE CONCAT(NAME,DESCRIPTION) LIKE ? or CONCAT(DESCRIPTION,NAME) like ? LIMIT ? , ?";
    private static final String SQL_SELECT_DEPARTMENTS_FOR_PAGE = "SELECT * FROM DEPARTMENTS LIMIT ? , ?";
    private static final String SQL_GET_DEPARTMENT = "SELECT * FROM DEPARTMENTS WHERE NAME LIKE ?";
    private static final String SQL_COUNT_ROWS = "SELECT COUNT(*) FROM DEPARTMENTS";
    private static final String SQL_COUNT_ROWS_FOR_NAME = "SELECT COUNT(*) FROM DEPARTMENTS WHERE NAME LIKE ?";
    private static final String SQL_EDIT_DEPARTMENT = "UPDATE DEPARTMENTS SET NAME=?,DESCRIPTION=? WHERE ID=?";

    @Autowired
    private SimpleJdbcTemplate template;

    @Override
    @Transactional("jdbcTransactionManager")
    public void addDepartment(Department department) {
        template.update(SQL_INSERT_DEPARTMENT, department.getName(), department.getDescription());
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public void deleteDepartment(Department department) {
        template.update(SQL_DELETE_DEPARTMENT, department.getId());
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public void deleteDepartment(Long id) {
        template.update(SQL_DELETE_DEPARTMENT, id);
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public Department getDepartment(Long id) {
        return template.queryForObject(SQL_SELECT_DEPARTMENT, new ParameterizedRowMapper<Department>() {

            @Override
            public Department mapRow(ResultSet rs, int i) throws SQLException {
                return getDepartmentFromResultSet(rs);
            }

        }, id);
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public List<Department> getDepartments() {
        List<Department> depList = null;
        List<Map<String, Object>> queryForList = template.queryForList(SQL_SELECT_DEPARTMENTS);
        depList = getDepartmentsFromList(queryForList);
        return depList;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public List<Department> findDepartments(String name, int offset, int lenght) {
        List<Department> depList = null;
        List<Map<String, Object>> queryForList = template.queryForList(SQL_FIND_DEPARTMENTS_CONCAT, name, name, offset, lenght);
        depList = getDepartmentsFromList(queryForList);
        return depList;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public List<Department> getDepartmentsForPage(int offset, int lenght) {
        List<Department> depList = null;
        List<Map<String, Object>> queryForList = template.queryForList(SQL_SELECT_DEPARTMENTS_FOR_PAGE, offset, lenght);
        depList = getDepartmentsFromList(queryForList);
        return depList;
    }
    
    private List<Department> getDepartmentsFromList(List<Map<String, Object>> queryForList) {
        List<Department> depList = null;
        if (queryForList.size() > 0) {
            depList = new ArrayList<Department>();
            for (int i = 0; i < queryForList.size(); i++) {
                Department dep = new Department();
                Map<String, Object> get = queryForList.get(i);
                dep.setId((Long) get.get("ID"));
                dep.setName((String) get.get("NAME"));
                dep.setDescription((String) get.get("DESCRIPTION"));
                depList.add(dep);
            }
        }
        return depList;
    }

    private Department getDepartmentFromResultSet(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getLong("ID"));
        dep.setName(rs.getString("NAME"));
        dep.setDescription(rs.getString("DESCRIPTION"));
        return dep;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public Department getDepartment(String name) {
        int countRows = countRows(name);
        if (countRows > 0) {
            return template.queryForObject(SQL_GET_DEPARTMENT, new ParameterizedRowMapper<Department>() {

                @Override
                public Department mapRow(ResultSet rs, int i) throws SQLException {
                    return getDepartmentFromResultSet(rs);
                }
            }, name);
        } else {
            return null;
        }
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public int countAllRows() {
        int res = template.queryForInt(SQL_COUNT_ROWS);
        return res;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public void editDepartment(Department department) {
        template.update(SQL_EDIT_DEPARTMENT, department.getName(), department.getDescription(), department.getId());
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public int countRows(String name) {
        return template.queryForInt(SQL_COUNT_ROWS_FOR_NAME, name);
    }

}
