package cse250.pa1;

import cse250.pa1.LinkedListNode;
import java.util.Iterator;
import java.util.Optional;

/**
 * A linked list that stores its elements in sorted order.
 * <p>
 * When an element is inserted into the list (or updated), it is positioned such that
 * <tt>node.next</tt> is the next greater value being stored in the list and <tt>node.prev</tt> is
 * the next lesser value being stored in the list. Duplicate values are stored in a single list
 * node, with the node also holding a count of the number of elements with that value.
 * <p>
 * SortedList also has "hinted" variants of several methods, where the caller may provide a
 * reference to a node that is close to the search term in the sorted list.  If this is actually the
 * case, the runtime of these methods, which is normally linear <i>in the size of the list</i> will
 * drop to linear in the number of records between the hint and the search term.
 *
 * @param <T> The type of value stored in the list
 */
public class SortedList<T extends Comparable<T>> implements Iterable<T> {
    /**
     * Reference to the first node in the list, or null if list is empty
     */
    public Optional<LinkedListNode<T>> headNode;
    /**
     * Reference to the last node in the list, or null if list is empty
     */
    public Optional<LinkedListNode<T>> lastNode;
    /**
     * Number of elements in the list (which may be different from number of nodes)
     */
    public int length;

    /**
     * Creates an empty SortedList
     */
    public SortedList() {
        this.headNode = Optional.empty();
        this.lastNode = Optional.empty();
        this.length = 0;
    }

    /**
     * Find a reference to the node holding elem, or the node that would precede it
     *
     * @param elem the element to find
     * @return the node containing the greatest element less than or equal to <tt>elem</tt>
     * <p>
     * If the list contains <tt>elem</tt>, this function should return the list node containing it
     * <p>
     * If the list does not contain <tt>elem</tt>, this function should return a reference to the
     * node that would precede <tt>elem</tt> if it were in the list, or <tt>Optional.empty()</tt>
     * if
     * <tt>elem</tt> is smaller than the smallest element in the list.
     * <p>
     * This function should run in O(length)
     */
    public Optional<LinkedListNode<T>> findRefBefore(T elem) {
        LinkedListNode<T> current = headNode.get();
        if (headNode.get().value.compareTo(elem) > 0) {
            return Optional.empty();
        }else{
            while (current.next.isPresent()) {
                if (current.value.compareTo(elem) == 0) {
                    return Optional.of(current);
                }
                if (current.value.compareTo(elem) < 0 && current.next.get().value.compareTo(elem) > 0) {
                    return Optional.of(current);
                }
                current = current.next.get();
            }
            if (lastNode.get().value.compareTo(elem) == 0) {
                return Optional.of(current);
            }
            else{
                return lastNode;
            }
        }
    }

