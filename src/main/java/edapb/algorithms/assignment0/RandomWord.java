package edapb.algorithms.assignment0;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String output;
        while (!StdIn.isEmpty()) {
            output = StdIn.readString();
            if (StdRandom.bernoulli()) {
                System.out.println(output);
                break;
            }
        }
    }
}
