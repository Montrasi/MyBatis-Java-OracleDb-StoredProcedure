package it.mybatis.service;

import it.mybatis.Utils;
import it.mybatis.configuration.MyBatisUtils;
import it.mybatis.dto.Company;
import it.mybatis.mapper.CompanyMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CompanyService {
    private final Utils utils = new Utils();

    public void getCompanies() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
            List<Company> list = mapper.getCompanies();
            if (list != null && !list.isEmpty()) {
                utils.printCompany(list);
            }
        }
    }

    public void saveCompany(Company newCompany) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
            mapper.saveCompany(newCompany);
            sqlSession.commit();
        }
    }

    public Company getCompanyById(Long companyId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
            return mapper.getCompanyById(companyId);
        }
    }

    public void updateCompany(Company newCompany) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
            mapper.updateCompany(newCompany);
            sqlSession.commit();
        }
    }

    public void deleteCompanyById(Long companyId) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
            mapper.deleteCompanyById(companyId);
            sqlSession.commit();
        }
    }

}
