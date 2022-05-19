/**
 * RandomWord.java is a program that reads a sequence of words from standard input and prints one of those words
 * uniformly at random.
 *
 * The Knuth's method selects the ith word with a probability of 1/i to be the champion avoiding the use of arrays.
 *
 * @author Elian
 * @version 5.19.22
 */
package edapb.algorithms.assignment0;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        // First word will always win because 1/1 = 1, so, we can set the first string is the champion.
        String champion = StdIn.readString();

        String competitor;
        int index = 1;
        while (!StdIn.isEmpty()) {
            competitor = StdIn.readString();
            ++index;
            if (StdRandom.bernoulli(1.0D/index)) {
                champion = competitor;
            }
        }

        System.out.println(champion);
    }
}
