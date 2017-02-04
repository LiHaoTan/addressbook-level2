package seedu.addressbook.data.person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person addresses should contain the block number, street, unit and postal code separated by commas.";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final Pattern ADDRESS_FORMAT =
            Pattern.compile("(?<block>[^,]+),(?<street>[^,]+),(?<unit>[^,]+),(?<postalCode>[^,]+)");

    private final String value;
    private boolean isPrivate;
    
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        
//        if (!isValidAddress(trimmedAddress)) {
//            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
//        }
        
        if (!canParseAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
     
        this.value = trimmedAddress;
    }
    
    /**
     * Try parsing address and create the address components if the format matches. 
     * @return true if a given string is a valid address format.
     * @throws IllegalValueException if the parsed address components are not valid.
     */
    private boolean canParseAddress(String trimmedAddress) throws IllegalValueException {
        final Matcher matcher = ADDRESS_FORMAT.matcher(trimmedAddress);
        if (!matcher.matches()) {
            return false;
        }
        
        //System.out.println("Passed!!!!");

        this.block = new Block(matcher.group("block"));
        this.street = new Street(matcher.group("street"));
        this.unit = new Unit(matcher.group("unit"));
        this.postalCode = new PostalCode(matcher.group("postalCode"));

        return true;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.getValue().equals(((Address) other).getValue())); // state check
        // TODO improve by using the objects to compare instead of using the string value
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Returns the value
     */
    public String getValue() {
        return block.value + ", " + street.value + ", " + unit.value + ", " + postalCode.value;
    }
}
