import java.util.*;

class G {
    public static boolean dfsik(int num, List<Integer> used, Stack<Integer> vertexes,
                                List<List<Integer>> neighbours) {
        used.set(num, 1);
        boolean det = false;
        for (int i = 0; i < neighbours.get(num).size(); ++i) {
            if (used.get(neighbours.get(num).get(i)) == 0) {
                det = dfsik(neighbours.get(num).get(i), used, vertexes, neighbours);
            } else if (used.get(neighbours.get(num).get(i)) == 1) {
                det = true;
            }
            if (det) {
                return det;
            }
        }
        vertexes.push(num);
        used.set(num, 2);
        return det;
    }

    public static void dfs(List<Integer> used, Stack<Integer> vertexes,
                           List<List<Integer>> neighbours) {
        boolean det = false;
        for (int i = 0; i < used.size(); ++i) {
            if (used.get(i) == 0) {
                det = dfsik(i, used, vertexes, neighbours);
                if (det) {
                    System.out.println("-1");
                    break;
                }
            }
        }
        if (!det) {
            while (!vertexes.empty()) {
                System.out.print((vertexes.peek() + 1) + " ");
                vertexes.pop();
            }
        }
    }

    public static void worker(int number, List<Pair<Integer, Integer>> connects) {
        List<List<Integer>> neighbours = new ArrayList<>();
        for (int i = 0; i < number; ++i) {
            neighbours.add(new ArrayList<>());
        }
        for (int i = 0; i < connects.size(); ++i) {
            int first = connects.get(i).getL() - 1;
            int second = connects.get(i).getR() - 1;
            neighbours.get(first).add(second);
        }
        List<Integer> used = new ArrayList<>(Collections.nCopies(number, 0));
        Stack<Integer> vertexes = new Stack<>();
        dfs(used, vertexes, neighbours);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int count = scanner.nextInt();
        List<Pair<Integer, Integer>> connects = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            int elem_a = scanner.nextInt();
            int elem_b = scanner.nextInt();
            Pair<Integer, Integer> new_pair = new Pair<>(elem_a, elem_b);
            connects.add(new_pair);
        }
        worker(number, connects);
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