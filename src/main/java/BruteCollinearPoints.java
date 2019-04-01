import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] result;
    private int numberOfSegments = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; ++i) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        result = new LineSegment[(int) Math.pow(points.length, 4)];

        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                for (int k = j + 1; k < points.length; ++k) {
                    for (int l = k + 1; l < points.length; ++l) {
                        double slopeIJ = points[i].slopeTo(points[j]);
                        double slopeJK = points[j].slopeTo(points[k]);
                        double slopeKL = points[k].slopeTo(points[l]);

                        if ((slopeIJ == slopeJK) && (slopeJK == slopeKL)) {
                            Point[] colinearPoints = {points[i], points[j], points[k], points[l]};
                            Arrays.sort(colinearPoints, Point::compareTo);
                            LineSegment segment = new LineSegment(colinearPoints[0], colinearPoints[3]);
                            result[numberOfSegments++] = segment;
                        }
                    }
                }
            }
        }

        result = Arrays.copyOf(result, numberOfSegments);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return result;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}

