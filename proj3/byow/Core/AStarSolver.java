package byow.Core;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver {
    private SolverOutcome outcome;
    private double solutionWeight;
    private LinkedList<Point> solution = new LinkedList<>();
    private double timeSpent;
    private int timeOfEnqueueAndDequeue = 0;


    /**
     *  Constructor which finds the solution, computing everything necessary for all other methods
     *  to return their results in constant time. Note that timeout passed in is in seconds.
     * @param input
     * @param start
     * @param end
     * @param timeout
     */
    public AStarSolver(Graph input, Point start, Point end, double timeout) {
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ fringe = new ArrayHeapMinPQ();
        HashMap<Point, Double> distTo = new HashMap<>();
        HashMap<Point, Point> edgeTo = new HashMap<>();

        fringe.add(start, 0);
        timeOfEnqueueAndDequeue += 1;
        while (!fringe.isEmpty() || fringe.getSmallest().equals(end) || sw.elapsedTime() > timeout) {
            Point source = (Point) fringe.removeSmallest();
            timeOfEnqueueAndDequeue += 1;

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
                        timeOfEnqueueAndDequeue += 1;
                    }
                }
            }

            if (fringe.getSmallest().equals(end)) {
                solutionWeight = distTo.get(end);
                outcome = SolverOutcome.SOLVED;

                // Add vertex to solution
                Point temp = end;
                solution.addFirst(end);
                while (!temp.equals(start)) {
                    solution.addFirst(edgeTo.get(temp));
                    temp = edgeTo.get(temp);
                }
                timeSpent = sw.elapsedTime();
                return;
            }

            if (sw.elapsedTime() > timeout) {
                solution.clear();
                solutionWeight = 0;
                outcome = SolverOutcome.TIMEOUT;
                return;
            }
        }
        solution.clear();
        solutionWeight = 0;
        outcome = SolverOutcome.UNSOLVABLE;
        timeSpent = sw.elapsedTime();
    }

    /**
     * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.
     * @return
     */
    public SolverOutcome outcome() {
        return outcome;
    }

    /**
     * A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    public List<Point> solution() {
        return solution;
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    public double solutionWeight() {
        return solutionWeight;
    }

    /**
     * The total number of priority queue dequeue operations.
     * @return
     */
    public int numStatesExplored() {
        return timeOfEnqueueAndDequeue;
    }

    /**
     * The total time spent in seconds by the constructor.
     * @return
     */
    public double explorationTime() {
        return timeSpent;
    }
}
