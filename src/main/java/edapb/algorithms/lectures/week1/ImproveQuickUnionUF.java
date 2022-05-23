package edapb.algorithms.lectures.week1;

public class ImproveQuickUnionUF {
    private final int[] id;
    private final int[] sz;

    public ImproveQuickUnionUF(final int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // Chase the parent pointers until reach root. Now logN because of Weighted Union.
    // Path compression
    // Half path length by having each node point to its grandparent.
    private int root(int i) {
        while (i != id[i]) {
            // Make each object point to its grandparent.
            id[i] = id[id[i]];
            // Go up to node in id.
            i = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // Weighting trees
    // Find(connected) is affected by this because of the shorter height accomplished.
    public void union(int p, int q) {
        int treeRoot1 = root(p);
        int treeRoot2 = root(q);

        if (treeRoot1 == treeRoot2) {
            return;
        }

        // Based on tree size place smaller tree under bigger tree.
        // lgN because tree can double AT MOST logN and has depth of logN.
        if (sz[treeRoot1] < sz[treeRoot2]) {
            id[treeRoot1] = treeRoot2;
            sz[treeRoot2] += sz[treeRoot1];
        } else {
            id[treeRoot2] = treeRoot1;
            sz[treeRoot1] += sz[treeRoot2];
        }
    }
}
