package edapb.algorithms.assignment0;

public class HelloGoodbye {
    public static void main(String[] args) {
        final int minNumOfNames = 2;
        if (args.length < minNumOfNames)
            return;

        System.out.println("Hello " + args[0] + " and " + args[1] + ".");
        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
    }
}
