import java.util.Arrays;
import java.util.Random;

public class task3 {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        int[] arr = new int[10];
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            arr[i] = r.ints(0, 100).findFirst().getAsInt();
        }
        System.out.println("Generated the list: " + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        for (int n : arr) {
            doublyLinkedList.addAtFirstSmaller(n);
        }
        long finish = System.currentTimeMillis();
        long computationalTime = finish - start;
        int i = 0;
        for (int x : doublyLinkedList) {
            arr[i] = x;
            i++;
        }
        System.out.println("Sorterd list: " + Arrays.toString(arr));
        System.out.println("Time: " + computationalTime);
    }

}
