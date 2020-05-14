package bearmaps;

import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static bearmaps.Point.distance;
import static org.junit.Assert.assertEquals;

public class NaivePointSet implements PointSet {

    List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point refPoint = new Point(x, y);
        double nearestDistance = Integer.MAX_VALUE;
        Point nearestPoint = null;

        for (Point point : points) {
            double distance = distance(point, refPoint);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPoint = point;
            }
        }

        if (nearestPoint == null) {
            throw new NoSuchElementException();
        }

        return nearestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4

        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        nn = new NaivePointSet(List.of(A, B, C, D, E, F));
        ret = nn.nearest(0, 7);
        System.out.println(ret.getX()); // evaluates to 1
        System.out.println(ret.getY()); // evaluates to 5
    }
}
