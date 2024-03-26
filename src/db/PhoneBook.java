package db;

import Entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public void updateContact(Contact toUpdate, Contact newContact){
        this.removeContact(toUpdate);
        this.addContact(newContact);
    }
}
