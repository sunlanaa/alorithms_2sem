import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class D {
    final static int COUNT_OF_POINTS_FROM_0_TO_300 = 301;

    public static Node[] stable(int count, int[] cnt, List<Node> arr) {
        int[] pref = new int[COUNT_OF_POINTS_FROM_0_TO_300];
        Node[] res = new Node[count];
        pref[0] = 0;
        for (int i = 1; i < COUNT_OF_POINTS_FROM_0_TO_300; i++) {
            pref[i] = pref[i - 1] + cnt[i - 1];
        }
        for (int i = count - 1; i >= 0; i--) {
            res[pref[arr.get(i).getSumMarks()]] = arr.get(i);
            pref[arr.get(i).getSumMarks()]++;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String strCount = reader.readLine();
            int count = Integer.parseInt(strCount);
            List<Node> arr = new ArrayList<>();
            int[] cnt = new int[COUNT_OF_POINTS_FROM_0_TO_300];
            for (int i = 0; i < count; i++) {
                String str = reader.readLine();
                String[] splitStr = str.split(" ");
                int sumMarks = Integer.parseInt(splitStr[2]) + Integer.parseInt(splitStr[3]) + Integer.parseInt(splitStr[4]);
                arr.add(new Node(splitStr[1], splitStr[0], sumMarks));
                ++cnt[sumMarks];
            }
            Node[] res = stable(count, cnt, arr);
            for (int i = count - 1; i >= 0; i--) {
                System.out.println(res[i].getSurname() + " " + res[i].getName());
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}

class Node {
    private String name;
    private String surname;
    private int sumMarks;

    public Node(String name, String surname, int sumMarks) {
        this.name = name;
        this.surname = surname;
        this.sumMarks = sumMarks;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getSumMarks() {
        return sumMarks;
    }
}