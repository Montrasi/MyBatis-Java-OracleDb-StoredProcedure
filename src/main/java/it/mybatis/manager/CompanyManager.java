package it.mybatis.manager;

import it.mybatis.Mapper;
import it.mybatis.dto.Company;
import it.mybatis.service.CompanyService;

import java.io.BufferedReader;
import java.io.IOException;

public class CompanyManager {
    private static final String EMPTY_STRING = "";
    public static final String ANSI_RED = "\u001B[31m";

    private static Mapper mapper = new Mapper();
    private static CompanyService cmpService = new CompanyService();

    public void saveCompany(BufferedReader reader) throws IOException {
        String name = EMPTY_STRING;

        System.out.println("\n\nEnter the name");
        name = reader.readLine();

        if (!EMPTY_STRING.equals(name)) {
            Company newCompany = mapper.mapCompany(name);
            if (newCompany != null)
                cmpService.saveCompany(newCompany);
        } else {
            System.out.println("\n\nNo name entered!");
        }
    }

    public void updateCompany(BufferedReader reader) throws IOException {
        String name = EMPTY_STRING;

        System.out.println("\n\n\n\n");
        cmpService.getCompanies();
        System.out.println("\n\nEnter id of company which you want update");
        Long companyId = Long.parseLong(reader.readLine());

        Company oldCompany = cmpService.getCompanyById(companyId);

        System.out.println("\nHint: If you want keep the current value, press space!");
        System.out.println("Enter new name (old: " + oldCompany.getName() + ")");
        name = reader.readLine();

        if (!EMPTY_STRING.equals(name)) {
            Company newCompany = mapper.mapCompany(companyId, name);
            if (newCompany != null)
                cmpService.updateCompany(newCompany);
        } else {
            System.out.println("\n\nNo name entered!");
        }
    }

    public void deleteCompanyById(BufferedReader reader) throws IOException {
        System.out.println("\n\n\n\n");
        cmpService.getCompanies();
        System.out.println("\nWitch company do you want delete?");
        Long companyId = Long.parseLong(reader.readLine());

        System.out.println("\nAre you sure you want to delete the company?");
        System.out.println("Enter Y or N(any other key): ");
        String check = reader.readLine();
        if (!EMPTY_STRING.equals(check) && "y".equals(check.toLowerCase())) {
            cmpService.deleteCompanyById(companyId);
        } else {
            System.out.println("\nAction stopped!");
        }
    }

    public void getCompanies() {
        cmpService.getCompanies();
    }
}
