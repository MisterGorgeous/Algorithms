import edu.princeton.cs.algs4.StdIn;

public class Permutation {


    public static void main(String[] args) {
        if (args.length <= 0) return;
        int n = Integer.valueOf(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        for (int i = 0; i < n; i++) {
            queue.enqueue(StdIn.readString());
        }

        for (String randomString : queue) {
            System.out.println(randomString);
        }
    }
}
