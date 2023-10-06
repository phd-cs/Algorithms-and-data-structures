import java.util.Iterator;

public class MyStack<T extends Comparable<T>> implements Iterable<T> {

    private final DoublyLinkedList<T> doublyLinkedList;

    public MyStack() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    public T peek() {
        return doublyLinkedList.getFirst();
    }

    public T pop() {
        return doublyLinkedList.removeFirst();
    }

    public void push(T t) {
        doublyLinkedList.add(0, t);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final DoublyLinkedList<T> copyDoublyLinkedList = doublyLinkedList;
            @Override
            public boolean hasNext() {
                return !copyDoublyLinkedList.isEmpty();
            }
            @Override
            public T next() {
                return copyDoublyLinkedList.removeFirst();
            }
        };
    }
}
