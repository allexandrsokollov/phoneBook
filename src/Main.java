import Entities.Contact;
import Entities.PhoneNumber;
import db.ContactRepository;
import exceptions.MaxPhoneNumbersAmountException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            var phoneBook = new ContactRepository(connection);
            var contactId = (long) 1;
            var contactOne = new Contact(contactId, "name_one", "surname_one");
            try {
                contactOne.addNumber(new PhoneNumber("334", contactId));
                contactOne.addNumber(new PhoneNumber("333", contactId));
                contactOne.addNumber(new PhoneNumber("333", contactId));
                contactOne.addNumber(new PhoneNumber("334", contactId));
            } catch (MaxPhoneNumbersAmountException e) {
                System.out.println("Too much numbers in one contact.");
            }

            var contactId2 = (long) 2;
            var contactTwo = new Contact(contactId2, "name_two", "surname_two");
            try {
                contactTwo.addNumber(new PhoneNumber("334", contactId2));
                contactTwo.addNumber(new PhoneNumber("333", contactId2));
                contactTwo.addNumber(new PhoneNumber("333", contactId2));
                contactTwo.addNumber(new PhoneNumber("334", contactId2));
            } catch (MaxPhoneNumbersAmountException e) {
                System.out.println("Too much numbers in one contact.");
            }

            var contactId3 = (long) 3;

            var contactThree = new Contact(contactId3,"name_three", "surname_three");
            try {
                contactThree.addNumber(new PhoneNumber("325", contactId3));
                contactThree.addNumber(new PhoneNumber("326", contactId3));
                contactThree.addNumber(new PhoneNumber("327", contactId3));
                contactThree.addNumber(new PhoneNumber("328", contactId3));

            } catch (MaxPhoneNumbersAmountException exception) {
                System.out.println("Too much numbers in one contact.");
            }

            phoneBook.saveContact(contactOne);
            phoneBook.saveContact(contactTwo);
            phoneBook.saveContact(contactThree);


            for (Contact contact : phoneBook.getAllContacts()) {
                System.out.println(contact.getFullName());
                for (PhoneNumber number : contact.getNumbers()) {
                    System.out.println(number.getNumber());
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}