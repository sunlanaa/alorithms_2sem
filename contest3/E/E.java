import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RMQ {
    ArrayList<Integer> arr;
    int size;
    int maxSize;
    int[][] sparseTable;

    RMQ(ArrayList<Integer> arr) {
        this.arr = arr;
        this.size = arr.size();
        double log = Math.log(size) / Math.log(2);
        this.maxSize = (int) log + 1;
    }

    public void buildSparseTable() {
        sparseTable = new int[maxSize][size];
        for (int x = 0; x < size; x++) {
            sparseTable[0][x] = arr.get(x);
        }
        for (int k = 0; k + 1 < maxSize; k++) {
            for (int ind = 0; ind + (1 << k) < size; ind++) {
                sparseTable[k + 1][ind] = Math.min(sparseTable[k][ind], sparseTable[k][ind + (1 << k)]);
            }
        }
    }

    public int FindMin(int l, int r) {
        if (l == r) {
            return 1000000000;
        }
        double k2 = Math.log(r - l) / Math.log(2);
        int k = (int) k2;
        return Math.min(sparseTable[k][l], sparseTable[k][r - (1 << k)]);
    }

    public static void main(String[] args) throws IOException {
        try (Scanner input = new Scanner(System.in)) {
            int size = input.nextInt();
            int count = input.nextInt();
            ArrayList<Integer> arr = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arr.add(input.nextInt());
            }
            RMQ rmq = new RMQ(arr);
            rmq.buildSparseTable();
            for (int i = 0; i < count; i++) {
                int l = input.nextInt();
                int r = input.nextInt();
                int firstMin = rmq.FindMin(l - 1, r);
                int index = arr.indexOf(firstMin);
                System.out.println(Math.min(rmq.FindMin(l - 1, index), rmq.FindMin(index + 1, r)));
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}