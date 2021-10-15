package it.mybatis.manager;

import it.mybatis.Mapper;
import it.mybatis.dto.Employee;
import it.mybatis.service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;

public class EmployeeManager {
    private static final String EMPTY_STRING = "";
    public static final String ANSI_RED = "\u001B[31m";

    private static Mapper mapper = new Mapper();
    private static EmployeeService empService = new EmployeeService();

    public void saveEmployee(BufferedReader reader) throws IOException {
        String firstname = EMPTY_STRING, lastname = EMPTY_STRING, email = EMPTY_STRING;

        System.out.println("\n\nEnter your firstname");
        firstname = reader.readLine();
        System.out.println("\nEnter your lastname");
        lastname = reader.readLine();
        System.out.println("\nEnter your email");
        email = reader.readLine();

        if (!EMPTY_STRING.equals(firstname) && !EMPTY_STRING.equals(lastname) && !EMPTY_STRING.equals(email)) {
            Employee newEmployee = mapper.mapEmployee(firstname, lastname, email);
            if (newEmployee != null)
                empService.saveEmployee(newEmployee);
        } else {
            System.out.println("\n\nNot all fields have been filled!");
        }
    }

    public void updateEmployee(BufferedReader reader) throws IOException {
        System.out.println("\n\n\n\n");
        empService.getEmployees();
        System.out.println("\nWitch employee do you want update?");
        Long employeeId = Long.parseLong(reader.readLine());

        Employee oldEmployee = empService.getEmployById(employeeId);

        if (oldEmployee != null) {
            String firstname = EMPTY_STRING, lastname = EMPTY_STRING, email = EMPTY_STRING;

            System.out.println("\n\nHint: If you want keep the current value, press space!");
            System.out.println("Enter new firstname (old: " + oldEmployee.getFirstname() + ") ");
            firstname = reader.readLine();
            System.out.println("\nEnter new lastname (old: " + oldEmployee.getLastname() + ") ");
            lastname = reader.readLine();
            System.out.println("\nEnter new email (old: " + oldEmployee.getEmail() + ") ");
            email = reader.readLine();

            Employee updatedEmployee = mapper.mapEmployeeWithOldValue(firstname, lastname, email, oldEmployee);

            empService.updateEmployee(updatedEmployee);
        } else {
            System.out.println("\nNo employee found!");
        }
    }

    public void deleteEmployeeById(BufferedReader reader) throws IOException {
        System.out.println("\n\n\n\n");
        empService.getEmployees();
        System.out.println("\nWitch employee do you want delete?");
        Long employeeId = Long.parseLong(reader.readLine());

        System.out.println("\nAre you sure you want to delete the employee?");
        System.out.println("Enter Y or N(any other key): ");
        String check = reader.readLine();
        if (!EMPTY_STRING.equals(check) && "y".equals(check.toLowerCase())) {
            empService.deleteEmployeeById(employeeId);
        } else {
            System.out.println("\nAction stopped!");
        }
    }

    public void getEmployees() {
        empService.getEmployees();
    }
}
