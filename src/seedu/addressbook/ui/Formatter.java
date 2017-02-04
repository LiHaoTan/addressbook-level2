package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.*;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class Formatter {
    
    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";
    
    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";
    
    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private static final String MESSAGE_ENTER_COMMAND = LINE_PREFIX + "Enter command: ";

    private static final String MESSAGE_COMMAND_ENTERED = "[Command entered:%s]";
    
    public String formatInputCommandMessage() {
       return MESSAGE_ENTER_COMMAND; 
    }
    public String formatEchoCommandMessage(String fullInputLine) {
        return String.format(MESSAGE_COMMAND_ENTERED, fullInputLine);
    }
    
    public String formatWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return buildMultiLineMessage(DIVIDER, DIVIDER, MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo, DIVIDER);
    }
    
    public String formatGoodbyeMessage() {
        return buildMultiLineMessage(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }
    
    public String formatInitFailedMessage() {
        return buildMultiLineMessage(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    public String formatResultFeedback(String feedbackToUser) {
        return buildMultiLineMessage(feedbackToUser, DIVIDER);
    }

    public String buildMultiLineMessage(String... message) {
        boolean isFirstLine = true;
        final StringBuilder multiLineMessage = new StringBuilder();
        
        for (String m : message) {
            if (isFirstLine) {
                isFirstLine = false;
            } else {
                multiLineMessage.append("\n");
            }

            multiLineMessage.append(m);
        }
        return multiLineMessage.toString();
    }

    public String decorateMessage(String message) {
        return LINE_PREFIX + message.replace("\n", LS + LINE_PREFIX);
    }

    /**
     * Formats a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    public String formatPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        return getIndexedListForViewing(formattedPersons);
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
