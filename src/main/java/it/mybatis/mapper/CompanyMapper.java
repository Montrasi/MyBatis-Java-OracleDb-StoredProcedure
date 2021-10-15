package it.mybatis.mapper;

import it.mybatis.dto.Company;

import java.util.List;

public interface CompanyMapper {

    List<Company> getCompanies();

    void saveCompany(Company company);

    Company getCompanyById(Long companyId);

    void updateCompany(Company newCompany);

    void deleteCompanyById(Long companyId);

}
