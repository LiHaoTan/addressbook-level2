package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone extends Contact {

    public static final String EXAMPLE = "123456789";
    public static final String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain numbers";
    public static final String PHONE_VALIDATION_REGEX = "\\d+";

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Phone(String phone, boolean isPrivate) throws IllegalValueException {
        super(phone, isPrivate, PHONE_VALIDATION_REGEX, MESSAGE_PHONE_CONSTRAINTS);
    }

    /**
     * Checks if a given string is a valid person phone number.
     */
    // TODO because this method is static, the behavior from Contact.isValid instance method cannot be used
    // To figure out if there is a better way to not repeat code.
    public static boolean isValidPhone(String test) {
        return test.matches(PHONE_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && this.value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
