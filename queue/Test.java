package queue;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = a;
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        a = null;
        a = new ArrayList<>();
        System.out.println(System.identityHashCode(b));
    }
}
