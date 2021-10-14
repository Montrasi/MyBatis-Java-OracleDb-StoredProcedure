package it.mybatis;

import it.mybatis.dto.Person;
import it.mybatis.service.PersonService;
import it.mybatis.service.SP_PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MyBatisMain {

    private static final String EMPTY_STRING = "";
    private static Mapper mapper = new Mapper();
    private static PersonService service = new PersonService();
    private static SP_PersonService spService = new SP_PersonService();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String action = EMPTY_STRING;

        while (!action.equals("q")) {
            action = getActionToTake(reader);

            switch (action) {
                case "1":
                    getAllPerson();
                    break;
                case "2":
                    saveNewPerson(reader);
                    break;
                case "3":
                    updatePerson(reader);
                    break;
                case "4":
                    deletePersonById(reader);
                    break;
                default:
                    if (!"q".equals(action)) {
                        System.out.println("\n\nNo action has been taken!\n\n\n\n");
                    }
            }
        }

    }

    public static String getActionToTake(BufferedReader reader) throws IOException {

        System.out.println("\n\n\n\n\nWitch action do you want take?");
        System.out.println("1 - Read data");
        System.out.println("2 - Add new person");
        System.out.println("3 - Update person");
        System.out.println("4 - Delete person");
        return reader.readLine();
    }

    private static void getAllPerson() {

        List<Person> list = service.getPersons();

        if (list != null && !list.isEmpty()) {
            String leftAlignFormat = "| %-5s | %-12s | %-14s | %-23s |%n";

            System.out.format("+-------+--------------+----------------+-------------------------+%n");
            System.out.format("| ID    | FIRSTNAME    | LASTNAME       | EMAIL                   |%n");
            System.out.format("+-------+--------------+----------------+-------------------------+%n");
            for (Person person : list) {
                System.out.format(leftAlignFormat, person.getIdPerson(), person.getFirstname(), person.getLastname(), person.getEmail());
            }
            System.out.format("+-------+--------------+----------------+-------------------------+%n");
        } else {
            System.out.println("No persons registered!");
        }

    }

    public static void saveNewPerson(BufferedReader reader) throws IOException {
        String firstname = EMPTY_STRING, lastname = EMPTY_STRING, email = EMPTY_STRING;

        System.out.println("\n\nEnter your firstname");
        firstname = reader.readLine();
        System.out.println("\nEnter your lastname");
        lastname = reader.readLine();
        System.out.println("\nEnter your email");
        email = reader.readLine();

        Person newPerson = mapper.mapPersonByClearFields(firstname, lastname, email);

        // service.insertPerson(newPerson);
        spService.insertPerson(newPerson); // Stored Procedure
    }

    public static void updatePerson(BufferedReader reader) throws IOException, ClassNotFoundException {

        System.out.println("\n\nWitch user do you want update?");
        Person oldPerson = searchPersonByMail(reader);

        if (oldPerson != null) {
            String firstname = EMPTY_STRING, lastname = EMPTY_STRING, email = EMPTY_STRING;

            System.out.println("\n\nHint: If you want keep the current value, press space!");
            System.out.println("\nEnter new firstname (old: " + oldPerson.getFirstname() + ") ");
            firstname = reader.readLine();
            System.out.println("\nEnter new lastname (old: " + oldPerson.getLastname() + ") ");
            lastname = reader.readLine();
            System.out.println("\nEnter new email (old: " + oldPerson.getEmail() + ") ");
            email = reader.readLine();

            Person updatedPerson = mapper.mapPersonWithOldPerson(firstname, lastname, email, oldPerson);

            // service.updatePerson(updatedPerson);
            spService.updatePerson(updatedPerson); // Stored Procedure
        } else {
            System.out.println("\nNo person found with the mail entered!");
        }

    }

    public static Person searchPersonByMail(BufferedReader reader) throws IOException, ClassNotFoundException {
        System.out.println("Enter email");
        String email = reader.readLine();

        if (!EMPTY_STRING.equals(email)) {
            email = "%" + email + "%";
            Person oldPerson = service.checkExistPersonByMail(email);
            if (oldPerson != null) {
                return oldPerson;
            }
        } else {
            System.out.println("\nNo mail entered!");
        }
        return null;
    }

    public static void deletePersonById(BufferedReader reader) throws IOException, ClassNotFoundException {
        System.out.println("\n\nWitch user do you want delete?");
        Person personToDelete = searchPersonByMail(reader);

        if (personToDelete != null) {
            System.out.println("\n\nAre you sure you want to delete the user?");
            System.out.println("Enter Y or N(any other key): ");
            String check = reader.readLine();
            if (!EMPTY_STRING.equals(check) && "y".equals(check.toLowerCase())) {
                // service.deletePerson(personToDelete.getIdPerson());
                spService.deletePerson(personToDelete.getIdPerson()); // Stored Procedure
            } else {
                System.out.println("\nAction stopped!");
            }
        } else {
            System.out.println("\nUser doesn't exist!");
        }
    }
}
