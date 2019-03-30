import com.sun.deploy.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {
    private Deque<String> deque;

    @Before
    public void setup() {
        Deque<String> deque = new Deque<String>();
    }

    @Test
    public void isEmpty() {
       deque.addFirst("");
       deque.removeLast();

       assertTrue(deque.isEmpty());

        deque.addLast("");
        deque.removeFirst();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void size() {
    }

    @Test
    public void addFirst() {
    }

    @Test
    public void addLast() {
    }

    @Test
    public void removeFirst() {
    }

    @Test
    public void removeLast() {
    }

    @Test
    public void iterator() {
    }
}