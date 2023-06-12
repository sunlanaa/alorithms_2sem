import java.util.*;

class DST {
    private int[] parent;
    private int[] rank;

    public DST(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    public int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    public void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            if (rank[rootU] < rank[rootV]) {
                int temp = rootU;
                rootU = rootV;
                rootV = temp;
            }
            parent[rootV] = rootU;
            if (rank[rootU] == rank[rootV]) {
                rank[rootU]++;
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    public int from;
    public int to;
    public int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class Main {
    public static int kruskal(int n, List<Edge> list) {
        Collections.sort(list);
        DST vertexes = new DST(n);
        int ans = 0;
        int cycle = 0;
        int iter = 0;
        while (cycle != n - 1) {
            int x = list.get(iter).from;
            int y = list.get(iter).to;
            if (!vertexes.isSame(x, y)) {
                vertexes.union(x, y);
                ans += list.get(iter).weight;
                cycle++;
            }
            iter++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();
            list.add(new Edge(from, to, weight));
        }
        System.out.println(kruskal(n, list));
    }
}