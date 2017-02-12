package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
    @Test
    public void isAnyNull() {
        // empty list
        assertFalse(Utils.isAnyNull());

        // Any non-empty list
        assertFalse(Utils.isAnyNull(new Object(), new Object()));
        assertFalse(Utils.isAnyNull("test"));
        assertFalse(Utils.isAnyNull(""));
        assertFalse(Utils.isAnyNull(123));
        assertFalse(Utils.isAnyNull(false));

        // non empty list with just one null at the beginning
        assertTrue(Utils.isAnyNull((Object) null));
        assertTrue(Utils.isAnyNull(null, "", new Object()));
        assertTrue(Utils.isAnyNull(null, new Object(), new Object()));
        assertTrue(Utils.isAnyNull(null, true));

        // non empty list with nulls in the middle
        assertTrue(Utils.isAnyNull(new Object(), null, null, "test"));
        assertTrue(Utils.isAnyNull("", null, new Object()));
        assertTrue(Utils.isAnyNull(false, false, false, null, null, Double.POSITIVE_INFINITY));

        // non empty list with one null as the last element
        assertTrue(Utils.isAnyNull("", new Object(), null));
        assertTrue(Utils.isAnyNull(new Object(), new Object(), null));
        assertTrue(Utils.isAnyNull(true, 123, "HAHA", 'c', 0.576f, 040, null));

        // confirms nulls inside the list are not considered
        List<Object> nullListSingleObject = Arrays.asList((Object) null);
        assertFalse(Utils.isAnyNull(nullListSingleObject));
        List<Integer> nullListMultiObject = Arrays.asList(new Integer[]{1, 2, 3, null, null, 4, 5, 6});
        assertFalse(Utils.isAnyNull(nullListMultiObject));
    }

    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique((Object) null);
        assertAreUnique(1);
        assertAreUnique("");
        assertAreUnique("abc");
        assertAreUnique(false);
        assertAreUnique(0xABCD);

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);
        assertAreUnique(true, false);
        assertAreUnique(0.1, 0.2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(1, new Integer(1));
        assertNotUnique(null, 1, new Integer(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
        assertNotUnique(0.125, 0.125);
        assertNotUnique(0xA, 10);
        assertNotUnique(0345, 229);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
