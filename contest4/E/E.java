
import java.util.*;

public class E {
    static void Bfs(int first, int second, int number, List<List<Integer>> neighbours) {
        List<Boolean> used = new ArrayList<>(Collections.nCopies(number, false));
        List<Integer> parent = new ArrayList<>(Collections.nCopies(number, 0));
        Deque<Integer> queue = new LinkedList<>();
        queue.add(first);
        used.set(first, true);
        parent.set(first, 0);
        int num = first;
        boolean det = false;
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
                        det = true;
                        break;
                    }
                }
            }
            if (det) {
                break;
            }
            queue.removeFirst();
        }
        if (!det) {
            System.out.println(-1);
        } else {
            List<Integer> ans = new ArrayList<>();
            int vertex = second;
            ans.add(second);
            while (vertex != first) {
                int f = vertex;
                vertex = parent.get(f);
                ans.add(vertex);
            }
            System.out.println(ans.size() - 1);
            for (int i = ans.size() - 1; i >= 0; --i) {
                System.out.print(ans.get(i) + 1 + " ");
            }
        }
    }

    static void Worker(int first, int second, int number, List<Pair<Integer, Integer>> connects) {
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
            Bfs(first, second, number, neighbours);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        Worker(start, end, number, connects);
    }
}
class Pair<L,R> {
    private L l;
    private R r;
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
}