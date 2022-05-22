package edapb.algorithms.lectures.week1;

public class QuickFindUF {
    private final int[] id;

    public QuickFindUF(final int n) {
        id = new int[n];
        // Each index i is set to i! 1 2 3...
        for (int i = 0; i < n; ++i) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // Go through array n times set to qId if pId
    // Do N unions would quadratic time the algorithm is very slow with huge problems.
    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }
}
