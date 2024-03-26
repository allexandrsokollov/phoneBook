import Entities.Contact;
import db.PhoneBook;
import Entities.Number;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        var phoneBook = new PhoneBook();

        var contact_one = new Contact("name_one", "surname_one");
        try {
            contact_one.add_number(new Number("334"));
            contact_one.add_number(new Number("333"));
            contact_one.add_number(new Number("333"));
            contact_one.add_number(new Number("334"));
        } catch (Exception e) {
            System.out.println("Too much numbers in one contact.");
        }

        List<Number> contact_two_numbers = new ArrayList<>();
        var contact_two = new Contact("name_two", "surname_two");
        try {
            contact_two.add_number(new Number("334"));
            contact_two.add_number(new Number("333"));
            contact_two.add_number(new Number("333"));
            contact_two.add_number(new Number("334"));
        } catch (Exception e) {
            System.out.println("Too much numbers in one contact.");
        }

        var contact_three = new Contact("name_three", "surname_three");
        try {
            contact_three.add_number(new Number("325"));
            contact_three.add_number(new Number("326"));
            contact_three.add_number(new Number("327"));
            contact_three.add_number(new Number("328"));

        } catch (Exception exception) {
            System.out.println("Too much numbers in one contact.");
        }

        phoneBook.addContact(contact_one);
        phoneBook.addContact(contact_two);
        phoneBook.addContact(contact_three);


        for (Contact contact : phoneBook.getContacts()) {
            System.out.println(contact.get_full_name());
            for (Number number : contact.getNumbers()) {
                System.out.println(number.get_number());
            }
            System.out.println();
        }
    }
}