package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private String name;
    private String surname;
    private final List<Number> numbers;

    private final int MAX_NUMBERS_COUNT = 3;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.numbers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        var contactObject = (Contact) o;
        if (!Objects.equals(this.surname, contactObject.surname)) {
            return false;
        }
        return Objects.equals(this.name, contactObject.name);
    }

    public String get_full_name() {
        var builder = new StringBuilder();
        var name = builder.append(this.name).append(" ").append(this.surname);
        return new String(name);
    }

    public void change_name(String name) {
        this.name = name;
    }

    public void change_surname(String surname) {
        this.surname = surname;
    }

    public void remove_number(Number number) {
        this.numbers.remove(number);
    }

    public void add_number(Number number) throws Exception {
        if (this.numbers.size() >= this.MAX_NUMBERS_COUNT) {
            throw new Exception("Max amount of numbers is " + this.MAX_NUMBERS_COUNT);
        }
        if (this.numbers.contains(number)) {
            return;
        }
        this.numbers.add(number);
    }

    public List<Number> getNumbers() {
        return this.numbers;
    }

    public int getNumbersCount() {
        return this.numbers.size();
    }

    public void updateNumber(Number numberToUpdate, Number newNumber) throws Exception {
        this.remove_number(numberToUpdate);
        this.add_number(newNumber);
    }

}
