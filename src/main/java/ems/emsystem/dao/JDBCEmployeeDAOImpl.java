/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.emsystem.dao;

import ems.emsystem.domain.Department;
import ems.emsystem.domain.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class JDBCEmployeeDAOImpl implements EmployeeDAO {

    private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO Employees (FIRSTNAME,LASTNAME,SALARY,BIRTHDATE,ACTIVE,DEPARTMENT) VALUES(?,?,?,?,?,?)";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM Employees WHERE ID=?";
    private static final String SQL_SELECT_EMPLOYEES = "SELECT * FROM Employees";
    private static final String SQL_SELECT_EMPLOYEE = "SELECT * FROM Employees WHERE ID = ?";
    private static final String SQL_FIND_EMPLOYEE = "SELECT * FROM Employees WHERE FIRSTNAME LIKE ?";
    private static final String SQL_SELECT_EMPLOYEE_PAGINATION = "SELECT * FROM Employees LIMIT ? , ?";
    private static final String SQL_FIND_EMPLOYEES_CONCAT = "SELECT * FROM Employees WHERE CONCAT(FIRSTNAME,LASTNAME) LIKE ? or CONCAT(LASTNAME,FIRSTNAME) like ? LIMIT ? , ?";
    private static final String SQL_COUNT_ROWS = "SELECT COUNT(*) FROM Employees WHERE CONCAT(FIRSTNAME,LASTNAME) LIKE ? or CONCAT(LASTNAME,FIRSTNAME) like ? ";
    private static final String SQL_GET_EMPLOYEE = "SELECT * FROM Employees WHERE FIRSTNAME LIKE ? AND LASTNAME LIKE ? AND SALARY = ? AND DEPARTMENT = ? ";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE Employees SET FIRSTNAME=?,LASTNAME=?,SALARY=?,BIRTHDATE=?,ACTIVE=?,DEPARTMENT=? WHERE ID=?";

    @Autowired
    private SimpleJdbcTemplate template;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    @Transactional("jdbcTransactionManager")
    public void addEmployee(Employee employee) {
        template.update(SQL_INSERT_EMPLOYEE,
                employee.getFirstname(),
                employee.getLastname(),
                employee.getSalary(),
                employee.getBirthdate(),
                employee.isActive(),
                employee.getDepartment().getId()
        );
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public List<Employee> getAllEmployees() {
        List<Employee> el = null;
        List<Map<String, Object>> queryForList = template.queryForList(SQL_SELECT_EMPLOYEES);
        el = getEmployeesFromList(queryForList);
        return el;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public void deleteEmployee(Employee employee) {
        template.update(SQL_DELETE_EMPLOYEE, employee.getId());
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public void deleteEmployee(Long id) {
        template.update(SQL_DELETE_EMPLOYEE, id);
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public Employee getEmployee(Long id) {
        return template.queryForObject(SQL_SELECT_EMPLOYEE, new ParameterizedRowMapper<Employee>() {

            @Override
            public Employee mapRow(ResultSet rs, int i) throws SQLException {
                return getEmployeeFromResultSet(rs);
            }
        }, id);
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public List<Employee> findEmployees(String name, int offset, int lenght) {
        List<Employee> el = null;
        List<Map<String, Object>> queryForList = template.queryForList(SQL_FIND_EMPLOYEES_CONCAT, name, name, offset, lenght);
        el = getEmployeesFromList(queryForList);
        return el;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public List<Employee> getEmployeesForPage(int offset, int lenght) {
        List<Employee> el = null;
        List<Map<String, Object>> queryForList = template.queryForList(SQL_SELECT_EMPLOYEE_PAGINATION, offset, lenght);
        el = getEmployeesFromList(queryForList);
        return el;
    }

    private Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee em = new Employee();
        em.setId(rs.getLong("ID"));
        em.setActive(rs.getBoolean("ACTIVE"));
        em.setBirthdate(rs.getDate("BIRTHDATE"));
        em.setFirstname(rs.getString("FIRSTNAME"));
        em.setLastname(rs.getString("LASTNAME"));
        em.setSalary(rs.getDouble("SALARY"));
        long dep_id = rs.getLong("DEPARTMENT");
        Department dep = departmentDAO.getDepartment(dep_id);
        em.setDepartment(dep);
        return em;
    }

    private List<Employee> getEmployeesFromList(List<Map<String, Object>> queryForList) {
        List<Employee> el = null;
        if (queryForList.size() > 0) {
            el = new ArrayList<Employee>();
            for (int i = 0; i < queryForList.size(); i++) {
                Employee em = new Employee();
                Map<String, Object> map = queryForList.get(i);
                Department department = departmentDAO.getDepartment((Long) map.get("DEPARTMENT"));
                em.setId((Long) map.get("ID"));
                em.setFirstname((String) map.get("FIRSTNAME"));
                em.setLastname((String) map.get("LASTNAME"));
                em.setSalary((Double) map.get("SALARY"));
                em.setBirthdate((Date) map.get("BIRTHDATE"));
                em.setActive((Boolean) map.get("ACTIVE"));
                em.setDepartment(department);
                el.add(em);
            }
        }
        return el;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public int countForSerach(String name) {
        int res = template.queryForInt(SQL_COUNT_ROWS, name, name);
        return res;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public Employee getEmployee(String firstname, String lastname, Double salary, Department department) {
        List<Map<String, Object>> queryForList = template.queryForList(SQL_GET_EMPLOYEE, firstname, lastname, salary, department.getId());
        List<Employee> employeesFromList = getEmployeesFromList(queryForList);
        Employee res = employeesFromList.get((employeesFromList.size() - 1));
        return res;
    }

    @Override
    @Transactional("jdbcTransactionManager")
    public void editEmployee(Employee employee) {
        template.update(SQL_UPDATE_EMPLOYEE,
                employee.getFirstname(),
                employee.getLastname(),
                employee.getSalary(),
                employee.getBirthdate(),
                employee.isActive(),
                employee.getDepartment().getId(),
                employee.getId());
    }

}
