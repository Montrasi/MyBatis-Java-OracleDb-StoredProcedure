package it.mybatis.service;

import it.mybatis.configuration.MyBatisUtils;
import it.mybatis.dto.Person;
import it.mybatis.mapper.PersonMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PersonService {

    public void insertPerson(Person person) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            Long currentId = getNextIdOfTable();
            if (currentId != null) {
                currentId = currentId + 1L;
                person.setIdPerson(currentId);
            }
            mapper.insertPerson(person);
            sqlSession.commit();
        }
    }

    public Person getPersonById(Long idPerson) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            return mapper.getPersonById(idPerson);
        }
    }

    private Long getNextIdOfTable(){
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            return mapper.getNextIdOfTable();
        }
    }

    public List<Person> getPersons() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            return mapper.getPersons();
        }
    }

    public void updatePerson(Person person) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            mapper.updatePerson(person);
            sqlSession.commit();
        }
    }

    public void deletePerson(Long idPerson) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            mapper.deletePerson(idPerson);
            sqlSession.commit();
        }
    }

    public Person checkExistPersonByMail(String email) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            return mapper.checkExistPersonByMail(email);
        }
    }
}
