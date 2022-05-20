/**
 * RandomWord.java is a program that reads a sequence of words from standard
 * input and prints one of those words uniformly at random.
 *
 * The Knuth's method selects the ith word with a probability of 1/i to be
 * champion avoiding the use of arrays.
 *
 * @author Elian
 * @version 5.19.22
 */
package edapb.algorithms.assignment0;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(final String[] args) {
        if (StdIn.isEmpty()) {
            return;
        }

        // Only one competitor at the start, so they are the default champion.
        String competitor = StdIn.readString();
        String champion = competitor;
        int index = 1;
        while (!StdIn.isEmpty()) {
            competitor = StdIn.readString();
            ++index;
            if (StdRandom.bernoulli(1.0D / index)) {
                champion = competitor;
            }
        }

        System.out.println(champion);
    }
}
