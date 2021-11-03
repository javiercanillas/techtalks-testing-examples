package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BlackBoxListTest {

    @Test
    void addOneItem() {
        List<String> list = new ArrayList<>();
        Assertions.assertTrue(list.add("a"));

        list = new LinkedList<>();
        Assertions.assertTrue(list.add("a"));
    }
}
