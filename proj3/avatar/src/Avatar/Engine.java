package Avatar;

import TileEngine.TERenderer;
import TileEngine.TETile;
import TileEngine.Animation;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.*;

import static Avatar.Avatar.currX;
import static Avatar.Avatar.currY;

public class Engine {
    TERenderer ter = new TERenderer();

    /* Feel free to change the width and height. */
    public static TETile[][] world = null;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static Random seed;
    public static boolean gameover = false;
    public static boolean dark = true;
    public static boolean win = false;
    private StringBuilder record = new StringBuilder();
    String information;
    Build map;
    Avatar avatar;
    Monster monster;

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
            System.out.println(inputKey);
            takeAction(inputKey);
        }
        record.append(input);
        return world;
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public boolean interactWithKeyboard() {
        drawBackground();
        drawMenu();

        while (!gameover) {

            if (!Animation.move.equals(Animation.Movement.FALSE)) {
                tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            }

            if (StdDraw.hasNextKeyTyped()) {
                char inputKey = getNextKey();
                record.append(inputKey);
                takeAction(inputKey);
                render();
            }
        }

        if (win) {
            StdDraw.clear(Color.BLACK);
            StdDraw.setPenColor(Color.YELLOW);
            Font font = new Font("Monaco", Font.BOLD, 40);
            StdDraw.setFont(font);
            StdDraw.text(WIDTH * 0.5, HEIGHT - 12, "You win");
            StdDraw.text(WIDTH * 0.5, HEIGHT - 18, "Press 'r' to return to menu or 'q' to quit");
            StdDraw.show();

            while (true) {
                if (getNextKey() == 'r') {
                    Animation.setMove(Animation.Movement.FALSE);
                    init();
                    return true;
                } else if (getNextKey() == 'q') {
                    return false;
                }
            }
        } else {
            StdDraw.clear(Color.BLACK);
            StdDraw.setPenColor(Color.RED);
            Font font = new Font("Monaco", Font.BOLD, 40);
            StdDraw.setFont(font);
            StdDraw.text(WIDTH * 0.5, HEIGHT - 12, "You lose");
            StdDraw.text(WIDTH * 0.5, HEIGHT - 18, "Press 'r' to return to menu or 'q' to quit");
            StdDraw.show();

            while (true) {
                if (getNextKey() == 'r') {
                    Animation.setMove(Animation.Movement.FALSE);
                    init();
                    return true;
                } else if (getNextKey() == 'q') {
                    return false;
                }
            }
        }
    }

    public void takeAction(char inputKey) {
        if (Animation.move.equals(Animation.Movement.FALSE)) {
            if (inputKey != 'n' && inputKey != 'q' && inputKey != 'l' && inputKey != 's') {
                takeAction(getNextKey());
            }
            if (inputKey == 'n') {
                StdDraw.text(WIDTH * 0.5, HEIGHT - 22, "Enter Seed");
                StdDraw.show();

                String s = "";
                while (true) {
                    char c = getNextKey();
                    if (c == 's') {
                        inputKey = c;
                        break;
                    }
                    if (!String.valueOf(c).matches("^[0-9]+$")) {
                        continue;
                    }
                    s = s + c;
                    drawSeed(s);
                }
                record.append(s);
                if (s.length() > 0) {
                    this.seed = new Random(Long.parseLong(s));
                }
            }
            if (inputKey == 's') {
                Animation.setMove(Animation.Movement.NONE);
                record.append(inputKey);
                start();
                return;
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
            return;
        }
        if (inputKey == ':') {
            if (getNextKey() == 'q') {
                record.deleteCharAt(record.length() - 1);
                save(record.toString());
                System.exit(0);
            }
        }
        if (inputKey == 'w') {
            Animation.setMove(Animation.Movement.UP);
            moveBefore();
            avatar.up();
            monster.hunt();
        }
        if (inputKey == 's') {
            Animation.setMove(Animation.Movement.DOWN);
            moveBefore();
            avatar.down();
            monster.hunt();
        }
        if (inputKey == 'a') {
            Animation.setMove(Animation.Movement.LEFT);
            moveBefore();
            avatar.left();
            monster.hunt();
        }
        if (inputKey == 'd') {
            Animation.setMove(Animation.Movement.RIGHT);
            moveBefore();
            avatar.right();
            monster.hunt();
        }
        if (inputKey == 'h') {
            monster.hunt();
            render();
        }
    }

    public void start() {
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        map = new Build();
        Engine.world = map.build(Engine.world);
        monster = new Monster(Engine.world);
        avatar = new Avatar(Engine.world);
        System.out.println(TETile.toString(world));
    }

    public void render() {
        if (Engine.dark) {
            ter.renderDarkFrame(world, currX, currY);
        } else {
            ter.renderFrame(world);
        }
    }

    public void init() {
        world = null;
        seed = null;
        gameover = false;
        dark = true;
        win = false;
        record = null;
    }

    //--------------helper---------------

    private void moveBefore() {
        if (Animation.move.equals(Animation.Movement.UP)) {
            world[currX][currY] = Animation.AVATAR_U1;
            render();
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_U2;
            render();
        }
        if (Animation.move.equals(Animation.Movement.DOWN)) {
            world[currX][currY] = Animation.AVATAR_D1;
            render();
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_D2;
            render();
        }
        if (Animation.move.equals(Animation.Movement.LEFT)) {
            world[currX][currY] = Animation.AVATAR_L1;
            render();
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_L2;
            render();
        }
        if (Animation.move.equals(Animation.Movement.RIGHT)) {
            world[currX][currY] = Animation.AVATAR_R1;
            render();
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_R2;
            render();
        }
    }

    private void readSeed(String input) {
        String s = "";
        StringInput si = new StringInput(input);

        while (si.hasNextKey()) {
            char inputKey = si.getNextKey();
            if (String.valueOf(inputKey).matches("^[0-9]+$")) {
                s = s + inputKey;
            }
        }
        this.seed = new Random(Long.parseLong(s));
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
        if (dark) {
            ter.renderDarkFrame(world, currX, currY);
        } else {
            ter.renderFrame(world);
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, Engine.HEIGHT - 1, information);
        StdDraw.show();
    }

    private void drawBackground() {
        StdDraw.setCanvasSize(WIDTH * 16,  HEIGHT * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.YELLOW);
    }

    private void drawMenu() {
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 9, "Welcome");
        StdDraw.show();
        font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 15, "New game(n)");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 17, "Load game(l)");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 19, "Quit(q)");
        font = new Font("Monaco", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 24, "(during the game, you can always press ':' and then 'q' to save and quit)");
        StdDraw.show();
    }

    private void drawSeed(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 9, "Press s to start or q to quit");
        StdDraw.show();
        font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 22, "Enter Seed");
        StdDraw.text(WIDTH * 0.5, HEIGHT - 24, s);
        StdDraw.show();
    }

    private void save(String save) {
        File f = new File("./save.txt");
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
        File f = new File("save.txt");
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

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toLowerCase(StdDraw.nextKeyTyped());
                return c;
            }
        }
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
    }
}
