package sort;

public class Test {
    public static void main(String[] args) {
        int arr[] = new int[1024];
        for (int i = 0; i < 1024; i++) {
            arr[i] = (i + 1) * 3;
        }
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.search(arr, 825));
        System.out.println(arr[274]);
    }
}