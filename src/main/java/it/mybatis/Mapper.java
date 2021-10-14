package it.mybatis;

import it.mybatis.dto.Person;

import java.sql.SQLException;

public class Mapper {
    private static final String EMPTY_STRING = "";

    public Person mapPersonByClearFields(String firstname, String lastname, String email) {
        Person person = new Person();

        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setEmail(email);

        return person;
    }

    // Map new person with also old value of person
    public Person mapPersonWithOldPerson(String firstname, String lastname, String email, Person oldPerson) {
        Person person = new Person();

        person.setIdPerson(oldPerson.getIdPerson());
        person.setFirstname(!EMPTY_STRING.equals(firstname) ? firstname : oldPerson.getFirstname());
        person.setLastname(!EMPTY_STRING.equals(lastname) ? lastname : oldPerson.getLastname());
        person.setEmail(!EMPTY_STRING.equals(email) ? email : oldPerson.getEmail());

        return person;
    }
}
