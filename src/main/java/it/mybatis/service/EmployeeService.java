package it.mybatis.service;

import it.mybatis.Utils;
import it.mybatis.configuration.MyBatisUtils;
import it.mybatis.dto.Employee;
import it.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmployeeService {
    private final Utils utils = new Utils();

    public void getEmployees() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> list = mapper.getEmployees();
            if (list != null && !list.isEmpty()) {
                utils.printEmployee(list);
            }
        }
    }

    public void saveEmployee(Employee newEmployee) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.saveEmployee(newEmployee);
            sqlSession.commit();
        }
    }

    public Employee getEmployById(Long employeeId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            return mapper.getEmployeeById(employeeId);
        }
    }

    public void updateEmployee(Employee updateEmployee) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.updateEmployee(updateEmployee);
            sqlSession.commit();
        }
    }

    public void deleteEmployeeById(Long employeeId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.deleteEmployeeById(employeeId);
            sqlSession.commit();
        }
    }

}
