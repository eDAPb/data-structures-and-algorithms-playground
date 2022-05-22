package edapb.algorithms.lectures.week1;

public class QuickUnionUF {
    private final int[] id;

    public QuickUnionUF(final int n) {
        id = new int[n];
        for (int i = 0; i < n; ++i) {
            id[i] = i;
        }
    }

    // Chase the parent pointers until reach root.
    private int root(int i) {
        while (i != id[i]) {
            // Go up one level in the tree
            i = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // Change root of p to point to root of q
    public void union(int p, int q) {
        int child = root(p);
        int parent = root(q);

        id[child] = parent;
    }
}
