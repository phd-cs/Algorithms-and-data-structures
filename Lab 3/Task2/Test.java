
public class Test {

    public static void main(String[] args) {
        TreeSetCounter<String> tree = new TreeSetCounter<>();

        tree.add("hello");
        tree.add("hello");
        tree.add("on");
        tree.add("you");
        tree.add("hello");
        tree.add("again");

        tree.print();

    }
}
