import java.util.Arrays;
import java.util.Iterator;


public class MyArrayList<T> implements Iterable<T>
{
    
    private T[] list;
    private int size;

    
    public MyArrayList(){
        list = (T[])new Object[10];
        size = 0;
    }
    
    boolean add(T t){
        if(size == list.length)
            list = Arrays.copyOf(list, 2*list.length);

        list[size] = t;
        size++;
        return true;
    }
    
    public void add(int index, T t){
        if (index >= size || index < 0) {
			System.out.println("Index out of bound");
		}
		else {
            if (size == list.length)
                list = Arrays.copyOf(list, 2*list.length);
			for (int i = size; i > index; i--)
				list[i] = list[i - 1];

			list[index] = t;
			size++;
        }
    }
    
    public boolean contains(T t){
        for (int i = 0; i < size; i++) {
			if (list[i].equals(t))
				return true;
		}
		return false;
    }

    public T get(int index){
        return list[index];
    }
    
    public int indexOf(T t){
        for (int i = 0; i < list.length; i++) {
			if (list[i].equals(t))
				return i;
		}
		return -1;
    }
    
    public boolean remove(T t){
       if (contains(t)) {
            for (int i = indexOf(t); i < list.length - 1; i++)
                list[i] = list[i + 1];
            size--;
            return true;
        }
    return false;
    }   
    
    public T set(int index, T t){
        T temp = list[index];
        list[index] = t;
        return temp;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public int Size(){
        return list.length;
    }

    public Iterator<T> iterator() {
        class MyIterator implements Iterator<T> {
            int index = 0;
            public boolean hasNext() {
                return size > index;
            }
            public T next() {
                T lista = list[index];
                index++;
                return lista;
            }
        }
        return new MyIterator();
    }
    
    public int size(){
        return size;
    }

    public void clear(){
        size = 0;
        for(int x = 0 ; x < list.length; x++){
            list[x] = null;
        }
    }
    
    public String toString(){
        String str="[";
		for (int i = 0; i < size; i++)
            str += list[i] + ", ";
        str += "]";
		return str;
    }

    public static void main(String[] args) {
		MyArrayList<Integer> TestList = new MyArrayList<Integer>();
		TestList.add(1);
		TestList.add(2);
		TestList.add(3);
		TestList.add(4);
		TestList.add(5);
		System.out.println(TestList);

		System.out.println(TestList.remove(2));
		System.out.println(TestList);

		System.out.println(TestList.size());
        System.out.println("Index of 3: " + TestList.indexOf(3));
        TestList.set(2, 99);
		System.out.println(TestList);
        System.out.println(TestList.contains(99));
	}
}