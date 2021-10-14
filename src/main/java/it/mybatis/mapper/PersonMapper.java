package it.mybatis.mapper;

import it.mybatis.dto.Person;

import java.util.List;

public interface PersonMapper {

    void insertPerson(Person person);

    Person getPersonById(Long idPerson);

    Long getNextIdOfTable();

    List<Person> getPersons();

    void updatePerson(Person person);

    void deletePerson(Long idPerson);

    Person checkExistPersonByMail(String email);

}
