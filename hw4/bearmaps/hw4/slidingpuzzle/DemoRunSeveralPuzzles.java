package bearmaps.hw4.slidingpuzzle;

import bearmaps.hw4.AStarSolver;
import bearmaps.hw4.LazySolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Showcases how the AStarSolver can be used for solving sliding puzzles.
 * Runs several puzzles in a row.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoRunSeveralPuzzles {
    private static String[] basicPuzzles = {"BasicPuzzle1.txt", "BasicPuzzle2.txt",
        "BasicPuzzle3.txt", "BasicPuzzle4.txt"};

    private static String[] hardPuzzles = {"HardPuzzle1.txt", "HardPuzzle2.txt",
        "HardPuzzle3.txt"};

    private static String[] elitePuzzles = {"ElitePuzzle1.txt", "ElitePuzzle2.txt",
        "ElitePuzzle3.txt"};

    public static void main(String[] args) {

        String[] basicPuzzleFiles = basicPuzzles;

        System.out.println(basicPuzzleFiles.length + " puzzle files being run.");
        for (int i = 0; i < basicPuzzleFiles.length; i += 1) {
            Board start = Board.readBoard(basicPuzzleFiles[i]);
            int N = start.size();
            Board goal = Board.solved(N);

            BoardGraph spg = new BoardGraph();
            System.out.println(basicPuzzleFiles[i] + ":");
            // ShortestPathsSolver<Board> solver = new LazySolver<>(spg, start, goal, 30);
            ShortestPathsSolver<Board> solver = new AStarSolver<>(spg, start, goal, 10);
            SolutionPrinter.summarizeOutcome(solver);
        }

        String[] hardPuzzleFiles = hardPuzzles;

        System.out.println(hardPuzzleFiles.length + " puzzle files being run.");
        for (int i = 0; i < hardPuzzleFiles.length; i += 1) {
            Board start = Board.readBoard(hardPuzzleFiles[i]);
            int N = start.size();
            Board goal = Board.solved(N);

            BoardGraph spg = new BoardGraph();
            System.out.println(hardPuzzleFiles[i] + ":");
            // ShortestPathsSolver<Board> solver = new LazySolver<>(spg, start, goal, 30);
            ShortestPathsSolver<Board> solver = new AStarSolver<>(spg, start, goal, 10);
            SolutionPrinter.summarizeOutcome(solver);
        }

        String[] elitePuzzleFiles = elitePuzzles;

        System.out.println(elitePuzzleFiles.length + " puzzle files being run.");
        for (int i = 0; i < elitePuzzleFiles.length; i += 1) {
            Board start = Board.readBoard(elitePuzzleFiles[i]);
            int N = start.size();
            Board goal = Board.solved(N);

            BoardGraph spg = new BoardGraph();
            System.out.println(elitePuzzleFiles[i] + ":");
            // ShortestPathsSolver<Board> solver = new LazySolver<>(spg, start, goal, 30);
            ShortestPathsSolver<Board> solver = new AStarSolver<>(spg, start, goal, 10);
            SolutionPrinter.summarizeOutcome(solver);
        }

    }
}
