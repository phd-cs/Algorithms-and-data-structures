

import java.util.*;


public class MaxPQ<T extends Comparable<T>>
{
    T[] a;
    int size = 0;
    
    public MaxPQ()
    {
        a = (T[]) new Comparable[10];
        size = 0;
    }

    public MaxPQ(T [] arr)
    {

        a= (T[]) new Comparable[arr.length];
        for (T t : arr) {
            insert(t);
        }
    }
    
    
    public MaxPQ(int max)
    {
        a = (T[]) new Comparable[max];
        size = 0;
    }
    
    
    int size()
    {
        return size;
    }
    
    
    boolean isEmpty()
    {
        return size == 0;
    }
   
    
    public void insert(T t)
    {
        // array doubling 
        if (size == a.length - 2)
            a = Arrays.copyOf(a, 2*a.length);
            
        a[++size] = t;
        
        swim(size);
    }
    
    
    
    
    private void swim(int k)
    {
        while (k/2 > 0 && less(k/2, k))
        {
            exchange(k/2, k);
            k /= 2;
        }
    }
    
    private boolean less(int i, int j)
    {
        return a[i].compareTo(a[j]) < 0;
    }
    
    private void exchange(int i, int j)
    {
        T temp = a[i]; a[i] = a[j]; a[j] = temp;
    }
    
    
    public T delMax()
    {
        if (size == 0) return null;
        
        T maxElement = a[1];
        
        exchange(1, size);
        
        a[size--] = null;
        
        if (size > 1) sink(1);
        
        return maxElement;
    }
    
    public void sink(int k)
    {
        while (2*k <= size)
        {
            int j = 2*k;
            if (j < size && less(j, j+1)) j++;
            if (less(j,k)) break;
            exchange(j, k);
            k = j;
        }
    }
    
    
    public T max()
    {
        if (size == 0) return null;
        return a[1];
    }
    
    
    
   
    
    
    public static void main(String [] cmdLn)
    {
        MaxPQ<Integer> pq = new MaxPQ<>();
        pq.insert(1);
        pq.insert(3);
        pq.insert(7);
        pq.insert(2);
        System.out.println(pq.max());
        
    }
    
    
    
    
}