import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{
    private LineSegment[] result;
    private int numberOfSegments = 0;

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
        {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; ++i)
        {
            if (points[i] == null)
            {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; ++i)
        {
            for (int j = i + 1; j < points.length; ++j)
            {
                if (points[i].compareTo(points[j]) == 0)
                {
                    throw new IllegalArgumentException();
                }
            }
        }

        result = new LineSegment[(int) Math.pow(points.length, 4)];

        Point[] pointToOperate = Arrays.copyOf(points, points.length);
        double[] slopes = new double[points.length];

        for (int i = 0; i < points.length; i++)
        {
            Arrays.sort(pointToOperate, points[i].slopeOrder());

            for (int j = 0; j < pointToOperate.length; j++)
            {
                slopes[j] = points[i].slopeTo(points[j]);
            }

            double slope = Double.POSITIVE_INFINITY;

            for (int k = 1; k < pointToOperate.length; k++)
            {
                int index = Arrays.asList(points).indexOf(pointToOperate[k]);

                if (k == 1)
                {
                    slope = slopes[index];
                }
                else
                {
                    if (slopes[index] != slope)
                    {
                        if (k > 3)
                        {
                            Point[] collinearPoints = Arrays.copyOf(pointToOperate, k);
                            Arrays.sort(collinearPoints, Point::compareTo);
                            LineSegment segment = new LineSegment(collinearPoints[0], collinearPoints[k - 1]);
                            result[numberOfSegments++] = segment;
                        }
                        else
                        {
                            break;
                        }

                    }
                }

            }
        }

        result = Arrays.copyOf(result, numberOfSegments);
    }

    public int numberOfSegments()
    {
        return numberOfSegments;
    }

    public LineSegment[] segments()
    {
        return result;
    }

    public static void main(String[] args)
    {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points)
        {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments())
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
