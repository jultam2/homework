package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class MyListTest {
    private MyList<Integer> myList;

    @BeforeEach
    public void createMyList() {
        myList = new MyList<>();
    }

    @Test
    public void testAddAndGet() {
        myList.add(1);
        myList.add(2);
        myList.add(3);

        assertEquals(1, (int) myList.get(0));
        assertEquals(2, (int) myList.get(1));
        assertEquals(3, (int) myList.get(2));
    }

    @Test
    public void testRemove() {
        myList.add(1);
        myList.add(2);
        myList.add(3);

        int removed = myList.remove(1);

        assertEquals(2, removed);
        assertEquals(2, myList.size());
    }

    @Test
    public void testMap() {
        myList.add(1);
        myList.add(2);
        myList.add(3);

        Function<Integer, Integer> doubleFunction = x -> x * 2;
        MyList<Integer> mappedList = myList.map(doubleFunction);

        assertEquals(2, (int) mappedList.get(0));
        assertEquals(4, (int) mappedList.get(1));
        assertEquals(6, (int) mappedList.get(2));
    }

    @Test
    public void testSize() {
        myList.add(1);
        myList.add(2);
        myList.add(3);

        assertEquals(3, myList.size());
    }

    @Test
    public void getThrowIndexOutOfBoundException() {

        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(-1));
    }

    @Test
    public void firstElementRemoval() {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);

        myList.remove(0);

        assertEquals(2, myList.get(0));
        assertEquals(4, myList.size());
    }

    @Test
    public void lastElementRemoval() {

        int count = 30;
        for (int i = 0; i < count; i++) {
            myList.add(i);
        }
        int previousNumber = myList.get(28);
        //WHEN
        myList.remove(29);
        //THEN
        assertEquals(previousNumber, myList.get(28));
        assertEquals(count - 1, myList.size());
    }

    @Test
    public void testEmptyMyListIterator() {
        MyList<Integer> myList = new MyList<>();
        Iterator<Integer> iterator = myList.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testListIterator() {
        MyList<Integer> myList = new MyList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        Iterator<Integer> iterator = myList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSameInstance() {
        MyList<Integer> myList = new MyList<>();
        myList.add(1);
        myList.add(2);

        assertEquals(myList, myList);
    }

    @Test
    public void testSameContent() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);
        myList1.add(2);

        MyList<Integer> myList2 = new MyList<>();
        myList2.add(1);
        myList2.add(2);

        assertEquals(myList1, myList2);
    }

    @Test
    public void testDifferentContent() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);
        myList1.add(2);

        MyList<Integer> myList2 = new MyList<>();
        myList2.add(3);
        myList2.add(4);

        assertNotEquals(myList1, myList2);
    }

    @Test
    public void testSameHashCodes() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);
        myList1.add(2);

        MyList<Integer> myList2 = new MyList<>();
        myList2.add(1);
        myList2.add(2);

        assertEquals(myList1.hashCode(), myList2.hashCode());
    }

    @Test
    public void testDifferentHashCodes() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);
        myList1.add(2);

        MyList<Integer> myList2 = new MyList<>();
        myList2.add(3);
        myList2.add(4);

        assertNotEquals(myList1.hashCode(), myList2.hashCode());
    }

    @Test
    public void testDifferentSize() {
        MyList<Integer> myList1 = new MyList<>();
        myList1.add(1);

        MyList<Integer> myList2 = new MyList<>();
        myList2.add(1);
        myList2.add(2);

        assertNotEquals(myList1, myList2);
    }

    @Test
    public void testNullObject() {
        MyList<Integer> myList = new MyList<>();
        myList.add(1);

        assertNotEquals(null, myList);
    }

    @Test
    public void testDifferentClassesSameContent() {
        MyList<Integer> myList = new MyList<>();
        myList.add(1);
        myList.add(2);

        MyList<Double> differentClassList = new MyList<>();
        differentClassList.add(1.0);
        differentClassList.add(2.0);

        assertNotEquals(myList, differentClassList);
    }
}

