package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

// TODO the commonalities does not seem to do have anything to do with contact information but rather
// some kind of validation, perhaps an interface something like Validated might be more suitable.
// Alternatively, Name actually can also be considered a Contact.
public abstract class Contact {

    // TODO Java do not support inheriting fields but even if methods are used instead, static methods cannot be inherited
    // shadowing does not seem like a good solution so perhaps there is a better way.
    // public static final String EXAMPLE = "valid@e.mail";
    // public static final String MESSAGE_EMAIL_CONSTRAINTS =
    //         "Person emails should be 2 alphanumeric/period strings separated by '@'";
    // public static final String EMAIL_VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";

    private final String messageConstraints;
    private final String validationRegEx;

    private final String value;
    private boolean isPrivate;

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Contact(String value, boolean isPrivate, String validationRegEx, String messageConstraints)
            throws IllegalValueException {
        this.isPrivate = isPrivate;
        this.validationRegEx = validationRegEx;
        this.messageConstraints = messageConstraints;

        String trimmedValue = value.trim();
        if (!isValid(trimmedValue)) {
            throw new IllegalValueException(this.messageConstraints);
        }
        this.value = trimmedValue;
    }

    /**
     * Checks if the value is valid based on what type of contact information it is.
     */
    private boolean isValid(String test) {
        return test.matches(validationRegEx);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public String toString() {
        return getValue();
    }

    /**
     * Returns the value of the contact information.
     */
    public String getValue() {
        return value;
    }

}
