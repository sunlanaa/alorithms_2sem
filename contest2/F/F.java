import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class F {
    private static int leftChild(int i) {
        return (2 * i + 1);
    }

    private static int rightChild(int i) {
        return (2 * i + 2);
    }

    private static void swap(int[] sortArr, int i, int j) {
        int swap = sortArr[i];
        sortArr[i] = sortArr[j];
        sortArr[j] = swap;
    }

    private static void heapify(int[] sortArr, int i, int size) {
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        int largest = i;

        if (leftChild < size && sortArr[leftChild] > sortArr[i]) largest = leftChild;
        if (rightChild < size && sortArr[rightChild] > sortArr[largest]) largest = rightChild;

        if (largest != i) {
            swap(sortArr, i, largest);
            heapify(sortArr, largest, size);
        }
    }

    public static int pop(int[] sortArr, int size) {
        if (size <= 0) {
            return -1;
        }
        int top = sortArr[0];

        sortArr[0] = sortArr[size - 1];
        heapify(sortArr, 0, size - 1);
        return top;
    }

    public static void heapSort(int[] sortArr) {
        int n = sortArr.length;
        int i = (n - 2) / 2;
        while (i >= 0) {
            heapify(sortArr, i--, n);
        }
        while (n > 0) {
            sortArr[n - 1] = pop(sortArr, n);
            n--;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] notSortedArr = new int[(int) Math.pow(10, 6)];
        int sizeOfArray = 0;
        for (int i = 0; i < count; i++) {
            int size = Integer.parseInt(reader.readLine());
            String[] arr = reader.readLine().split(" ");
            for (int j = 0; j < size; j++) {
                notSortedArr[sizeOfArray] = Integer.parseInt(arr[j]);
                sizeOfArray++;
            }
        }
        int[] sortedArr = Arrays.copyOfRange(notSortedArr, 0, sizeOfArray);
        heapSort(sortedArr);
        for (int i = 0; i < sortedArr.length; i++) {
            System.out.print(sortedArr[i] + " ");
        }
    }
}
