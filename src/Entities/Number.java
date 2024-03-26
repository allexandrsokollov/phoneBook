package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Number {
    private String number;


    public Number(String number) {
        this.number = number;

    }

    public String get_number() {
        return this.number;
    }

    public void change_number(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.get_number_digits(this));
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Number)) {
            return false;
        }

        var current_number_digits = this.get_number_digits(this);
        var object_number_digits = this.get_number_digits((Number) o);

        if (current_number_digits.size() != object_number_digits.size()) {
            return false;
        }

        for (int index = 0; index < current_number_digits.size(); index++) {
            if (!Objects.equals(current_number_digits.get(index), object_number_digits.get(index))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> get_number_digits(Number number) {
        var result = new ArrayList<Integer>();

        for (char ch : number.get_number().toCharArray()) {
            if (Character. isDigit(ch)) {
                result.add((int) ch);
            }
        }
        return result;
    }
}
