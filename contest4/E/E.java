import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class E {
    static List<Integer> bfs(int first, int second, int number, List<List<Integer>> neighbours) {
        List<Boolean> used = new ArrayList<>(Collections.nCopies(number, false));
        List<Integer> parent = new ArrayList<>(Collections.nCopies(number, 0));
        Deque<Integer> queue = new LinkedList<>();
        queue.add(first);
        used.set(first, true);
        parent.set(first, 0);
        int num = first;
        boolean isWay= false;
        while (!queue.isEmpty()) {
            num = queue.peek();
            for (int i = 0; i < neighbours.get(num).size(); ++i) {
                if (!used.get(neighbours.get(num).get(i))) {
                    if (neighbours.get(num).get(i) != second) {
                        used.set(neighbours.get(num).get(i), true);
                        parent.set(neighbours.get(num).get(i), num);
                        queue.addLast(neighbours.get(num).get(i));
                    } else {
                        parent.set(second, num);
                        isWay = true;
                        break;
                    }
                }
            }
            if (isWay) {
                break;
            }
            queue.removeFirst();
        }
        if (!isWay) {
            return Collections.emptyList();
        } else {
            List<Integer> ans = new ArrayList<>();
            int vertex = second;
            ans.add(second);
            while (vertex != first) {
                int f = vertex;
                vertex = parent.get(f);
                ans.add(vertex);
            }
            return ans;
        }
    }

    static void worker(int first, int second, int number, List<Pair<Integer, Integer>> connects) {
        List<List<Integer>> neighbours = new ArrayList<>();
        for (int i = 0; i < number; ++i) {
            neighbours.add(new ArrayList<>());
        }
        for (int i = 0; i < connects.size(); ++i) {
            int firstConnect = connects.get(i).getL() - 1;
            int secondConnect = connects.get(i).getR() - 1;
            neighbours.get(firstConnect).add(secondConnect);
            neighbours.get(secondConnect).add(firstConnect);
        }
        if (first == second) {
            System.out.println(0);
            System.out.println(first + 1);
        } else {
            List<Integer> path = bfs(first, second, number, neighbours);
            if (path.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(path.size() - 1);
                for (int i = path.size() - 1; i >= 0; --i) {
                    System.out.print(path.get(i) + 1 + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int number = scanner.nextInt();
            int count = scanner.nextInt();
            List<Pair<Integer, Integer>> connects = new ArrayList<>();
            int start = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            for (int i = 0; i < count; ++i) {
                int elemA = scanner.nextInt();
                int elemB = scanner.nextInt();
                Pair<Integer, Integer> newPair = new Pair<>(elemA, elemB);
                connects.add(newPair);
            }
            worker(start, end, number, connects);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

class Pair<L, R> {
    private L l;
    private R r;

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }
}