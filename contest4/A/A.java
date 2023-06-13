import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Hashmap {
    int capacity = 3;
    List<Double>[] data;

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

    public void rehash() {
        List<Double>[] oldData = data;
        capacity *= 2;
        data = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new ArrayList<>();
        }
        for (List<Double> list : oldData) {
            for (double value : list) {
                insert(value);
            }
        }
    }
}

class A {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            Hashmap hash = new Hashmap();
            for (int i = 0; i < n; i++) {
                String operation = scanner.next();
                double val = scanner.nextDouble();
                if (operation.equals("+")) {
                    hash.insert(val);
                    if (hash.capacity == hash.data.length * 2) {
                        hash.rehash();
                    }
                } else if (operation.equals("-")) {
                    hash.erase(val);
                } else {
                    if (hash.exists(val)) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}