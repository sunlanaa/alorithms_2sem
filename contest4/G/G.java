import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.xml.catalog.Catalog;

import java.util.*;

enum VertexStatus {
    WHITE,
    GREY,
    BLACK
}

class TaskG {
    public static boolean dfsik(int num, List<VertexStatus> used, Stack<Integer> vertexes,
                                List<List<Integer>> neighbours) {
        used.set(num, VertexStatus.GREY);
        boolean isPossibletoSort = false;
        for (int i = 0; i < neighbours.get(num).size(); ++i) {
            int neighbour = neighbours.get(num).get(i);
            if (used.get(neighbour) == VertexStatus.WHITE) {
                isPossibletoSort = dfsik(neighbour, used, vertexes, neighbours);
            } else if (used.get(neighbour) == VertexStatus.GREY) {
                isPossibletoSort = true;
            }
            if (isPossibletoSort) {
                return isPossibletoSort;
            }
        }
        vertexes.push(num);
        used.set(num, VertexStatus.BLACK);
        return isPossibletoSort;
    }

    public static void dfs(List<VertexStatus> used, Stack<Integer> vertexes,
                           List<List<Integer>> neighbours) {
        boolean isPossibletoSort = false;
        for (int i = 0; i < used.size(); ++i) {
            if (used.get(i) == VertexStatus.WHITE) {
                isPossibletoSort = dfsik(i, used, vertexes, neighbours);
                if (isPossibletoSort) {
                    System.out.println("-1");
                    return;
                }
            }
        }
        while (!vertexes.empty()) {
            System.out.print((vertexes.peek() + 1) + " ");
            vertexes.pop();
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
        List<VertexStatus> used = new ArrayList<>(Collections.nCopies(number, VertexStatus.WHITE));
        Stack<Integer> vertexes = new Stack<>();
        dfs(used, vertexes, neighbours);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
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