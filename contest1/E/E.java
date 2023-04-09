import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class E {
    public static long Area(long[] arr) {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        int top;
        long areaTop;
        int i = 0;
        long n = arr.length;
        while (i < n) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.push(i++);
            } else {
                top = stack.pop();
                int len = 0;
                if (stack.isEmpty()) {
                    len = i;
                } else {
                    len = i - stack.peek() - 1;
                }
                areaTop = (long) arr[top] * len;
                maxArea = Math.max(maxArea, areaTop);
            }
        }
        while (!stack.empty()) {
            top = stack.pop();
            int len = 0;
            if (stack.isEmpty()) {
                len = i;
            } else {
                len = i - stack.peek() - 1;
            }
            areaTop = (long) arr[top] * len;
            maxArea = Math.max(maxArea, areaTop);
        }
        return maxArea;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        String[] input = str.split(" ");
        long[] arr = new long[Integer.parseInt(input[0])];
        for (int i = 1; i < Integer.parseInt(input[0]) + 1; i++) {
            arr[i - 1] = Integer.parseInt(input[i]);
        }
        System.out.println(Area(arr));
    }
}
