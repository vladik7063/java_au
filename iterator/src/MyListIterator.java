

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListIterator<T> implements Iterable<T>{

    
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this);
    }

    public class MyIterator<E> implements Iterator<E>{

        int index = 0;
        MyListIterator<E> eMyListIterator;

        public MyIterator(MyListIterator<E> eMyListIterator) {
            this.eMyListIterator = eMyListIterator;
        }

        @Override
        public boolean hasNext() {

            return eMyListIterator.length >= index + 1;
        }

        @Override
        public E next() throws NoSuchElementException {
            return eMyListIterator.get(index++);
        }

    }

    class Node {
        private final T value;
        private Node nextNode;
        private Node prevNode;

        public Node(T value) {
            this.value = value;
            nextNode = null;
            prevNode = null;
        }

    }

    int length;
    Node startNode;
    Node endNode;

    public MyListIterator() {
        length = 0;
        startNode = null;
        endNode = null;
    }

    public T get(int index) {
        Node target = startNode;
        if (index >= length) throw new IndexOutOfBoundsException();
        for (int i = 0; i < index; i++){
            target = target.nextNode;
        }
        return target.value;
    }

    /** Add a node of value val before the first element of the linked list. */
    public void addAtHead(T val) {
        Node NewNode = new Node(val);
        if (startNode == null) {
            startNode = NewNode;
            endNode = NewNode;
            length++;
            return;
        }
        NewNode.nextNode = startNode;
        startNode.prevNode = NewNode;
        startNode = NewNode;
        length++;
    }
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(T val) {
        Node NewNode = new Node(val);
        if (startNode == null) {
            startNode = NewNode;
            endNode = NewNode;
            length++;
            return;
        }
        NewNode.prevNode = endNode;
        endNode.nextNode = NewNode;
        endNode = NewNode;
        length++;
    }

    /** Add a node of value val before the index-th node in the linked list.*/
    public void addAtIndex(int index, T val) {

        if (index > length) return;

        if (index == 0) {
            addAtHead(val);
        }else if (index == length) {
            addAtTail(val);
            return;
        }

        Node target = startNode;
        Node addNode = new Node(val);
        for (int i = 0; i < index; i++){
            target = target.nextNode;
        }

        addNode.prevNode = target.prevNode;
        addNode.nextNode = target;
        target.prevNode.nextNode = addNode;
        target.prevNode = addNode;
        length++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= length || index < 0) return;

        if (index == 0) {
            if (length == 1) {
                startNode = null;
                endNode = null;
            } else {
                startNode.nextNode.prevNode = null;
                startNode = startNode.nextNode;
            }
            length--;
            return;
        }

        if (index == length - 1) {
            endNode.prevNode.nextNode = null;
            endNode = endNode.prevNode;
            length--;
            return;
        }

        Node terget = startNode;
        for (int i = 0; i < index; i++){
            terget = terget.nextNode;
        }
        terget.prevNode.nextNode = terget.nextNode;
        terget.nextNode.prevNode = terget.prevNode;
        length--;
    }
}

