package sort;

public class MergeSort implements ISort {
    @Override
    public void sort(int[] arr) {
        // in place sort
        mergeSort(arr, 0, arr.length - 1);
    }

    // 분할
    private void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + ((high - low) / 2);
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        // array를 분할하는 것이 아닌, 분할되는 인덱스를 구함

       merge(arr, low, mid, high);
    }

    // 합병
    private void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1]; // 보조배열
        int idx = 0;

        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[idx] = arr[left];
                left++;
            } else {
                temp[idx] = arr[right];
                right++;
            }
            idx++;
        }

        while (left <= mid) { // 왼쪽 리스트 값이 남음
            temp[idx] = arr[left];
            idx++;
            left++;
        }

        while (right <= high) { // 오른쪽 리스트 값이 남음
            temp[idx] = arr[right];
            idx++;
            right++;
        }

        for (int i = low; i <= high; i++) { // temp에는 정렬된 값이 있으니
            arr[i] = temp[i - low]; // 그걸 arr에 넣어서 메모리 소비를 줄임
        }
    }
}
