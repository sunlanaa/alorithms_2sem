import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class StackWithMin extends Stackk {
    Stackk stack2;
    int size;
    int top;

    public StackWithMin(int m) {
        super(m);
        this.size = m;
        this.stack2 = new Stackk(m);
    }

    public void push(int value) {
        if (value <= min()) {
            stack2.push(value);
        }
        super.push(value);
    }

    public int pop() {
        int value = super.pop();
        if (value == min()) {
            stack2.pop();
        }
        return value;
    }

    public int min() {
        if (stack2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return stack2.back();
        }
    }

    public String get() {
        return stack2.get();
    }

    public void clear() {
        stack2.clear();
    }
}

class Stackk {
    private int size;
    private int[] array;
    private int top;

    public Stackk(int m) {
        this.size = 0;
        this.array = new int[m];
        this.top = -1;
    }

    public void push(int element) {
        array[++top] = element;
    }

    public int pop() {
        int elem = array[top];
        array[top--] = 0;
        return elem;
    }

    public int back() {
        return array[top];

    }

    public int size() {
        return top + 1;

    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }
        top = -1;
        size = 0;
    }

    public String get() {
        return Arrays.toString(array);
    }

}

public class QueueC {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String count = reader.readLine();
        Stackk add = new Stackk(Integer.parseInt(count));
        Stackk del = new Stackk(Integer.parseInt(count));
        StackWithMin stackMin_add = new StackWithMin(Integer.parseInt(count));
        StackWithMin stackMin_del = new StackWithMin(Integer.parseInt(count));

        for (int i = 0; i < Integer.parseInt(count); i++) {
            String str = reader.readLine();
            if (str.length() > 8) {
                add.push(Integer.parseInt(str.split(" ")[1]));
                System.out.println("ok");
                stackMin_add.push(Integer.parseInt(str.split(" ")[1]));
            } else if (str.equals("dequeue") && (!add.isEmpty() || !del.isEmpty())) {
                if (del.isEmpty()) {
                    while (!add.isEmpty()) {
                        int elem = add.pop();
                        del.push(elem);
                        stackMin_del.push(elem);
                        stackMin_add.pop();
                    }
                }
                System.out.println(del.pop());
                stackMin_del.pop();
            } else if (str.equals("front") && (!add.isEmpty() || !del.isEmpty())) {
                if (del.isEmpty()) {
                    while (!add.isEmpty()) {
                        int elem = add.pop();
                        del.push(elem);
                        stackMin_del.push(elem);
                        stackMin_add.pop();
                    }
                }
                System.out.println(del.back());
            } else if (str.equals("size")) {
                System.out.println(add.size() + del.size());
            } else if (str.equals("clear")) {
                add.clear();
                del.clear();
                stackMin_add.clear();
                stackMin_del.clear();
                System.out.println("ok");
            } else if (str.equals("min") && (!add.isEmpty()||!del.isEmpty())) {
                System.out.println(Math.min(stackMin_add.min(), stackMin_del.min()));
            } else {
                System.out.println("error");
            }
        }
    }
}
