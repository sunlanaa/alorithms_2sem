import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D {

    public static Node[] stable(int count, int[] cnt, List<Node> arr) {
        int[] pref = new int[301];
        Node[] res = new Node[count];
        pref[0] = 0;
        for (int i = 1; i < 301; i++) {
            pref[i] = pref[i - 1] + cnt[i - 1];
        }
        for (int i = count - 1; i >= 0; i--) {
            res[pref[arr.get(i).getSumMarks()]] = arr.get(i);
            pref[arr.get(i).getSumMarks()]++;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strCount = reader.readLine();
        int count = Integer.parseInt(strCount);
        List<Node> arr = new ArrayList<>();
        int[] cnt = new int[301];
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