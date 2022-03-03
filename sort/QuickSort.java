package sort;

public class QuickSort implements ISort {

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = low + ((high - low) / 2);
        int pivotValue = arr[pivot]; // 피봇 값

        int left = low;
        int right = high;
        while (left <= right) {
            while (arr[left] < pivotValue) {
                left++;
            }

            while (arr[right] > pivotValue) {
                right--;
            }

            if (left <= right) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                left++; // 왼쪽에서 멈췄다면 피봇보다 큰 값이 있는 거고,
                right--; // 오른쪽에서 멈췄다면 피봇보다 작은 값이 있는 거다.
                // left가 right보다 작거나 같다는 말은 아직 피봇을 중심으로 작은 값이
                // 왼쪽, 큰 값이 오른쪽으로 정렬되지 않았다는 말이니 걸린 부분의 위치를 바꿔준다.
            }
        }

        quickSort(arr, low, right); // 왼쪽 정렬
        quickSort(arr, left, high); // 오른쪽 정렬
    }
}
