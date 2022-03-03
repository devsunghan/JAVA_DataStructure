package sort;

public class BinarySearch {

    public int search(int[] arr, int target){
        // 1. 데이터 충간 인덱스 값을 찾는다.
        // 2. 중간 인득세 위치를 기준으로 arr 을 절반으로 나눈다.
        // 3. 나눠진 절반의 리스트에서 target 값을 찾는다.

        int l = 0; // arr의 가장 왼쪽
        int r = arr.length - 1; // arr의 가장 오른쪽

        int cnt = 1;
        int m; // middle
        while (l <= r) { // l이 r보다 커졌다는 컨, 모든 데이터를 봤는 말
            m = l + ((r - l) / 2);
            System.out.println(cnt + "회의 L : " + l + ", R : " + r + ", M : " + m);
            if (arr[m] == target) {
                return m;
            }

            if (arr[m] < target) {
                l = m + 1; // 왼쪽은 볼 거 없음, 타켓이 m번째보다 큼
                System.out.println("cut L");
            } else {
                r = m - 1; // 오른쪽은 볼 거 없음, 타켓이 M번째보다 작음
                System.out.println("cut R");
            }
            cnt++;
        }
        return -1;
    }
}
