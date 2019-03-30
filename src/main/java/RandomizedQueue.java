import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Node first;


    public RandomizedQueue() {
        first = null;
        n = 0;
        assert check();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
        assert check();
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int randomNumber = getRandomNumber();
        Node sample = first;

        if (randomNumber > 0) {
            for (int i = 0; i < randomNumber - 1; i++) {
                sample = sample.next;
            }
        }
        Node nextNext = sample.next.next;

        Item result = sample.next.item;

        if (sample.next.next != null) {
            sample.next = nextNext;
        } else {
            sample.next = null;
        }
        n--;

        return result;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int randomNumber = getRandomNumber();
        Node sample = first;

        for (int i = 0; i < randomNumber; i++) {
            sample = sample.next;
        }
        return sample.item;
    }                     // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private int getRandomNumber() {
        return StdRandom.uniform(0, n);
    }

    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        } else if (n == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        } else {
            if (first == null) return false;
            if (first.next == null) return false;
        }

        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }


    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current;
        private int size;

        RandomizedQueueIterator() {
            this.size = n;
            current = new Node();

            Node copyFirst = first;
            Node copyCurrent = current;

            for (int i = 0; i < size; ++i) {
                copyCurrent.item = copyFirst.item;
                copyCurrent.next = copyFirst.next;
                copyCurrent = copyCurrent.next;
                copyFirst = copyFirst.next;
            }

        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int randomNumber = StdRandom.uniform(0, size);
            Node sample = current;

            if (randomNumber > 0) {
                for (int i = 0; i < randomNumber - 1; i++) {
                    sample = sample.next;
                }
            }
            if (sample.next == null) {
                current = null;
                size--;
                return sample.item;
            }
            Node nextNext = sample.next.next;

            Item result = sample.next.item;

            if (sample.next.next != null) {
                sample.next = nextNext;
            } else {
                sample.next = null;
            }
            size--;

            return result;
        }
    }

    private class Node {
        private Item item;
        private Node next;
    }
}