    /**
     * Find a reference to the node holding elem, or the node that would precede it
     *
     * @param elem the element to find
     * @param hint a reference to a node "close" to <tt>elem</tt>
     * @return the node containing the greatest element less than or equal to <tt>elem</tt>
     * <p>
     * If the list contains <tt>elem</tt>, this function should return the list node containing it
     * <p>
     * If the list does not contain <tt>elem</tt>, this function should return a reference to the
     * node that would precede <tt>elem</tt> if it were in the list, or <tt>Optional.empty()</tt> if
     * <tt>elem</tt> is smaller than the smallest element in the list.
     * <p>
     * If <tt>hint</tt> is at position i, and <tt>elem</tt> would be at position j, then this
     * function should run in O(|i-j|)
     */
    public Optional<LinkedListNode<T>> findRefBefore(T elem, LinkedListNode<T> hint) {
        // THIS IS STILL A WORK IN PROGRESS

        LinkedListNode<T> current = hint;
        if (headNode.get().value.compareTo(elem) > 0) {
            return Optional.empty();
        }else{
            if (hint.value.compareTo(elem) > 0) {
                while (current.prev.isPresent()) {
                    if (current.value.compareTo(elem) == 0) {
                        return Optional.of(current);
                    }
                    if (current.value.compareTo(elem) > 0 && current.prev.get().value.compareTo(elem) < 0) {
                        return current.prev;
                    }
                    current = current.prev.get();
                }
                if (headNode.get().value.compareTo(elem) == 0) {
                    return Optional.of(current);
                }
            }
            else{
                while (current.next.isPresent()) {
                    if (current.value.compareTo(elem) == 0) {
                        return Optional.of(current);
                    }
                    if (current.value.compareTo(elem) < 0 && current.next.get().value.compareTo(elem) > 0) {
                        return Optional.of(current);
                    }
                    current = current.next.get();
                }
                if (lastNode.get().value.compareTo(elem) == 0) {
                    return Optional.of(current);
                }
                else{
                    return lastNode;
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Find a reference to the specified element
     *
     * @param elem the element to find
     * @return <tt>Optional.of(node)</tt> where <tt>node</tt> contains <tt>elem</tt>, or
     * <tt>Optional.empty()</tt> if <tt>elem</tt> is not in the list
     * <p>
     * This function should run in O(length)
     */
    public Optional<LinkedListNode<T>> findRef(T elem) {
        LinkedListNode<T> current = headNode.get();
        while (current.next.isPresent()) {
            if (current.value.compareTo(elem) == 0) {
                return Optional.of(current);
            }
            current = current.next.get();
        }
        if (lastNode.get().value.compareTo(elem) == 0) {
            return lastNode;
        }else{
            return Optional.empty();
        }
    }

    /**
     * Find a reference to the specified element
     *
     * @param elem the element to find
     * @param hint a reference to a node "close" to <tt>elem</tt>
     * @return <tt>Optional.of(node)</tt> where <tt>node</tt> contains <tt>elem</tt>, or
     * <tt>Optional.empty()</tt> if <tt>elem</tt> is not in the list
     * <p>
     * If <tt>hint</tt> is at position i, and <tt>elem</tt> would be at position j, then this
     * function should run in O(|i-j|)
     */
    public Optional<LinkedListNode<T>> findRef(T elem, LinkedListNode<T> hint) {
        LinkedListNode<T> current = hint;
        if (current.value.compareTo(elem) < 0) {
            while(current.next.isPresent()) {
                if (current.value.compareTo(elem) == 0) {
                    return Optional.of(current);
                }
                current = current.next.get();
            }
            if (lastNode.get().value.compareTo(elem) == 0) {
                return lastNode;
            }
        }
        else {
            while (current.prev.isPresent()) {
                if (current.value.compareTo(elem) == 0) {
                    return Optional.of(current);
                }
                current = current.prev.get();
            }
            if (headNode.get().value.compareTo(elem) == 0) {
                return headNode;
            }
            else{
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Return the node holding the element at the specified index
     *
     * @param idx the index to look up
     * @return the node holding the element at position <tt>idk</tt>
     * @throws IndexOutOfBoundsException if <tt>idx</tt> < 0 or <tt>idx</tt> >= length
     *
     * Note that <tt>idx</tt> refers to the index of an element, not of a node.
     *
     * If the list changes, references to nodes who's values are unchanged should remain valid, even
     * if their index changes.
     *
     * This function should run in O(idx)
     */
    public LinkedListNode<T> getRef(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= length) {
            throw new IndexOutOfBoundsException();
        }

        Optional<LinkedListNode<T>> current = headNode;

        int i = 0;
        while (current.isPresent()) {
            if (i <= idx && idx < i + current.get().count){
                return current.get();
            }
            i += current.get().count;
            current = current.get().next;
        }
        return null;
    }

    /**
     * Return the value at the specified index
     *
     * @param idx the index to look up
     * @return the value currently at index <tt>idx</tt>
     * @throws IndexOutOfBoundsException if <tt>idx</tt> < 0 or <tt>idx</tt> >= length
     *
     * Note that <tt>idx</tt> refers to the index of an element, not of a node.
     *
     * This function should run in O(idx)
     */
    public T get(int idx) throws IndexOutOfBoundsException {
        return getRef(idx).value;
    }

    /**
     * Insert a new value into the list
     *
     * @param elem the value to insert
     * @return a reference to the node holding the inserted value
     * <p>
     * The value should be placed so that the list remains in sorted order. After the insertion, the
     * insertion points <tt>next</tt> field should be a reference holding the next largest element,
     * and the <tt>prev</tt> field should be a reference to the next smallest element.
     * <p>
     * If <tt>elem</tt> is already in the list, the existing node should just have its
     * <tt>count</tt> field updated instead of creating a new node.
     * <p>
     * This function should run in O(length)
     */
    public LinkedListNode<T> insert(T elem) {
        LinkedListNode<T> newNode = new LinkedListNode<>(elem, 1);

        // Check if list is empty
        if (!headNode.isPresent()) {
            headNode = Optional.of(newNode); // set head node to new inserted node.
            lastNode = Optional.of(newNode); // set last node to new inserted node.
            length++;

            return headNode.get(); // return the new head node
        }
        else if (headNode.get().value.compareTo(elem) == 0 && !headNode.get().next.isPresent()) {
            //check if new node is same as head node
            headNode.get().count++;
            length++;
            return headNode.get();
        }
        else if (headNode.get().value.compareTo(elem) > 0 && !headNode.get().next.isPresent()) {
            //check if new node needs to become the head node and set last node to previous head node
            lastNode = headNode;
            headNode = Optional.of(newNode);
            headNode.get().next = lastNode;
            lastNode.get().prev = headNode;
            length++;
            return headNode.get();
        }
        else if (headNode.get().value.compareTo(elem) < 0 && !headNode.get().next.isPresent()) {
            lastNode = Optional.of(newNode);
            headNode.get().next = lastNode;
            lastNode.get().prev = headNode;
            length++;
            return lastNode.get();
        }
        else{
            LinkedListNode<T> current = headNode.get();
            if (current.value.compareTo(elem) > 0) {
                newNode.next = headNode;
                headNode.get().prev = Optional.of(newNode);
                headNode = Optional.of(newNode);
                return headNode.get();
            }
            else {
                while (current.next.isPresent()) {
                    if (current.value.compareTo(elem) == 0) {
                        current.count++;
                        length++;
                        return current;
                    }
                    else if (current.next.get().value.compareTo(elem) > 0){
                        //if next node is bigger than elem then insert new node before that node
                        current.next.get().prev = Optional.of(newNode);
                        newNode.next = current.next;
                        current.next = Optional.of(newNode);
                        newNode.prev = Optional.of(current);
                        length++;
                        return newNode;
                    }
                    current = current.next.get();
                }
                //at end of list
                if (lastNode.get().value.compareTo(elem) == 0) {
                    lastNode.get().count++;
                    length++;
                    return lastNode.get();
                }
                else{
                    lastNode.get().next = Optional.of(newNode);
                    newNode.prev = lastNode;
                    lastNode = Optional.of(newNode);
                    length++;
                    return lastNode.get();
                }
            }
        }
    }

    /**
     * Insert a new value into the list
     *
     * @param elem the value to insert
     * @param hint a reference to a node "close" to <tt>elem</tt>
     * @return a reference to the node holding the inserted value
     * <p>
     * The value should be placed so that the list remains in sorted order. After the insertion, the
     * insertion points <tt>next</tt> field should be a reference holding the next largest element,
     * and the <tt>prev</tt> field should be a reference to the next smallest element.
     * <p>
     * If <tt>elem</tt> is already in the list, the existing node should just have its
     * <tt>count</tt> field updated instead of creating a new node.
     * <p>
     * If <tt>hint</tt> is at position i, and <tt>elem</tt> would be at position j, then this
     * function should run in O(|i-j|)
     */
    public LinkedListNode<T> insert(T elem, LinkedListNode<T> hint) {
        LinkedListNode<T> newNode = new LinkedListNode<>(elem, 1);

        // Check if list is empty
        if (!headNode.isPresent()) {
            headNode = Optional.of(newNode); // set head node to new inserted node.
            lastNode = Optional.of(newNode); // set last node to new inserted node.
            length++;

            return headNode.get(); // return the new head node
        }
        else if (headNode.get().value.compareTo(elem) == 0 && !headNode.get().next.isPresent()) {
            //check if new node is same as head node
            headNode.get().count++;
            return headNode.get();
        }
        else if (headNode.get().value.compareTo(elem) > 0 && !headNode.get().next.isPresent()) {
            //check if new node needs to become the head node and set last node to previous head node
            lastNode = headNode;
            headNode = Optional.of(newNode);
            headNode.get().next = lastNode;
            lastNode.get().prev = headNode;
            length++;
            return headNode.get();
        }
        else if (headNode.get().value.compareTo(elem) < 0 && !headNode.get().next.isPresent()) {
            lastNode = Optional.of(newNode);
            headNode.get().next = lastNode;
            lastNode.get().prev = headNode;
            length++;
            return lastNode.get();
        }
        else{
            LinkedListNode<T> current = headNode.get();
            LinkedListNode<T> current2 = hint;
            if (current.value.compareTo(elem) > 0) {
                newNode.next = headNode;
                headNode.get().prev = Optional.of(newNode);
                headNode = Optional.of(newNode);
                return headNode.get();
            }
            else {
                if (current2.value.compareTo(elem) > 0) {
                    //navigate backwards
                    while (current2.prev.isPresent()) {
                        if (current2.value.compareTo(elem) == 0) {
                            current2.count++;
                            length++;
                            return current2;
                        }
                        else if (current2.prev.get().value.compareTo(elem) < 0){
                            //if next node is bigger than elem then insert new node before that node
                            current2.prev.get().next = Optional.of(newNode);
                            newNode.next = Optional.of(current2);
                            newNode.prev = current2.prev;
                            current2.prev = Optional.of(newNode);
                            length++;
                            return newNode;
                        }
                        current2 = current2.prev.get();
                    }
                    if (headNode.get().value.compareTo(elem) == 0) {
                        headNode.get().count++;
                        length++;
                        return headNode.get();
                    }else {
                        headNode.get().prev = Optional.of(newNode);
                        newNode.next = headNode;
                        headNode = Optional.of(newNode);
                        length++;
                        return headNode.get();
                    }
                }
                else {
                    while (current2.next.isPresent()) {
                        //navigate forwards
                        if (current2.value.compareTo(elem) == 0) {
                            current2.count++;
                            length++;
                            return current2;
                        }
                        else if (current2.next.get().value.compareTo(elem) > 0){
                            //if next node is bigger than elem then insert new node before that node
                            current2.next.get().prev = Optional.of(newNode);
                            newNode.next = current2.next;
                            current2.next = Optional.of(newNode);
                            newNode.prev = Optional.of(current2);
                            length++;
                            return newNode;
                        }
                        current2 = current2.next.get();
                    }
                    if (lastNode.get().value.compareTo(elem) == 0) {
                        lastNode.get().count++;
                        length++;
                        return lastNode.get();
                    }
                    else {
                        lastNode.get().next = Optional.of(newNode);
                        newNode.prev = lastNode;
                        lastNode = Optional.of(newNode);
                        length++;
                        return lastNode.get();
                    }
                }
            }
        }
    }

    /**
     * Remove one instance of the value held by <tt>ref</tt> from the list
     *
     * @param ref the node holding the value to remove (must be part of the list)
     * @return the removed value
     * <p>
     * If the list only contains a single instance of the value being removed, then the node should
     * be removed from the list as well. If the list contains multiple instances of the value, the
     * node itself should remain in the list.
     * <p>
     * This function should run in O(1)
     */
    public T remove(LinkedListNode<T> ref) {
        if (ref.count > 1){
            ref.count--;
            length--;
            return ref.value;
        }
        else{
            if (!headNode.get().next.isPresent()) {
                headNode = Optional.empty();
                lastNode = Optional.empty();
                length--;
                return ref.value;
            }
            else if (ref == headNode.get()){
                headNode = ref.next;
                headNode.get().prev = Optional.empty();
                length--;
                return ref.value;
            }
            else if (ref == lastNode.get()){
                lastNode = ref.prev;
                lastNode.get().next = Optional.empty();
                length--;
                return ref.value;
            }
            else{
                ref.prev.get().next = ref.next;
                ref.next.get().prev = ref.prev;
                length--;
                return ref.value;
            }
        }
    }

    /**
     * Remove n instances of the value held by <tt>ref</tt> from the list
     *
     * @param ref the node holding the value to remove (must be part of the list)
     * @param n the number of instances of the value to remove
     * @return the removed value
     * @throws IllegalArgumentException if <tt>n</tt> > <tt>ref.count</tt>
     *
     * If the node contains more than <tt>n</tt> instances of the value, then the node itself should
     * remain in the list.
     *
     * This function should run in O(1)
     */
    public T removeN(LinkedListNode<T> ref, int n) throws IllegalArgumentException {
        if (n > ref.count){
            throw new IllegalArgumentException();
        }
        else{
            if (ref.count == n){
                if (!headNode.get().next.isPresent()) {
                    headNode = Optional.empty();
                    lastNode = Optional.empty();
                    length = length - n;
                    return ref.value;
                }
                else if (ref == headNode.get()){
                    headNode = ref.next;
                    headNode.get().prev = Optional.empty();
                    length = length - n;
                    return ref.value;
                }
                else if (ref == lastNode.get()){
                    lastNode = ref.prev;
                    lastNode.get().next = Optional.empty();
                    length = length - n;
                    return ref.value;
                }
                else{
                    ref.prev.get().next = ref.next;
                    ref.next.get().prev = ref.prev;
                    length = length - n;
                    return ref.value;
                }
            }
            else{
                ref.count = ref.count - n;
                length = length - n;
                return ref.value;
            }
        }
    }

    /**
     * Remove all instances of the value held by <tt>ref</tt> from the list
     *
     * @param ref the node holding the value to remove (must be part of the list)
     * @return the removed value
     * <p>
     * This function should run in O(1)
     */
    public T removeAll(LinkedListNode<T> ref) {
        if (!headNode.get().next.isPresent()) {
            headNode = Optional.empty();
            lastNode = Optional.empty();
            length = length - ref.count;
            return ref.value;
        }
        else if (ref == headNode.get()){
            headNode = ref.next;
            headNode.get().prev = Optional.empty();
            length = length - ref.count;
            return ref.value;
        }
        else if (ref == lastNode.get()){
            lastNode = ref.prev;
            lastNode.get().next = Optional.empty();
            length = length - ref.count;
            return ref.value;
        }
        else{
            ref.prev.get().next = ref.next;
            ref.next.get().prev = ref.prev;
            length = length - ref.count;
            return ref.value;
        }
    }

    /**
     * Modifies a single value presently in the list
     *
     * @param ref  a reference to the node with the value to be updated
     * @param elem the new value
     * @return a reference to the updated node
     * <p>
     * This code is provided for you.
     * <p>
     * If i is the position of ref before the update and j is the position of ref after the update,
     * then this function should run in O(|i-j|)
     */
    public LinkedListNode<T> update(LinkedListNode<T> ref, T elem) {
        LinkedListNode<T> ret = insert(elem, ref);
        remove(ref);
        return ret;
    }

    /**
     * Modifies a single value presently in the list
     *
     * @param idx  the index of the element to be updated
     * @param elem the new value
     * @return a reference to the updated node
     * <p>
     * This code is provided for you.
     * <p>
     * If i is the position of ref before the update and j is the position of ref after the update,
     * then this function should run in O(|i-j|)
     */
    public void update(int idx, T elem) {
        update(getRef(idx), elem);
    }

    /**
     * Return an iterator over the elements of the collection
     *
     * @return an iterator over the elements of the collection
     * <p>
     * The iterator should return elements in sorted order from least to greatest (according to the
     * types <tt>compareTo</tt> method).
     * <p>
     * The iterator's <tt>next</tt> and <tt>hasNext</tt> methods should both run in O(1)
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Optional<LinkedListNode<T>> current = headNode;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return current.isPresent();
            }

            @Override
            public T next() {
                T ret = current.get().value;
                count++;
                if (count >= current.get().count) {
                    current = current.get().next;
                    count = 0;
                }
                return ret;
            }
        };
    }
}
