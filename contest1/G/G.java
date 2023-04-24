import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G{

    public static long[] mergeSort(long[] arr, long right) {
        if (arr == null) {
            return null;
        }
        if (arr.length < 2) {
            return arr;
        }
        long mid = right / 2;
        long[] arr1 = mergeSort(Arrays.copyOfRange(arr, 0, (int) mid), mid);
        long[] arr2 = mergeSort(Arrays.copyOfRange(arr, (int) mid, (int) right),right - mid);
        return merge(arr1, arr2);
    }

    public static long[] merge(long[] arr1, long[] arr2) {
        int it1 = 0;
        int it2 = 0;
        long[] res = new long[arr1.length + arr2.length];
        while ((it1 < arr1.length) && (it2 < arr2.length)) {
            if (arr1[it1] < arr2[it2]) {
                res[it1 + it2] = arr1[it1];
                it1++;
            } else {
                res[it1 + it2] = arr2[it2];
                it2++;
            }
        }
        while (it1 < arr1.length) {
            res[it1 + it2] = arr1[it1];
            ++it1;
        }
        while (it2 < arr2.length) {
            res[it1 + it2] = arr2[it2];
            ++it2;
        }
        return res;
    }

    public static boolean check(long[] arr, long mid, long k) {
        long n = 1;
        long lastStart = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - lastStart > mid) {
                n++;
                lastStart = arr[i];
            }
        }
        return (n > k);
    }

    static long binSearching(long[] arr, int count, long k) {

        long start = -1;
        long end = Math.max(arr[count - 1] - arr[0], 1);

        while (end - start > 1) {
            long mid = (end + start) / 2;

            if (check(arr, mid, k)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str1 = reader.readLine();
        String str2 = reader.readLine();

        String[] input_1 = str1.split(" ");
        String[] input_2 = str2.split(" ");

        int count = Integer.parseInt(input_1[0]);
        long k = Long.parseLong(input_1[1]);
        long[] arr = new long[(int) count];
        for (int i = 0; i < count; i++) {
            arr[i] = Long.parseLong(input_2[i]);
        }

        if (count == 1){
            System.out.println(0);
        }
        else{
            long[] sortedArr = mergeSort(arr, count);
            System.out.println(binSearching(sortedArr, count, k));
        }
    }
}
