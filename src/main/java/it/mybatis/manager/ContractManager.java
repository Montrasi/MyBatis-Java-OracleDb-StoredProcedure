package it.mybatis.manager;

import it.mybatis.Mapper;
import it.mybatis.dto.Contract;
import it.mybatis.service.CompanyService;
import it.mybatis.service.ContractService;
import it.mybatis.service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContractManager {
    private static final String EMPTY_STRING = "";
    public static final String ANSI_RED = "\u001B[31m";

    private static Mapper mapper = new Mapper();
    private static ContractService cntService = new ContractService();
    private static EmployeeService empService = new EmployeeService();
    private static CompanyService cmpService = new CompanyService();
    private static SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");

    public void saveContract(BufferedReader reader) throws IOException, ParseException {
        Long companyId = null, employeeId = null;
        String strDate = EMPTY_STRING;
        Date hireDate = null;

        System.out.println("\n\n\n\n");
        cmpService.getCompanies();
        System.out.println("\nWhich company? (Enter his id)");
        companyId = Long.parseLong(reader.readLine());

        System.out.println("\n\n\n\n");
        empService.getEmployees();
        System.out.println("\nWhich employee? (Enter his id)");
        employeeId = Long.parseLong(reader.readLine());

        System.out.println("\nWhich is the hire date? (Enter dd-mm-yyyy , default is today)");
        strDate = reader.readLine();
        if (!EMPTY_STRING.equals(strDate)) {
            hireDate = simpleDate.parse(strDate);
        } else {
            hireDate = new Date();
        }

        if (hireDate != null) {
            // empCheck used to know if employee already have a work
            String empCheck = cntService.checkEmployeeWork(employeeId);
            if (empCheck == null) {
                Contract contract = mapper.mapContract(companyId, employeeId, hireDate);
                cntService.saveContract(contract);
            } else {
                System.out.println(ANSI_RED + empCheck + " already have a work!" + ANSI_RED);
            }
        }
    }

    public void updateContract(BufferedReader reader) throws IOException, ParseException {
        Long contractId = null, employeeId = null;
        String strDate = EMPTY_STRING;
        Date fireDate = null;

        System.out.println("\n\n\n\n");
        cntService.getContracts();
        System.out.println("\nWhich contract? (Enter his id)");
        contractId = Long.parseLong(reader.readLine());

        Contract oldContract = cntService.getContractById(contractId);
        System.out.println("\n\nHint: If you want keep the current value, press space!");
        System.out.println("Enter firing date (Enter dd-mm-yyyy , default is today)");
        strDate = reader.readLine();
        if (!EMPTY_STRING.equals(strDate)) {
            fireDate = simpleDate.parse(strDate);
        } else {
            fireDate = new Date();
        }
        if (fireDate != null) {
            // check if fireDate is after than hireDate
            if (fireDate.after(oldContract.getHireDate())) {
                Contract contract = mapper.mapContractFireDate(fireDate, oldContract);
                cntService.updateContract(contract);
            } else
                System.out.println(ANSI_RED + fireDate + " entered can be before of hire date!" + ANSI_RED);
        }
    }

    public void deleteContractById(BufferedReader reader) throws IOException {
        System.out.println("\n\n\n\n");
        cntService.getContracts();
        System.out.println("\nWitch contract do you want delete?");
        Long contractId = Long.parseLong(reader.readLine());

        System.out.println("\nAre you sure you want to delete the contract?");
        System.out.println("Enter Y or N(any other key): ");
        String check = reader.readLine();
        if (!EMPTY_STRING.equals(check) && "y".equals(check.toLowerCase())) {
            cntService.deleteContractById(contractId);
        } else {
            System.out.println("\nAction stopped!");
        }
    }

    public void getContracts() {
        cntService.getContracts();
    }
}
