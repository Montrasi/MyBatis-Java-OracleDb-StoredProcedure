package it.mybatis;

import it.mybatis.manager.CompanyManager;
import it.mybatis.manager.ContractManager;
import it.mybatis.manager.EmployeeManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyBatisMain {

    private static final String EMPTY_STRING = "";
    public static final String ANSI_RED = "\u001B[31m";

    private static Mapper mapper = new Mapper();
    private static EmployeeManager empManager = new EmployeeManager();
    private static CompanyManager cmpManager = new CompanyManager();
    private static ContractManager cntManager = new ContractManager();

    private static SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");


    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String action = EMPTY_STRING;

        while (!action.equals("q")) {
            action = getActionToTake(reader);

            switch (action) {
                case "1":
                    getInfo(reader);
                    break;
                case "2":
                    saveNewInfo(reader);
                    break;
                case "3":
                    updateInfo(reader);
                    break;
                case "4":
                    deleteInfo(reader);
                    break;
                default:
                    if (!"q".equals(action)) {
                        System.out.println("\n\nNo action has been taken!\n\n\n\n");
                    }
            }
        }

    }

    public static String getActionToTake(BufferedReader reader) throws IOException {

        System.out.println("\n\n\n\n\nWitch action do you want take? (Enter 'Q' to exit)");
        System.out.println("1 - Read data");
        System.out.println("2 - Add Data");
        System.out.println("3 - Update person");
        System.out.println("4 - Delete person");
        return reader.readLine();
    }

    private static void getInfo(BufferedReader reader) throws IOException {
        String chose = getSubjectOfAction(reader);

        if (chose != null) {
            switch (chose) {
                case "1":
                    cmpManager.getCompanies();
                    break;
                case "2":
                    empManager.getEmployees();
                    break;
                case "3":
                    cntManager.getContracts();
                    break;
            }
        }
    }

    private static String getSubjectOfAction(BufferedReader reader) throws IOException {

        System.out.println("\nWhat do you want see?");
        System.out.println("1 - Company");
        System.out.println("2 - Employee");
        System.out.println("3 - Company, Contract, Employee");
        String chose = reader.readLine();

        if (!EMPTY_STRING.equals(chose)) {
            return chose;
        } else {
            System.out.println("No chose took!");
        }
        return null;
    }

    /* -------------------------------- SAVE -------------------------------- */
    private static void saveNewInfo(BufferedReader reader) throws IOException, ParseException {
        String chose = getSubjectOfAction(reader);

        if (chose != null) {
            switch (chose) {
                case "1":
                    cmpManager.saveCompany(reader);
                    break;
                case "2":
                    empManager.saveEmployee(reader);
                    break;
                case "3":
                    cntManager.saveContract(reader);
                    break;
            }
        }
    }

    /* -------------------------------- UPDATE -------------------------------- */
    private static void updateInfo(BufferedReader reader) throws IOException, ParseException {
        String chose = getSubjectOfAction(reader);

        if (chose != null) {
            switch (chose) {
                case "1":
                    cmpManager.updateCompany(reader);
                    break;
                case "2":
                    empManager.updateEmployee(reader);
                    break;
                case "3":
                    cntManager.updateContract(reader);
                    break;
            }
        }
    }

    /* -------------------------------- DELETE -------------------------------- */
    private static void deleteInfo(BufferedReader reader) throws IOException {
        String chose = getSubjectOfAction(reader);

        if (chose != null) {
            switch (chose) {
                case "1":
                    cmpManager.deleteCompanyById(reader);
                    break;
                case "2":
                    empManager.deleteEmployeeById(reader);
                    break;
                case "3":
                    cntManager.deleteContractById(reader);
                    break;
            }
        }
    }

}
