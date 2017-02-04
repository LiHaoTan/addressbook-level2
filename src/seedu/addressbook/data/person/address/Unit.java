package seedu.addressbook.data.person.address;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address unit in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnit(String)}
 */
public class Unit {

    public static final String EXAMPLE = "#12-34";
    public static final String MESSAGE_UNIT_CONSTRAINTS =
            "Unit should start with # and then with numbers separated by '-'";
    public static final String UNIT_VALIDATION_REGEX = "#\\d+-\\d+";
    
    public final String value;

    public Unit(String unit) throws IllegalValueException {
        String trimmedUnit = unit.trim();
        if (!isValidUnit(trimmedUnit)) {
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }
        this.value = trimmedUnit; 
    }
    
    /**
     * Checks if a given string is a valid person email.
     */
    public static boolean isValidUnit(String test) {
        return test.matches(UNIT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && this.value.equals(((Unit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
