package byow.Core;

import byow.SaveDemo.Editor;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static Random seed;
    public static boolean gameover;
    public static TETile[][] world = null;
    private StringBuilder record = new StringBuilder();
    String information;
    Build map;
    Avatar avatar;

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        if (!input.matches("^[0-9a-zA-Z]+$")) {
            System.out.print("Wrong input!");
            return null;
        }

        readSeed(input);

        // identify input
        StringInput si = new StringInput(input);
        while (si.hasNextKey()) {
            char inputKey = si.getNextKey();
            if (String.valueOf(inputKey).matches("^[0-9]+$")) {
                continue;
            }
            if (inputKey == 'n') {
                continue;
            }
            takeAction(inputKey);
        }
        return world;
    }

    public void readSeed(String input) {
        String s = "";
        StringInput si = new StringInput(input);

        while (si.hasNextKey()) {
            char inputKey = si.getNextKey();
            if (String.valueOf(inputKey).matches("^[0-9]+$")) {
                s = s + inputKey;
            }
        }
        this.seed = new Random(Integer.parseInt(s));
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        gameover = false;
        drawBackground();
        drawMenu();

        while (!gameover) {

            if (!Animation.move.equals(Animation.Movement.FALSE)) {
                tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            }

            if (StdDraw.hasNextKeyTyped()) {
                char inputKey = getNextKey();
                record.append(inputKey);
                System.out.println(record);
                takeAction(inputKey);
            }
        }
    }

    public void takeAction(char inputKey) {

        if (Animation.move.equals(Animation.Movement.FALSE)) {
            if (inputKey == 'n') {
                StdDraw.text(WIDTH * 0.5, HEIGHT - 18, "Enter Seed");
                StdDraw.show();
                this.seed = inputSeed();
                StdDraw.clear(Color.BLACK);
                StdDraw.text(WIDTH * 0.5, HEIGHT - 15, "Press s to start or q to quit");
                StdDraw.show();
            }
            if (inputKey == 's') {
                start();
                Animation.setMove(Animation.Movement.NONE);
            }
            if (inputKey == 'q') {
                System.exit(0);
            }
            if (inputKey == 'l') {
                record.deleteCharAt(record.length() - 1);
                String savedString = load();
                if (savedString.equals("error")) {
                    System.out.println("Found no file");
                    System.exit(0);
                }
                System.out.println(savedString);
                interactWithInputString(savedString);
            }
        }
        if (inputKey == ':') {
            if (getNextKey() == 'q') {
                record.deleteCharAt(record.length() - 1);
                save(record.toString());
                System.exit(0);
            }
        }
        if (inputKey == 'w') {
            if (Animation.move.equals(Animation.Movement.FALSE)) {
                return;
            }
            avatar.up();
        }
        if (inputKey == 's') {
            if (Animation.move.equals(Animation.Movement.FALSE)) {
                return;
            }
            avatar.down();
        }
        if (inputKey == 'a') {
            if (Animation.move.equals(Animation.Movement.FALSE)) {
                return;
            }
            avatar.left();
        }
        if (inputKey == 'd') {
            if (Animation.move.equals(Animation.Movement.FALSE)) {
                return;
            }
            avatar.right();
        }
    }

    public void start() {
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        map = new Build();
        Engine.world = map.build(Engine.world);
        avatar = new Avatar(Engine.world);
        ter.renderFrame(world);
    }

    private Random inputSeed() {
        String s = "";
        while (true) {
            char c = getNextKey();
            if (!String.valueOf(c).matches("^[0-9]+$")) {
                break;
            }
            s = s + c;
            drawSeed(s);
        }
        record.append(s);
        return new Random(Integer.parseInt(s));
    }

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toLowerCase(StdDraw.nextKeyTyped());
                return c;
            }
        }
    }

    private void drawSeed(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 8, "Welcome to Bludo world");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 12, "New game(n)");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 15, "Quit(q)");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 18, "Enter Seed");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 20, s);
        StdDraw.show();
    }

    private void drawBackground() {
        StdDraw.setCanvasSize(WIDTH * 16,  HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.YELLOW);
    }

    private void drawMenu() {
        StdDraw.text(WIDTH * 0.5, HEIGHT - 8, "Welcome to Bludo world");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 12, "New game(n)");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 15, "Quit(q)");
        StdDraw.show();
    }

    private void tileInfo(Position mousePos) {
        if (mousePos.getY() > 29) {
            return;
        }
        String temp = world[mousePos.getX()][mousePos.getY()].description();
        if (temp.equals(information)) {
            return;
        }
        information = temp;
        ter.renderFrame(world);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, Engine.HEIGHT - 1, information);
        StdDraw.show();
    }

    private void save(String save) {
        File f = new File("/Users/kun/cs61b/cs61b-2019spring/proj3/byow/save_data");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(save);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private String load() {
        File f = new File("/Users/kun/cs61b/cs61b-2019spring/proj3/byow/save_data");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (String) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        return "error";
    }

    private class StringInput {
        String input;
        int index;

        public StringInput(String s) {
            input = s.toLowerCase();
            index = 0;
        }

        private char getNextKey() {
            char temp = input.charAt(index);
            index += 1;
            return temp;
        }

        private boolean hasNextKey() {
            return index < input.length();
        }

        private void refresh() {
            index = 0;
        }
    }
}
