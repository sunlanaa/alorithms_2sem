import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int index = partition(arr, left, right);
            quickSort(arr, left, index - 1);
            quickSort(arr, index + 1, right);
        }
    }

    public static int partition(int arr[], int left, int right) {
        int pivot = arr[right];
        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = swapTemp;
        return i + 1;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader1.readLine();
            int[] arr = new int[Integer.parseInt(s1)];
            for (int i = 0; i < arr.length; i++) {
                String str = reader1.readLine();
                arr[i] = Integer.parseInt(str);
            }
            quickSort(arr, 0, arr.length - 1);
            for (int i : arr) {
                System.out.print(i + " ");
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
