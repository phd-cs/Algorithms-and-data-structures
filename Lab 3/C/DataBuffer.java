

import java.util.Arrays;
import java.util.Iterator;

public class DataBuffer<T> implements Iterable<T> {

    private int BufferSize;
    private int front;
    private int back;
    private int size;
    private Object []arr;

    public DataBuffer(int bufferSize) {
        BufferSize = bufferSize;
        arr=new Object[BufferSize];
        front=0;
        back=-1;
        size=0;
    } 

    synchronized void enqueue(T t)
    {
        if(size==BufferSize)
        {
            throw new java.lang.Error("Buffer is Full cannot add more values");

        }
        else
        {

            back=back+1;
            if(back==BufferSize)
            {
                back=0;
            }
            arr[back]=t;
            size++;
        }
    }

    synchronized T dequeue()
    {
        if(size==0)
        {
            System.out.println("No element in the Buffer");
            return null;
        }
        else {
            int temp = front;

            front=front+1;
            size--;
            if(front==BufferSize)
            {
                front=0;
            }

            return (T) arr[temp];
        }
    }

    void changeBufferSize(int newBufferSize)
    {
        Object []temp=new Object[BufferSize];

        for (int i = 0; i <BufferSize ; i++) {
            temp[i]=arr[i];
        }
        arr=new Object[newBufferSize];

        if(newBufferSize<BufferSize)
        {
            for (int i = 0; i <newBufferSize ; i++) {
                arr[i]=temp[i];
            }
        }
        else
        {
            for (int i = 0; i <BufferSize ; i++) {
                arr[i]=temp[i];
            }
        }

        BufferSize=newBufferSize;
    }

    boolean isFull()
    {
        return size == BufferSize;
    }

    boolean isEmpty()
    {
        return size==0;
    }

    int Size()
    {
        return size;
    }

    int bufferSize()
    {
        return BufferSize;
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.stream(arr).iterator();
    }


}
