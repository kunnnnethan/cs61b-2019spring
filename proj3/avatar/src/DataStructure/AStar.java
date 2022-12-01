package DataStructure;

import Avatar.Avatar;
import Avatar.Point;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStar {
    private Outcome outcome;
    private LinkedList<Point> solution = new LinkedList<>();


    /**
     *  Constructor which finds the solution, computing everything necessary for all other methods
     *  to return their results in constant time. Note that timeout passed in is in seconds.
     * @param input
     * @param start
     * @param end
     * @param timeout
     */
    public AStar(Graph input, Point start, Point end, double timeout) {
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ fringe = new ArrayHeapMinPQ();
        HashMap<Point, Double> distTo = new HashMap<>();
        HashMap<Point, Point> edgeTo = new HashMap<>();

        fringe.add(start, 0);
        while (!fringe.isEmpty() || fringe.getSmallest().equals(end) || sw.elapsedTime() > timeout) {
            Point source = (Point) fringe.removeSmallest();

            List<WeightedEdge<Point>> neighborEdges = input.neighbors(source);
            for (WeightedEdge<Point> e : neighborEdges) {

                // relax
                Point p = e.from();
                Point q = e.to();
                double w = e.weight();
                if (p.equals(start)) {
                    distTo.put(p, 0.0);
                    distTo.put(q, w);
                }
                if (!distTo.containsKey(p)) {
                    distTo.put(p, distTo.get(p));
                }
                if (!distTo.containsKey(q)) {
                    distTo.put(q, distTo.get(p) + w);
                }
                if (distTo.get(p) + w <= distTo.get(q)) {
                    distTo.put(q, distTo.get(p) + w);
                    if (fringe.contains(q)) {
                        fringe.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                        edgeTo.put(q, p); // q comes from p; change the edge as well when relax the edge
                    } else {
                        fringe.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                        edgeTo.put(q, p); // q comes from p
                    }
                }
            }

            if (fringe.getSmallest().equals(end)) {
                outcome = Outcome.SOLVED;

                // Add vertex to solution
                Point temp = end;
                solution.addFirst(end);
                while (!temp.equals(start)) {
                    solution.addFirst(edgeTo.get(temp));
                    temp = edgeTo.get(temp);
                }
                return;
            }

            if (sw.elapsedTime() > timeout) {
                solution.clear();
                outcome = Outcome.TIMEOUT;
                return;
            }
        }
        solution.clear();
        outcome = Outcome.UNSOLVABLE;
    }

    /**
     * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStar was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.
     * @return
     */
    public Outcome outcome() {
        return outcome;
    }

    /**
     * A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    public List<Point> solution() {
        return solution;
    }
}
