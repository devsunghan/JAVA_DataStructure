package list;

import static java.lang.System.currentTimeMillis;

public class Test {
    public static void main(String[] args) {
        long a = 1;
        long beforeTime = currentTimeMillis();
        for (int i=0; i < 100000000; i++) {
            a += i;
        }
        long afterTime = currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println(a);
        System.out.println("시간차(m) : " + secDiffTime);
    }
}
