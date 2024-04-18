package Entities;

import exceptions.MaxPhoneNumbersAmountException;

import java.util.*;

public class Contact {
    private String name;
    private String surname;
    private final Set<PhoneNumber> numbers;

    public Long contactId;

    private static final int MAX_NUMBERS_COUNT = 3;

    public Contact(Long contactId, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.numbers = new HashSet<>();
        this.contactId = contactId;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.surname + " " + this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void removeNumber(PhoneNumber number) {
        this.numbers.remove(number);
    }

    public void addNumber(PhoneNumber number) throws MaxPhoneNumbersAmountException {
        if (this.numbers.size() >= MAX_NUMBERS_COUNT) {
            throw new MaxPhoneNumbersAmountException("Max amount of numbers is " + MAX_NUMBERS_COUNT);
        }
        if (this.numbers.contains(number)) {
            return;
        }
        this.numbers.add(number);
    }

    public Set<PhoneNumber> getNumbers() {
        return Collections.unmodifiableSet(this.numbers);
    }

    public int getNumbersCount() {
        return this.numbers.size();
    }

    public void updateNumber(PhoneNumber numberToUpdate, PhoneNumber newNumber) throws MaxPhoneNumbersAmountException {
        this.removeNumber(numberToUpdate);
        this.addNumber(newNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.surname, this.numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Contact contactObject)) {
            return false;
        }
        if (!Objects.equals(this.surname, contactObject.surname)) {
            return false;
        }
        return Objects.equals(this.name, contactObject.name);
    }

}
