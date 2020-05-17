package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private ArrayHeapMinPQ fringe = new ArrayHeapMinPQ();
    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private int timeOfEnqueueAndDequeue = 0;


    /**
     *  Constructor which finds the solution, computing everything necessary for all other methods
     *  to return their results in constant time. Note that timeout passed in is in seconds.
     * @param input
     * @param start
     * @param end
     * @param timeout
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        fringe.add(start, 0);
        timeOfEnqueueAndDequeue += 1;
        while (!fringe.isEmpty() || fringe.getSmallest().equals(end) || sw.elapsedTime() > timeout) {
            Vertex source = (Vertex) fringe.removeSmallest();
            timeOfEnqueueAndDequeue += 1;
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(source);
            for (WeightedEdge<Vertex> e : neighborEdges) {

                // relax
                Vertex p = e.from();
                Vertex q = e.to();
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
                        timeOfEnqueueAndDequeue += 1;
                    } else {
                        fringe.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                        timeOfEnqueueAndDequeue += 1;
                    }
                }
            }

            if (fringe.getSmallest().equals(end)) {
                solution = List.of(start, end);
                solutionWeight = distTo.get(end);
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                return;
            }

            if (sw.elapsedTime() > timeout) {
                solutionWeight = 0;
                outcome = SolverOutcome.TIMEOUT;
                return;
            }
        }
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
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /**
     * A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    @Override
    public List<Vertex> solution() {
        return solution;
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    /**
     * The total number of priority queue dequeue operations.
     * @return
     */
    @Override
    public int numStatesExplored() {
        return timeOfEnqueueAndDequeue;
    }

    /**
     * The total time spent in seconds by the constructor.
     * @return
     */
    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
