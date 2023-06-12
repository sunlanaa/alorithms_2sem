import java.util.Scanner;

public class C {
    public static int[] prefixFunc(String a) {
        int[] vec = new int[a.length()];
        vec[0] = 0;
        int h = 0;
        for (int i = 1; i < a.length(); ++i) {
            while (h != 0 && a.charAt(h) != a.charAt(i)) {
                h = vec[h - 1];
            }
            if (a.charAt(h) == a.charAt(i)) {
                ++h;
            }
            vec[i] = h;
        }
        return vec;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append(scanner.nextLine()).append("\n");
        inputBuilder.append(scanner.nextLine()).append("\n");
        String input = inputBuilder.toString();
        String[] inputLines = input.split("\n");
        String a = inputLines[0];
        String b = inputLines[1];
        int[] links = prefixFunc(b);
        int h = 0;
        StringBuilder outputBuilder = new StringBuilder();
        for (int i = 0; i < a.length(); ++i) {
            while (h != 0 && b.charAt(h) != a.charAt(i)) {
                h = links[h - 1];
            }
            if (b.charAt(h) == a.charAt(i)) {
                ++h;
            }
            if (h == b.length()) {
                outputBuilder.append(i - b.length() + 1).append("\n");
                h = links[h - 1];
            }
        }
        System.out.println(outputBuilder.toString().trim());
    }
}