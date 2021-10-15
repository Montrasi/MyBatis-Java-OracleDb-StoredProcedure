package it.mybatis.mapper;

import it.mybatis.dto.Employee;

import java.util.List;

public interface EmployeeMapper {

    List<Employee> getEmployees();

    void saveEmployee(Employee newEmployee);

    Employee getEmployeeById(Long employeeId);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Long employeeId);

}
