package it.mybatis.mapper;

import it.mybatis.dto.Person;

// Copy of class 'PersonMapper' but here the query are made by STORED PROCEDURE
public interface SP_PersonMapper {

    void insertPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long idPerson);
}
