package DataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import Avatar.Point;

public class Graph {
    HashMap<Point, Point> points;

    public Graph() {
        points = new HashMap<>();
    }

    public void add(Point v) {
        points.put(v, v);
    }

    List<WeightedEdge<Point>> neighbors(Point v) {
        Point o = points.get(v);
        List<WeightedEdge<Point>> list = new LinkedList<>();

        if (points.containsKey(new Point(v.getX(), v.getY() + 1))) {
            list.add(new WeightedEdge<Point>(o, new Point(v.getX(), v.getY() + 1), 1));
        }
        if (points.containsKey(new Point(v.getX(), v.getY() - 1))) {
            list.add(new WeightedEdge<Point>(o, new Point(v.getX(), v.getY() - 1), 1));
        }
        if (points.containsKey(new Point(v.getX() - 1, v.getY()))) {
            list.add(new WeightedEdge<Point>(o, new Point(v.getX() - 1, v.getY()), 1));
        }
        if (points.containsKey(new Point(v.getX() + 1, v.getY()))) {
            list.add(new WeightedEdge<Point>(o, new Point(v.getX() + 1, v.getY()), 1));
        }
        return list;
    }

    double estimatedDistanceToGoal(Point s, Point goal) {
        return Math.abs(Point.distance(s, goal));
    }
}
