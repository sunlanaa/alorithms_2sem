import java.util.*;

class Hashmap {
    private int capacity = 10000;
    private List<Double>[] data;

    public Hashmap() {
        data = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new ArrayList<>();
        }
    }

    public boolean exists(double key) {
        int newHash = Double.hashCode(key) % capacity;
        for (double value : data[newHash]) {
            if (value == key) {
                return true;
            }
        }
        return false;
    }

    public void insert(double key) {
        if (exists(key)) {
            return;
        }
        int newHash = Double.hashCode(key) % capacity;
        data[newHash].add(key);
    }

    public void erase(double key) {
        if (!exists(key)) {
            return;
        }
        int newHash = Double.hashCode(key) % capacity;
        for (int i = 0; i < data[newHash].size(); i++) {
            if (data[newHash].get(i) == key) {
                data[newHash].remove(i);
                return;
            }
        }
    }
}

class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Hashmap hash = new Hashmap();
        for (int i = 0; i < n; i++) {
            String a = scanner.next();
            double val = scanner.nextDouble();
            if (a.equals("+")) {
                hash.insert(val);
            } else if (a.equals("-")) {
                hash.erase(val);
            } else {
                if (hash.exists(val)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}