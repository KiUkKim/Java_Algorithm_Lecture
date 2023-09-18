package BOJ_2343;

import java.util.*;
import java.io.*;

public class BOJ_2343 {
    private static int n, m;
    private static int [] arr;
    private static int low, high;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        int sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            low = Math.max(low, arr[i]);
        }

        high = sum;

        binarySearch();

        System.out.println(low);
    }

    private static void binarySearch() {
        int mid, sum, count;

        while (low <= high) {
            mid = low + high >> 1;
            sum = 0;
            count = 0;

            for (int i = 0; i < n; i++) {
                if (sum + arr[i] > mid) {
                    sum = 0;
                    count++;
                }

                sum += arr[i];
            }

            if (sum > 0) count++;

            if (count <= m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }

}
