package it.mybatis;

import it.mybatis.dto.Company;
import it.mybatis.dto.Contract;
import it.mybatis.dto.Employee;

import java.util.Date;

public class Mapper {
    private static final String EMPTY_STRING = "";

    public Company mapCompany(String name) {
        Company company = new Company();

        company.setName(name);

        return company;
    }

    public Company mapCompany(Long companyId, String name) {
        Company company = new Company();

        company.setCompanyId(companyId);
        company.setName(name);

        return company;
    }

    public Employee mapEmployee(String firstname, String lastname, String email) {
        Employee employee = new Employee();

        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setEmail(email);

        return employee;
    }

    public Contract mapContract(Long companyId, Long employeeId, Date hireDate) {
        Contract contract = new Contract();
        Company company = new Company();
        Employee employee = new Employee();

        company.setCompanyId(companyId);
        employee.setEmployeeId(employeeId);

        contract.setCompany(company);
        contract.setEmployee(employee);
        contract.setHireDate(hireDate);

        return contract;
    }


    // Map new person with also old value of person
    public Employee mapEmployeeWithOldValue(String firstname, String lastname, String email, Employee oldEmployee) {
        Employee employee = new Employee();

        employee.setEmployeeId(oldEmployee.getEmployeeId());
        employee.setFirstname(!EMPTY_STRING.equals(firstname) ? firstname : oldEmployee.getFirstname());
        employee.setLastname(!EMPTY_STRING.equals(lastname) ? lastname : oldEmployee.getLastname());
        employee.setEmail(!EMPTY_STRING.equals(email) ? email : oldEmployee.getEmail());

        return employee;
    }

    public Contract mapContractFireDate(Date fireDate, Contract oldContract) {
        Contract contract = new Contract();

        contract.setContractId(oldContract.getContractId());
        contract.setHireDate(oldContract.getHireDate());
        contract.setFiringDate(fireDate);

        contract.setCompany(oldContract.getCompany());
        contract.setEmployee(oldContract.getEmployee());

        return contract;
    }
}
