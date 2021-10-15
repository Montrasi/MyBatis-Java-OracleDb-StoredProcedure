package it.mybatis.mapper;

import it.mybatis.dto.Contract;

import java.util.List;

public interface ContractMapper {

    List<Contract> getContracts();

    void saveContract(Contract newContract);

    String checkEmployeeWork(Long employeeId);

    Contract getContractById(Long contractId);

    void updateContract(Contract contract);

    void deleteContractById(Long contractId);

}
