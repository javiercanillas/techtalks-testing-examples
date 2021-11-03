package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class WhiteBoxListTest {

    @Test
    void addOneItem() throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        // accessing private field
        var sizeField = ArrayList.class.getDeclaredField("size");
        // changing private field into public to be able to access it for testing purposes
        sizeField.setAccessible(true);
        Assertions.assertEquals(0, sizeField.get(list));
        Assertions.assertTrue(list.add("a"));
        Assertions.assertEquals(1, sizeField.get(list));

        list = new LinkedList<>();
        var firstField = LinkedList.class.getDeclaredField("first");
        firstField.setAccessible(true);
        Assertions.assertNull(firstField.get(list));
        Assertions.assertTrue(list.add("a"));
        final var first = firstField.get(list);
        Assertions.assertNotNull(first);
        var itemField = first.getClass().getDeclaredField("item");
        itemField.setAccessible(true);
        Assertions.assertEquals("a", itemField.get(first));
    }
}
