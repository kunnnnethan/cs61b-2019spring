package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private int level;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!", "We have to get you laid!",
                                                    "Stop masturbating everyday", "Your brain are totally full of shit"};

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(60, 30, seed);
        game.menu();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {

        //TODO: Generate random string of letters of length n
        String randomString = "";
        for (int i = 0; i < n; i += 1) {
            int r = rand.nextInt(26);
            randomString = randomString + CHARACTERS[r];
        }

        return randomString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen

        StdDraw.clear(Color.BLACK);
        Font font  =  new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);

        if (!gameOver) {
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(width * 0.5, height - 5, playerTurn ? "Type!" : "Ready?");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 2, width, height - 2);
        }

        StdDraw.text(width * 0.5, height * 0.5, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters

        for (int i = 0; i < letters.length(); i += 1) {
            char c = letters.charAt(i);

            // Each character should be visible on the screen for 1 second
            drawFrame(String.valueOf(c));
            StdDraw.pause(level);

            // a brief 0.5 second break between characters where the screen is blank
            drawFrame("");
            StdDraw.pause(500);
        }

    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String s = "";
        int i = 0;

        while (i < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                s = s + c;
                i = i + 1;
                drawFrame(s);
            }
        }

        StdDraw.pause(500);
        return s;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        level = 1000;
        gameOver = false;

        //TODO: Establish Engine loop
        while (true) {
            playerTurn = false;
            drawFrame("Round : " + round);
            StdDraw.pause(2000);
            String question = generateRandomString(round);

            playerTurn = true;
            flashSequence(question);
            String answer = solicitNCharsInput(round);

            if (!answer.equals(question)) {
                gameOver = true;
                break;
            }
            round = round + 1;
            level = level - 100;
        }
        drawFrame("Game Over!");
        StdDraw.pause(1000);
        drawFrame("You made it to round : " + round);
        StdDraw.pause(1000);
        drawFrame("You dickhead");
    }

    public void menu() {
        int n = 0;
        gameOver = true;

        while (n < 1) {
            StdDraw.clear(Color.BLACK);

            Font font  =  new Font("Monaco", Font.BOLD, 30);
            StdDraw.setFont(font);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(width * 0.5, height - 8, "Welcome to Bludo world");
            StdDraw.text(width * 0.5, height - 15, "Start(s)");
            StdDraw.text(width * 0.5, height - 18, "Quit(q)");
            StdDraw.show();

            if (StdDraw.hasNextKeyTyped()) {
                String input = String.valueOf(StdDraw.nextKeyTyped());
                if (input.equals("s")) {
                    startGame();
                    n += 1;
                } else if (input.equals("q")) {
                    drawFrame("Running away like a pig?");
                    StdDraw.pause(1000);
                    //System.exit(0);
                } else {
                    drawFrame("Do u even have a brain?");
                    StdDraw.pause(1000);
                }
            }
        }
    }
}
