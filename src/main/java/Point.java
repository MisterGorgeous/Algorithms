import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point {
    private final double x;
    private final double y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        return y > that.y || (y == that.y && x > that.x) ? 1 : y == that.y && x == that.x ? 0 : -1;
    }

    public double slopeTo(Point that) {
        if (x == that.x && y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        return (that.y - y) / (that.x - x);
    }

    public Comparator<Point> slopeOrder() {
        return (a, b) -> (int) (this.slopeTo(a) - this.slopeTo(b));
    }

}
