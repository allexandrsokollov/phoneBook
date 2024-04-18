package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhoneNumber {
    private String number;
    public Long phoneId;


    public PhoneNumber(String number, Long phoneId) {
        this.number = number;
        this.phoneId = phoneId;

    }

    public String getNumber() {
        return this.number;
    }

    public void changeNumber(String number) {
        this.number = number;
    }


    private List<Integer> getNumberDigits(PhoneNumber number) {
        var result = new ArrayList<Integer>();

        for (char ch : number.getNumber().toCharArray()) {
            if (Character. isDigit(ch)) {
                result.add((int) ch);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNumberDigits(this));
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        var current_number_digits = this.getNumberDigits(this);
        var object_number_digits = this.getNumberDigits((PhoneNumber) o);

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
}
