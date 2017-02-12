package seedu.addressbook.data;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.tag.Tag;

public class Tagging {
    public enum Type { ADD, DELETE }

    private Person person;
    private Tag tag;
    private Type type;

    public Tagging(Person person, Tag tag, Type type) {
        this.person = person;
        this.tag = tag;
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public Tag getTag() {
        return tag;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return (type == Type.ADD ? "+" : "-") + " " + getPerson().getName().fullName + " " + getTag().toString();
    }
}
