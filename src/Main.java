import Entities.Contact;
import db.PhoneBook;
import Entities.PhoneNumber;
import exceptions.MaxPhoneNumbersAmountException;


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

        var contactTwo = new Contact("name_two", "surname_two");
        try {
            contactTwo.addNumber(new PhoneNumber("334"));
            contactTwo.addNumber(new PhoneNumber("333"));
            contactTwo.addNumber(new PhoneNumber("333"));
            contactTwo.addNumber(new PhoneNumber("334"));
        } catch (MaxPhoneNumbersAmountException e) {
            System.out.println("Too much numbers in one contact.");
        }

        var contactThree = new Contact("name_three", "surname_three");
        try {
            contactThree.addNumber(new PhoneNumber("325"));
            contactThree.addNumber(new PhoneNumber("326"));
            contactThree.addNumber(new PhoneNumber("327"));
            contactThree.addNumber(new PhoneNumber("328"));

        } catch (MaxPhoneNumbersAmountException exception) {
            System.out.println("Too much numbers in one contact.");
        }

        phoneBook.addContact(contactOne);
        phoneBook.addContact(contactTwo);
        phoneBook.addContact(contactThree);


        for (Contact contact : phoneBook.getContacts()) {
            System.out.println(contact.getFullName());
            for (PhoneNumber number : contact.getNumbers()) {
                System.out.println(number.getNumber());
            }
            System.out.println();
        }
    }
}