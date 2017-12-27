package org.lxp.java8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudyListTest {
    private static List<String> list;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
    }

    @Test
    public void shouldRemoveAllElments() throws Exception {
        Assert.assertTrue(StudyList.removeIfNotEquals(list, "d"));
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void shouldReturnA() throws Exception {
        Assert.assertTrue(StudyList.removeIfNotEquals(list, "a"));
        Assert.assertEquals("[a]", list.toString());
    }

    @Test
    public void shouldReturnC() throws Exception {
        Assert.assertTrue(StudyList.removeIfNotEquals(list, "c"));
        Assert.assertEquals("[c]", list.toString());
    }

    @Test
    public void testRemoveIfEquals() throws Exception {
        Assert.assertTrue(StudyList.removeIfEquals(list, "c"));
        Assert.assertEquals("[a, b]", list.toString());

        Assert.assertFalse(StudyList.removeIfEquals(list, "d"));
        Assert.assertEquals("[a, b]", list.toString());
    }

    @Test
    public void testPlusOneAtFirstChar() throws Exception {
        StudyList.plusOneAtFirstChar(list);
        Assert.assertEquals("[b, c, d]", list.toString());
    }

    @Test
    public void testReverse() throws Exception {
        StudyList.reverse(list);
        Assert.assertEquals("[c, b, a]", list.toString());
    }

}
