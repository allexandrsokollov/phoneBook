import Entities.Contact;
import db.PhoneBook;
import Entities.PhoneNumber;
import exceptions.MaxPhoneNumbersAmountException;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        var phoneBook = new PhoneBook();

        var contactOne = new Contact("name_one", "surname_one");
        try {
            contactOne.addNumber(new PhoneNumber("334"));
            contactOne.addNumber(new PhoneNumber("333"));
            contactOne.addNumber(new PhoneNumber("333"));
            contactOne.addNumber(new PhoneNumber("334"));
        } catch (MaxPhoneNumbersAmountException e) {
            System.out.println("Too much numbers in one contact.");
        }

        List<PhoneNumber> contact_two_numbers = new ArrayList<>();
        var contact_two = new Contact("name_two", "surname_two");
        try {
            contact_two.addNumber(new PhoneNumber("334"));
            contact_two.addNumber(new PhoneNumber("333"));
            contact_two.addNumber(new PhoneNumber("333"));
            contact_two.addNumber(new PhoneNumber("334"));
        } catch (MaxPhoneNumbersAmountException e) {
            System.out.println("Too much numbers in one contact.");
        }

        var contact_three = new Contact("name_three", "surname_three");
        try {
            contact_three.addNumber(new PhoneNumber("325"));
            contact_three.addNumber(new PhoneNumber("326"));
            contact_three.addNumber(new PhoneNumber("327"));
            contact_three.addNumber(new PhoneNumber("328"));

        } catch (MaxPhoneNumbersAmountException exception) {
            System.out.println("Too much numbers in one contact.");
        }

        phoneBook.addContact(contactOne);
        phoneBook.addContact(contact_two);
        phoneBook.addContact(contact_three);


        for (Contact contact : phoneBook.getContacts()) {
            System.out.println(contact.getFullName());
            for (PhoneNumber number : contact.getNumbers()) {
                System.out.println(number.getNumber());
            }
            System.out.println();
        }
    }
}