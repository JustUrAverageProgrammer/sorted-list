package cse250.pa1.tests;

import cse250.pa1.LinkedListNode;
import cse250.pa1.SortedList;

import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.Assert.*;

public class SortedListTests {

    // Testing if insert() works going in order
    @Test
    public void testInsertInOrderElementsInOrder() {
        SortedList<Integer> list = new SortedList<>();

        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }
    }

    // Testing if insert() works going back order
    @Test
    public void testInsertReverseOrderElementsInOrder() {
        SortedList<Integer> list = new SortedList<>();

        for (int i = 9; i >= 0; i--) {
            list.insert(i);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }
    }

    @Test
    public void testInsert(){
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> node1 = list.insert(10);
        LinkedListNode<Integer> node2 = list.insert(40);
        LinkedListNode<Integer> node3 = list.insert(50);
        LinkedListNode<Integer> node4 = list.insert(20);
        LinkedListNode<Integer> node5 = list.insert(60);
        LinkedListNode<Integer> node6 = list.insert(30);
        LinkedListNode<Integer> node7 = list.insert(20);
        LinkedListNode<Integer> node8 = list.insert(40);

        list.remove(node1);
        list.remove(node4);

    }

    // Testing if insert() works going in order starting at hint node
    @Test
    public void testInsertInOrderElementsWithHints() {
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> tail = list.insert(0);
        for (int i = 1; i < 150; i++) {
            tail = list.insert(i, tail);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }
    }

    // Testing get() to return the value not reference at given index
    @Test
    public void testGetValueValidIndex() {
        SortedList<Integer> list = new SortedList<>();

        list.insert(-150);
        list.insert(0);
        list.insert(4);
        list.insert(2344);
        list.insert(43857);
        list.insert(43857);
        list.insert(3464564);

        Integer actual = list.get(5);
        assertEquals(Integer.valueOf(43857), actual);
    }

    // Testing get() to return an exception when it's out of bounds
    @Test
    public void testGetInvalidIndex() {
        SortedList<Integer> list = new SortedList<>();

        for (int i = 0; i < 5; i++) {
            list.insert(i);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> {Integer actual = list.get(23);});
    }

    // Testing insert() to make sure it is setting correctly
    @Test
    public void testInsertSetFields(){
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> insert1 = list.insert(7);
        LinkedListNode<Integer> insert2 = list.insert(7);
        LinkedListNode<Integer> insert3 = list.insert(7);

        System.out.println(list.headNode.get().value);
        System.out.println(list.headNode.get().count);
        System.out.println(list.lastNode.get().value);
        System.out.println(list.lastNode.get().count);
    }

    // Testing remove() correctly updates the fields of SortedList
    @Test
    public void testRemoveUpdatesSortedList(){
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> node1 = list.insert(3);
        LinkedListNode<Integer> node2 = list.insert(70);
        LinkedListNode<Integer> node3 = list.insert(32);
        LinkedListNode<Integer> node4 = list.insert(60);
        LinkedListNode<Integer> node5 = list.insert(70);
        LinkedListNode<Integer> node6 = list.insert(70);

        list.removeN(node6, 3);

        System.out.println(list.headNode.get().value);
        System.out.println(list.headNode.get().next.get().value);
        System.out.println(list.headNode.get().next.get().next.get().value);
        System.out.println(list.lastNode.get().value);

        System.out.println(list.length);
    }

    // Testing findRefBefore when element does exist
    @Test
    public void testFindRefBeforeElementExists(){
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> i1 = list.insert(2);
        LinkedListNode<Integer> i2 = list.insert(4);
        assertEquals(i2, list.findRefBefore(4).get()); //check if node i2 is what is returned by findRefBefore

    }

    // Testing findRefBefore when elem doesn't exist
    @Test
    public void testFindRefBeforeElementNotExists(){
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> i1 = list.insert(2);
        LinkedListNode<Integer> i2 = list.insert(4);
        LinkedListNode<Integer> i3 = list.insert(8);
        assertEquals(i1, list.findRefBefore(3).get()); //check if node i1 is what is returned by findRefBefore
    }

    // Testing findRefBefore when elem doesn't exist and it's smaller than every element
    @Test
    public void testFindRefBeforeElementNotExistsAndTooSmall(){
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> i1 = list.insert(2);
        LinkedListNode<Integer> i2 = list.insert(4);
        LinkedListNode<Integer> i3 = list.insert(8);
        assertEquals(Optional.empty(), list.findRefBefore(1)); //check if node empty is what it returns
    }
}
