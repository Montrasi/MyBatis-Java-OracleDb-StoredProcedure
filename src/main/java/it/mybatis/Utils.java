package it.mybatis;

import it.mybatis.dto.Company;
import it.mybatis.dto.Contract;
import it.mybatis.dto.Employee;

import java.text.SimpleDateFormat;
import java.util.List;

public class Utils {
    private static final String EMPTY_STRING = "";
    private SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");

    public void printCompany(List<Company> list) {
        if (list != null && !list.isEmpty()) {
            String leftAlignFormat = "| %-5s | %-19s |%n";

            System.out.format("+-------+---------------------+%n");
            System.out.format("| ID    | NAME                |%n");
            System.out.format("+-------+---------------------+%n");
            for (Company company : list) {
                System.out.format(leftAlignFormat, company.getCompanyId(), company.getName());
            }
            System.out.format("+-------+---------------------+%n");
        } else {
            System.out.println("No Company registered!");
        }
    }

    public void printEmployee(List<Employee> list) {
        if (list != null && !list.isEmpty()) {
            String leftAlignFormat = "| %-5s | %-12s | %-14s | %-23s |%n";

            System.out.format("+-------+--------------+----------------+-------------------------+%n");
            System.out.format("| ID    | FIRSTNAME    | LASTNAME       | EMAIL                   |%n");
            System.out.format("+-------+--------------+----------------+-------------------------+%n");
            for (Employee employee : list) {
                System.out.format(leftAlignFormat, employee.getEmployeeId(), employee.getFirstname(),
                        employee.getLastname(), employee.getEmail());
            }
            System.out.format("+-------+--------------+----------------+-------------------------+%n");
        } else {
            System.out.println("No Employee registered!");
        }
    }

    public void printContract(List<Contract> list) {
        if (list != null && !list.isEmpty()) {
            String leftAlignFormat = "| %-5s | %-15s | %-20s | %-17s | %-18s |%n";

            System.out.format("+-------+-----------------+----------------------+-------------------+--------------------+%n");
            System.out.format("| ID    | COMPANY NAME    | EMPLOYEE             | HIRE DATE         | FIRING DATE        |%n");
            System.out.format("+-------+-----------------+----------------------+-------------------+--------------------+%n");
            for (Contract contract : list) {
                System.out.format(leftAlignFormat,
                        contract.getContractId(),
                        contract.getCompany().getName(),
                        contract.getEmployee().getLastname() + " " + contract.getEmployee().getFirstname(),
                        simpleDate.format(contract.getHireDate()),
                        contract.getFiringDate() != null ? simpleDate.format(contract.getFiringDate()) : EMPTY_STRING);
            }
            System.out.format("+-------+-----------------+----------------------+-------------------+--------------------+%n");
        } else {
            System.out.println("No Contract registered!");
        }
    }
}
