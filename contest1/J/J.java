import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

class Deque {
    private int capacity;
    private int size;
    private int[] data;
    private int front;
    private int tail;

    public Deque(int capacity) {
        this.capacity = Math.max(capacity, 1);
        this.data = new int[this.capacity];
        this.front = -1;
        this.tail = -1;
        this.size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void pushFront(int element) { // boolean
        if (isEmpty()) {
            front = tail = 0;
            data[0] = element;
        } else if (front == 0) {
            front = capacity - 1;
            data[front] = element;
        } else {
            data[--front] = element;
        }
        size++;
        System.out.println("ok");
    }

    public void pushBack(int element) {
        if (isEmpty()) {
            front = tail = 0;
            data[tail] = element;
        } else if (tail == capacity - 1) {
            tail = 0;
            data[tail] = element;
        } else {
            data[++tail] = element;
        }
        size++;
        System.out.println("ok");
    }

    public int popFront() {
        int ans = data[front];
        data[front] = 0;
        if (front == capacity - 1) {
            front = 0;
        } else {
            front++;
        }
        size--;
        return ans;
    }

    public int popBack() {
        int ans = data[tail];
        data[tail] = 0;
        if (tail == 0) {
            tail = capacity - 1;
        } else {
            tail--;
        }

        size--;
        return ans;
    }

    public int front_el() {
        return data[front];
    }

    public int back_el() {
        return data[tail];
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        int[] data = new int[capacity];
        front = -1;
        tail = 0;
        size = 0;
        System.out.println("ok");
    }

    public void exit() {
        System.out.println("bye");
    }


    public void print() {
//        if (front <= tail) {
//            for (int i = front; i < tail + 1; i++) {
//                System.out.print(data[i] + " ");
//            }
//        } else {
//            for (int i = front; i < capacity; i++) {
//                System.out.print(data[i] + " ");
//            }
//            for (int i = 0; i < tail + 1; i++) {
//                System.out.print(data[i] + " ");
//            }
//        }
//        System.out.println();
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String count = reader.readLine();
        Deque deque1 = new Deque(Integer.parseInt(count));

        for (int i = 0; i < Integer.parseInt(count); i++) {
            String str = reader.readLine();
            String[] command = str.split(" ");
            if (Objects.equals(command[0], "push_front")) {
                deque1.pushFront(Integer.parseInt(command[1]));
            } else if (Objects.equals(command[0], "push_back")) {
                deque1.pushBack(Integer.parseInt(command[1]));
            } else if (Objects.equals(command[0], "pop_front") && (!deque1.isEmpty())) {
                System.out.println(deque1.popFront());
            } else if (Objects.equals(command[0], "pop_back") && (!deque1.isEmpty())) {
                System.out.println(deque1.popBack());
            } else if (Objects.equals(command[0], "front") && (!deque1.isEmpty())) {
                System.out.println(deque1.front_el());
            } else if (Objects.equals(command[0], "back") && (!deque1.isEmpty())) {
                System.out.println(deque1.back_el());
            }else if (Objects.equals(command[0], "size")) {
                System.out.println(deque1.getSize());
            } else if (Objects.equals(command[0], "clear")) {
                deque1.clear();
            } else if (Objects.equals(command[0], "exit")) {
                deque1.exit();
                break;
            } else{
                System.out.println("error");
            }
        }
    }
}
