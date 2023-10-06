

import java.util.ArrayList;
import java.util.Iterator;

class Node<T> {
    T value;
    Node left;
    Node right;
    int counter;

    Node(T value) {
        this.value = value;
        right = null;
        left = null;
        counter=1;
    }
}

public class TreeSetCounter<T extends Comparable<T>> implements Iterable {


    private Node<T> root;
    private int size;
    private int maxFrequency;

    public TreeSetCounter()
    {
        root=null;
        size=0;
        maxFrequency=0;
    }

    private Node<T> addvalue(Node<T> current,T value)
    {
        if (current == null) {
            if(maxFrequency==0)
            {
                maxFrequency=1;
            }
            return new Node<>(value);
        }

        if (value.compareTo((T) current.value) < 0) {
            current.left = addvalue(current.left,value);
        } else if (value.compareTo((T) current.value) > 0) {
            current.right = addvalue(current.right, value);
        } else {
            current.counter=current.counter+1;
            if(current.counter>maxFrequency)
            {
                maxFrequency=current.counter;
            }
            return current;
        }

        return current;
    }
    private void inOrder(ArrayList<T> L,Node<T> node) {
        if (node == null) {
            return;
        }
        inOrder(L,node.left);
        L.add((T) node.value);
        inOrder(L,node.right);
    }
    private Node<T> Search(Node<T> current,T value)
    {
        if (current == null) {
            return current;
        }
        if (value == current.value) {
            return current;
        }
        return value.compareTo((T) current.value)<0
                ? Search(current.left, value)
                : Search(current.right, value);
    }

    public void add(T t)
    {
        size=size+1;
        root=addvalue(root,t);
    }
    public void clear()
    {
        root=null;
        size=0;
        maxFrequency=0;
    }
   public int getMaxFrequency()
    {
       return maxFrequency;
    }

    public int Size()
    {
        return size;
    }
    public boolean contains(T t)
    {
        Node<T> temp=Search(root,t);
        return temp!=null;
    }
   public boolean isEmpty()
    {
        return root==null;
    }

    public int counter(T t)
    {
        Node<T> temp= Search(root,t);
        return temp.counter;
    }

    private void inorderprint(Node<T> tnode)
    {
        if (tnode == null) {
            return;
        }
        inorderprint(tnode.left);
       System.out.println("Value:"+tnode.value+"   "+"Counter: "+tnode.counter);
        inorderprint(tnode.right);
    }

    public void print() {

        inorderprint(root);
    }
    @Override
    public Iterator iterator() {

        ArrayList<T> list=new ArrayList<>();
        inOrder(list,root);
        return list.iterator();
    }

}



