import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] a;
    private int firstIndex;
    private int lastIndex;

    public Deque() {
        a = (Item[]) new Object[2];
        firstIndex = 0;
        lastIndex = 2;
    }

    public boolean isEmpty() {
        return lastIndex == a.length && firstIndex == 0;
    }

    public int size() {
        return a.length - lastIndex + firstIndex;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (lastIndex == firstIndex) resize(2 * a.length);
        a[firstIndex++] = item;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (lastIndex == firstIndex) resize(2 * a.length);
        a[lastIndex--] = item;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[firstIndex - 1];
        a[firstIndex - 1] = null;
        firstIndex--;
        // shrink size of array if necessary
        if (isShouldBeShrinked()) resize(a.length / 2);
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[lastIndex];
        a[lastIndex] = null;
        lastIndex++;
        // shrink size of array if necessary
        if (isShouldBeShrinked()) resize(a.length / 2);
        return item;
    }

    private boolean isShouldBeShrinked() {
        return a.length - lastIndex + firstIndex == a.length / 4;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int currentIndex;

        public DequeIterator() {
            currentIndex = firstIndex;
        }

        public boolean hasNext() {
            return currentIndex <= firstIndex || currentIndex > lastIndex;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (currentIndex == 0) {
                currentIndex = a.length;
            }

            return a[--currentIndex];
        }
    }


    private void resize(int capacity) {
        assert capacity >= a.length - lastIndex + firstIndex;

        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < firstIndex; i++) {
            temp[i] = a[i];
        }

        int newLastIndex = capacity;

        for (int i = a.length - 1; i >= lastIndex; i--) {
            temp[newLastIndex--] = a[i];
        }

        a = temp;
        lastIndex = newLastIndex;
    }

}
