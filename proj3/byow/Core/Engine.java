package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.*;
import java.util.logging.Level;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static Random seed;
    public static LinkedList<Position> positions; // ordered list of random points

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

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

        TETile[][] initialWorldFrame = null;
        String seed = "";

        // identify input
        StringInput si = new StringInput(input);
        while (si.hasNextKey()) {
            char c = si.getNextKey();
            if (c == 'n') {
                ter.initialize(WIDTH, HEIGHT);
            } else if (c == 's') {
                initialWorldFrame = new TETile[WIDTH][HEIGHT];
            } else {
                seed = seed + c;
            }
        }

        this.seed = new Random(Integer.parseInt(seed));
        int numberOfPoint = 15 + this.seed.nextInt(25);

        // build Nothing in background
        buildBackGround(initialWorldFrame);

        // build random point
        buildRandomPoint(numberOfPoint, initialWorldFrame);

        // build vertical hall
        buildVertical(initialWorldFrame);

        // build horizontal hall
        buildHorizontal(initialWorldFrame);

        // build random room
        buildRoom(numberOfPoint, initialWorldFrame);

        //build long vertical line
        buildLongHorizontal(initialWorldFrame);
        buildLongHorizontal(initialWorldFrame);

        //build wall
        buildWall(initialWorldFrame);

        // build grass
        buildGrass(initialWorldFrame);

        // build locked door
        buildLockedDoor(initialWorldFrame);

        // result
        //point(initialWorldFrame);
        TETile[][] finalWorldFrame = initialWorldFrame;
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    public void buildBackGround(TETile[][] world) {

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void buildRandomPoint(int numberOfPoint, TETile[][] world) {
        positions = new LinkedList<>();
        PriorityQueue<Position> tempPositions = new PriorityQueue<>();

        for (int i = 0; i < numberOfPoint; i += 1) {
            int locationX = 2 + seed.nextInt((int) Math.round(WIDTH * 0.9));
            int locationY = 2 + seed.nextInt((int) Math.round(HEIGHT * 0.9));
            if (tempPositions.contains(new Position(locationX, locationY))) {
                i -= 1;
                continue;
            }
            tempPositions.add(new Position(locationX, locationY));
        }

        for (int i = 0; i < numberOfPoint; i += 1) {
            positions.addLast(tempPositions.poll());
        }
    }

    public void buildVertical(TETile[][] world) {

        for (int i = 0; i < positions.size() - 1; i += 1) {
            int x = positions.get(i).getX();
            int currY = positions.get(i).getY();
            int nextY = positions.get(i + 1).getY();

            if (nextY < currY) {
                int temp = currY;
                currY = nextY;
                nextY = temp;
            }
            for (int j = currY; j < nextY + 1; j += 1) {
                if (world[x][j] == Tileset.AVATAR) {
                    continue;
                }
                world[x][j] = Tileset.FLOOR;
            }
        }
    }

    public void buildHorizontal(TETile[][] world) {

        for (int i = 0; i < positions.size() - 1; i += 1) {
            int y = positions.get(i + 1).getY();
            int currX = positions.get(i).getX();
            int nextX = positions.get(i + 1).getX();

            if (nextX < currX) {
                int temp = currX;
                currX = nextX;
                nextX = temp;
            }
            for (int j = currX; j < nextX + 1; j += 1) {
                if (world[j][y] == Tileset.AVATAR) {
                    continue;
                }
                world[j][y] = Tileset.FLOOR;
            }
        }
    }

    public void buildRoom(int numberOfPoint, TETile[][] world) {
        for (int i = 0; i < numberOfPoint - seed.nextInt(3); i += 1) {
            Position p = positions.get(seed.nextInt(numberOfPoint));
            int width = seed.nextInt(5);
            int height = seed.nextInt(5);
            for (int x = -width; x < width; x += 1) {
                for (int y = -height; y < height; y += 1) {
                    int X = x + p.getX();
                    int Y = y + p.getY();
                    if (X < 1) {
                        X = 1;
                    }
                    if (X > WIDTH - 2) {
                        X = WIDTH - 2;
                    }
                    if (Y < 1) {
                        Y = 1;
                    }
                    if (Y > HEIGHT - 2) {
                        Y = HEIGHT - 2;
                    }

                    world[X][Y] = Tileset.FLOOR;
                }
             }
        }
    }

    public void buildLongHorizontal(TETile[][] world) {
        switch (seed.nextInt(3)){
            case 0 :
                int LEFT = (int) Math.round(seed.nextInt(WIDTH) * 0.8) + 1;
                int RIGHT = (int) Math.round(seed.nextInt(WIDTH) * 0.8) + 5;
                if (LEFT > RIGHT) {
                    int temp = LEFT;
                    LEFT = RIGHT;
                    RIGHT = temp;
                }
                if (LEFT < 1) {
                    LEFT = 1;
                }
                if (RIGHT > WIDTH - 2) {
                    RIGHT = WIDTH - 2;
                }
                int y = (int) Math.round(seed.nextInt(HEIGHT) * 0.8) + 1;
                for (int n = LEFT; n < RIGHT; n += 1) {
                    world[n][y] = Tileset.FLOOR;
                    world[n][y + 1] = Tileset.FLOOR;
                    world[n][y + 2] = Tileset.FLOOR;
                }
            case 1 :
                LEFT = (int) Math.round(seed.nextInt(WIDTH) * 0.8) + 1;
                RIGHT = (int) Math.round(seed.nextInt(WIDTH) * 0.8) + 5;
                if (LEFT > RIGHT) {
                    int temp = LEFT;
                    LEFT = RIGHT;
                    RIGHT = temp;
                }
                if (LEFT < 1) {
                    LEFT = 1;
                }
                if (RIGHT > WIDTH - 2) {
                    RIGHT = WIDTH - 2;
                }
                y = (int) Math.round(seed.nextInt(HEIGHT) * 0.8) + 1;
                for (int n = LEFT; n < RIGHT; n += 1) {
                    world[n][y] = Tileset.FLOOR;
                    world[n][y + 1] = Tileset.FLOOR;
                }
            case 2 :
                LEFT = (int) Math.round(seed.nextInt(WIDTH) * 0.8) + 1;
                RIGHT = (int) Math.round(seed.nextInt(WIDTH) * 0.8) + 5;
                if (LEFT > RIGHT) {
                    int temp = LEFT;
                    LEFT = RIGHT;
                    RIGHT = temp;
                }
                if (LEFT < 1) {
                    LEFT = 1;
                }
                if (RIGHT > WIDTH - 2) {
                    RIGHT = WIDTH - 2;
                }
                y = (int) Math.round(seed.nextInt(HEIGHT) * 0.8) + 1;
                for (int n = LEFT; n < RIGHT; n += 1) {
                    world[n][y] = Tileset.FLOOR;
                }
        }
    }

    public void buildWall(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (world[x][y] == Tileset.FLOOR) {
                    continue;
                }
                int UP = y + 1;
                int DOWN = y - 1;
                int LEFT = x - 1;
                int RIGHT = x + 1;
                if (UP > HEIGHT - 1) {
                    UP = HEIGHT - 1;
                }
                if (DOWN < 0) {
                    DOWN = 0;
                }
                if (LEFT < 0) {
                    LEFT = 0;
                }
                if (RIGHT > WIDTH - 1) {
                    RIGHT = WIDTH - 1;
                }
                if (world[x][UP] == Tileset.FLOOR || world[x][DOWN] == Tileset.FLOOR
                        || world[LEFT][y] == Tileset.FLOOR || world[RIGHT][y] == Tileset.FLOOR
                        || world[LEFT][UP] == Tileset.FLOOR || world[RIGHT][UP] == Tileset.FLOOR
                        || world[LEFT][DOWN] == Tileset.FLOOR || world[RIGHT][DOWN] == Tileset.FLOOR) {
                    world[x][y] = Tileset.WALL;
                }

                // avoid walls are isolated in the middle
                if ((world[x][UP] == Tileset.FLOOR && world[x][DOWN] == Tileset.FLOOR)
                        || (world[LEFT][y] == Tileset.FLOOR && world[RIGHT][y] == Tileset.FLOOR)) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }
    }

    public void buildGrass(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (world[x][y] == Tileset.FLOOR) {
                    switch (seed.nextInt(4)){
                        case 0 :
                            world[x][y] = Tileset.GRASS;
                        default:
                            continue;
                    }
                }
            }
        }
    }

    public void buildLockedDoor(TETile[][] world) {

        ArrayList<Position> p = new ArrayList<>();
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (world[x][y] == Tileset.WALL) {
                    int UP = y + 1;
                    int DOWN = y - 1;
                    int LEFT = x - 1;
                    int RIGHT = x + 1;
                    if (UP > HEIGHT - 1) {
                        UP = HEIGHT - 1;
                    }
                    if (DOWN < 0) {
                        DOWN = 0;
                    }
                    if (LEFT < 0) {
                        LEFT = 0;
                    }
                    if (RIGHT > WIDTH - 1) {
                        RIGHT = WIDTH - 1;
                    }
                    if ((world[LEFT][y] == Tileset.WALL && world[x][UP] == Tileset.WALL) ||
                            (world[RIGHT][y] == Tileset.WALL && world[x][UP] == Tileset.WALL) ||
                            (world[RIGHT][y] == Tileset.WALL && world[x][DOWN] == Tileset.WALL) ||
                            (world[LEFT][y] == Tileset.WALL && world[x][DOWN] == Tileset.WALL)) {
                        continue;
                    }
                    p.add(new Position(x, y));
                }
            }
        }
        int random = seed.nextInt(p.size());
        world[p.get(random).getX()][p.get(random).getY()] = Tileset.LOCKED_DOOR;
    }



    // ---------------stupid helper----------------
    public void point(TETile[][] world) {
        for (Position p : positions) {
            world[p.getX()][p.getY()] = Tileset.AVATAR;
        }
    }

    // --------------input string helper--------------
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
