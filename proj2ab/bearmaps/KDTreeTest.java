package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void test1() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(3.3, ret.getX(), 0.1);
        assertEquals(4.4, ret.getY(), 0.1);
    }

    @Test
    public void test2() {
        Point A = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        KDTree nn = new KDTree(List.of(A, B, C, D, E, F));
        Point ret = nn.nearest(0, 7); // returns p2
        assertEquals(1, ret.getX(), 0.1);
        assertEquals(5, ret.getY(), 0.1);
    }

    // randomPoint method
    private List<Point> randomPoints(int num) {
        List<Point> points = new LinkedList<>();

        for (int i = 0; i < num; i += 1) {
            Random random = new Random();
            points.add(new Point(random.nextDouble(), random.nextDouble()));
        }

        return points;
    }

    private void testWithNPointsAndNQueries(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        List<Point> queries = randomPoints(queryCount);
        for (Point point : queries) {
            Point expected = nps.nearest(point.getX(), point.getY());
            Point actual = kd.nearest(point.getX(), point.getY());
            assertEquals(expected, actual);
        }
    }

    private void npsRunningTime(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        NaivePointSet nps = new NaivePointSet(points);
        List<Point> queries = randomPoints(queryCount);
        for (Point point : queries) {
            nps.nearest(point.getX(), point.getY());
        }
    }

    private void kdRunningTime(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        KDTree kd = new KDTree(points);
        List<Point> queries = randomPoints(queryCount);
        for (Point point : queries) {
            kd.nearest(point.getX(), point.getY());
        }
    }

    @Test
    public void testWith1000PointsAnd200Queries() {
        testWithNPointsAndNQueries(10000, 2000);
    }

    @Test
    public void testTimeWithVariousPointsAnd10000Queries() {
        List<Integer> points = List.of(100, 1000, 10000, 100000);

        for (Integer point : points) {
            Stopwatch sw = new Stopwatch();
            testWithNPointsAndNQueries(point, 10000);
            System.out.println("Total time elapsed for " + point + " point is: " + sw.elapsedTime() +  " seconds.");
        }
    }

    @Test
    public void testKDRunningTimeWithVariousPointsAnd10000Queries() {
        List<Integer> points = List.of(100, 1000, 10000, 100000, 1000000);

        for (Integer point : points) {
            Stopwatch sw = new Stopwatch();
            kdRunningTime(point, 10000);
            System.out.println("Total time elapsed for " + point + " point is: " + sw.elapsedTime() +  " seconds.");
        }

        System.out.println("Nailed it!");
    }

    public static void main(String[] args) {
        KDTreeTest test = new KDTreeTest();

        Stopwatch sw = new Stopwatch();
        test.npsRunningTime(100000, 10000);
        System.out.println("Total time elapsed for nps is: " + sw.elapsedTime() +  " seconds.");

        sw = new Stopwatch();
        test.kdRunningTime(100000, 10000);
        System.out.println("Total time elapsed for kd is: " + sw.elapsedTime() +  " seconds.");
    }
}
