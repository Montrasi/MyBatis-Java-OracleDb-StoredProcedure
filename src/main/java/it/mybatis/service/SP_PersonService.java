package it.mybatis.service;

import it.mybatis.configuration.MyBatisUtils;
import it.mybatis.dto.Person;
import it.mybatis.mapper.PersonMapper;
import it.mybatis.mapper.SP_PersonMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

// Copy of class 'PersonService' but here the query are made by STORED PROCEDURE
public class SP_PersonService {

    public void insertPerson(Person person) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            SP_PersonMapper mapper = sqlSession.getMapper(SP_PersonMapper.class);
            mapper.insertPerson(person);
            sqlSession.commit();
        }
    }

    public void updatePerson(Person person) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            SP_PersonMapper mapper = sqlSession.getMapper(SP_PersonMapper.class);
            mapper.updatePerson(person);
            sqlSession.commit();
        }
    }

    public void deletePerson(Long idPerson) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            SP_PersonMapper mapper = sqlSession.getMapper(SP_PersonMapper.class);
            mapper.deletePerson(idPerson);
            sqlSession.commit();
        }
    }
}
