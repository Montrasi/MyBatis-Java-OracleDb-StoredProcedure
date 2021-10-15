package it.mybatis.service;

import it.mybatis.Utils;
import it.mybatis.configuration.MyBatisUtils;
import it.mybatis.dto.Contract;
import it.mybatis.mapper.ContractMapper;
import it.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ContractService {
    private Utils utils = new Utils();

    public void getContracts() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            ContractMapper mapper = sqlSession.getMapper(ContractMapper.class);
            List<Contract> list = mapper.getContracts();
            if (list != null && !list.isEmpty()) {
                utils.printContract(list);
            }
        }
    }

    public void saveContract(Contract newContract) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            ContractMapper mapper = sqlSession.getMapper(ContractMapper.class);
            mapper.saveContract(newContract);
            sqlSession.commit();
        }
    }

    public String checkEmployeeWork(Long employeeId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            ContractMapper mapper = sqlSession.getMapper(ContractMapper.class);
            return mapper.checkEmployeeWork(employeeId);
        }
    }

    public Contract getContractById(Long contractId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            ContractMapper mapper = sqlSession.getMapper(ContractMapper.class);
            return mapper.getContractById(contractId);
        }
    }

    public void updateContract(Contract contract) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            ContractMapper mapper = sqlSession.getMapper(ContractMapper.class);
            mapper.updateContract(contract);
            sqlSession.commit();
        }
    }

    public void deleteContractById(Long contractId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            ContractMapper mapper = sqlSession.getMapper(ContractMapper.class);
            mapper.deleteContractById(contractId);
            sqlSession.commit();
        }
    }

}
