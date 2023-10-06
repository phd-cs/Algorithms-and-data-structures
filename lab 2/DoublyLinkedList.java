import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T> {

    public class ListNode<T> {
        public ListNode<T> next;
        public ListNode<T> prev;
        public T Element;
    
        public ListNode(T Element) {
            this.Element = Element;
            this.prev = null;
            this.next = null;
        }

        public ListNode(T Element, ListNode<T> prev, ListNode<T> next) {
            this.Element = Element;
            this.prev = prev;
            this.next = next;
        }
    }

    private ListNode<T> newNode;
    int size;

    public DoublyLinkedList() {
        newNode = null;
    }

    public void add(T t) {
        if (newNode == null) 
            newNode = new ListNode<>(t, null, null);
        else {
            ListNode<T> temp = newNode;    
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new ListNode<>(t, null, temp);
        }
    }

    public void add(int index, T t) {
        if (index < 0)
            return;
        if (index == 0 && newNode == null) {
            newNode = new ListNode<>(t, null, null);
            return;
        }
        else if (index == 0) {
            newNode = new ListNode<>(t, newNode, null);
            newNode.next.prev = newNode;
            return;
        }
        size = 0;
        ListNode<T> temp = newNode;

        while (temp != null) {
            if (size == index)
                break;
            temp = temp.next;
            size++;
        }

        if (index != size)
            return;

        ListNode<T> inserting = new ListNode<>(t, temp, temp.prev);
        temp.prev = inserting;
        inserting.prev.next = inserting;
    }

    public T get(int index) {
        if (index < 0 || newNode == null) 
            return null;

        size = 0;
        ListNode<T> temp = newNode;
        while (temp != null) {
            if (size == index)
                return temp.Element;
            temp = temp.next;
            size++;
        }
        return null;
    }

    public T getFirst() {
        if (newNode != null)
            return newNode.Element;
        else 
            return null;
    }

    public T getLast() {
        ListNode<T> temp = newNode;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.Element;
    }

    public int remove(T t) {
        if (newNode == null)
            return -1;

        int del = 0;
        ListNode<T> temp = newNode;

        while (temp != null) {
            if (temp.Element.equals(t)) {
                if (temp.next == null && temp.prev == null) {
                    newNode = null;
                    del++;
                    break;
                }
                else if (temp.next == null) {
                    temp.prev.next = null;
                }
                else if (temp.prev == null) {
                    temp.next.prev = null;
                    newNode = temp.next;
                }
                else {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                }
                del++;    
            }
            temp = temp.next;
        }
        return del;
    }

    public T remove(int index) {
        if (newNode == null || index < 0)
            return null;

        size = 0;
        ListNode<T> temp = newNode;
        while (temp.next != null) {
            if (size == index)
                break;

            temp = temp.next;
            size++;
        }

        if (size != index)
            return null;

        if (temp.next == null && temp.prev == null) {
            T toReturn = newNode.Element;
            newNode = null;
            return toReturn;
        }
        else if (temp.next == null) {
            temp.prev.next = null;
        }
        else if (temp.prev == null) {
            temp.next.prev = null;
            newNode = temp.next;
        }
        else {
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
        }
        temp.next = null;  
        temp.prev = null;

        return temp.Element;
    }

    public T removeLast() {
        if (newNode == null)
            return null;
        ListNode<T> last = newNode;

        while (last.next != null)
            last = last.next;

        last.prev.next = null;
        last.prev = null;
        return last.Element;
    }

    public T removeFirst() {
        if (newNode == null)
            return null;

        ListNode<T> first = newNode;
        if (newNode.next == null)
            newNode = null;
        else {
            newNode = newNode.next;
            newNode.prev = null;
        }
        return first.Element;
    }

    public boolean isEmpty() {
        return newNode == null;
    }

    public int size() {
        size = 0;  
        ListNode<T> temp = newNode;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public void clear() {
        newNode = null;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            ListNode<T> listNode = newNode;
            @Override
            public boolean hasNext() {
                return listNode != null;
            }
            @Override
            public T next() {
                ListNode<T> ret = listNode;
                listNode = listNode.next;
                return ret.Element;
            }
        };
    }

    @Override
    public String toString() {
        ListNode<T> temp = newNode;
        String str = "ListNode[";
        while(temp != null) {
            str += temp.Element.toString();
            if (temp.next != null) {
                str += ",";
            }
            temp = temp.next;
        }
        str += "]";
        return str;
    }

    public void reverse() {
        MyStack<T> stack = new MyStack<>();

        for (T t : this) {
            stack.push(t);
        }
        this.clear();   
        for (T t : stack) {
            this.add(t);
        }
    }

    public void addAtFirstSmaller(T t) {
        if (newNode == null) {
            newNode = new ListNode<>(t);
            return;
        }
        ListNode<T> temp = newNode;

        while (temp.next != null)
            temp = temp.next;

        while (temp != null) {
            if (temp.Element.compareTo(t) < 0) {
                ListNode<T> insert = new ListNode<>(t, temp.next, temp);
                if (temp.next != null)
                    temp.next.prev = insert;
                temp.next = insert;
                return; 
            }
            temp = temp.prev;
        }
        ListNode<T> insert = new ListNode<>(t, newNode, null);
        newNode.prev = insert;
        newNode = insert;
    }
}
