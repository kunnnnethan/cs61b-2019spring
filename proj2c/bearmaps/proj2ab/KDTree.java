package bearmaps.proj2ab;
import java.util.List;
import static bearmaps.proj2ab.Point.distance;

public class KDTree implements PointSet {

    TreeNode treeRoot;
    int size;

    private class TreeNode {
        private Point point;
        private int count;
        private TreeNode leftChild;
        private TreeNode rightChild;

        private TreeNode(Point point, int count) {
            this.point = point;
            this.count = count;
            leftChild = null;
            rightChild = null;
        }
    }

    public KDTree() {
        treeRoot = null;
        size = 0;
    }

    public KDTree(List<Point> points) {
        for (Point point : points) {
            put(point);
        }
    }

    public void put(Point point) {
        treeRoot = put(point, treeRoot, 1);
    }

    private TreeNode put(Point point, TreeNode T, int count) {
        
        if (T == null) {
            size += 1;
            return new TreeNode(point, count);
        }
        
        if (T.count % 2 == 1) {
            if (point.getX() >= T.point.getX()) {
                T.rightChild = put(point, T.rightChild, 2);
            } else {
                T.leftChild = put(point, T.leftChild, 2);
            }
        } else {
            if (point.getY() >= T.point.getY()) {
                T.rightChild = put(point, T.rightChild, 1);
            } else {
                T.leftChild = put(point, T.leftChild, 1);
            }
        }
        return T;
    }

    @Override
    public Point nearest(double x, double y) {
        TreeNode best = nearest(treeRoot, new Point(x, y), treeRoot);
        return best.point;
    }
    
    private TreeNode nearest(TreeNode T, Point goal, TreeNode best) {
        TreeNode goodSide;
        TreeNode badSide;
        Point badSidePoint;
        
        if (T == null) {
            return best;
        }
        
        if (distance(T.point, goal) < distance(best.point, goal)) {
            best = T;
        }
        
        if (T.count % 2 == 1) {
            if (goal.getX() >= T.point.getX()) {
                goodSide = T.rightChild;
                badSide = T.leftChild;
                badSidePoint = T.point;
            } else {
                goodSide = T.leftChild;
                badSide = T.rightChild;
                badSidePoint = T.point;
            }
        } else {
            if (goal.getY() >= T.point.getY()) {
                goodSide = T.rightChild;
                badSide = T.leftChild;
                badSidePoint = T.point;
            } else {
                goodSide = T.leftChild;
                badSide = T.rightChild;
                badSidePoint = T.point;
            }
        }
        best = nearest(goodSide, goal, best);
        
        if (T.count % 2 == 1) {
            if (Math.pow(Math.abs(goal.getX() - badSidePoint.getX()), 2) < distance(best.point, goal)) {
                best = nearest(badSide, goal, best);
            }
        } else {
            if (Math.pow(Math.abs(goal.getY() - badSidePoint.getY()), 2) < distance(best.point, goal)) {
                best = nearest(badSide, goal, best);
            }
        }
        
        return best;
    }


}
