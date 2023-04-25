import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Deque {
    private int capacity;
    private int size;
    private int[] data;
    private int front;
    private int tail;

    public Deque() {
        this.capacity = 3;
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

    public void resize() {
        int[] newData = new int[capacity * 2];
        front = tail + 1;
        for (int i = 0; i < capacity; i++) {
            if (front == 0) {
                front = capacity * 2 - 1;
            } else {
                front--;
            }
            newData[front] = data[front % capacity];
        }
        data = newData;
        capacity *= 2;
    }

    public void pushFront(int element) { // boolean
        if (isFull()) {
            resize();
        }
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
    }

    public void pushBack(int element) {
        if (isFull()) {
            resize();
        }
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
    }

    public int popFront() {
        int ans = data[front];
        data[front] = 0;
        if (front == capacity - 1) {
            front = 0;
        } else if (ans == 0) {
            front += capacity - size;
            ans = data[front];
            data[front] = 0;
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
            tail = Math.abs(capacity) - 1;
        } else if (ans == 0) {
            tail -= capacity - size;
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
        front = -1;
        tail = -1;
        size = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String count = reader.readLine();
        Deque deque1 = new Deque();

        for (int i = 0; i < Integer.parseInt(count); i++) {
            String str = reader.readLine();
            String[] command = str.split(" ");
            switch (command[0]) {
                case "push_front":
                    deque1.pushFront(Integer.parseInt(command[1]));
                    System.out.println("ok");
                    break;
                case "push_back":
                    deque1.pushBack(Integer.parseInt(command[1]));
                    System.out.println("ok");
                    break;
                case "pop_front":
                    if (!deque1.isEmpty()) {
                        System.out.println(deque1.popFront());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "pop_back":
                    if (!deque1.isEmpty()) {
                        System.out.println(deque1.popBack());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "front":
                    if (!deque1.isEmpty()) {
                        System.out.println(deque1.front_el());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "back":
                    if (!deque1.isEmpty()) {
                        System.out.println(deque1.back_el());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "size":
                    System.out.println(deque1.getSize());
                    break;
                case "clear":
                    deque1.clear();
                    System.out.println("ok");
                    break;
                case "exit":
                    System.out.println("bye");
                    return;
                default:
                    System.out.println("error");
                    break;
            }
        }
    }
}

