package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

/**
 *  Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        buildTop(world, p, s, t);
        buildMiddle(world, p, s, t);
        buildBottom(world, p, s, t);
    }

    /** useless method
    private static void triangle(TETile[][] world, int x, int y, Position p, int s) {
        for (int i = x; i < s - 1; i += 1) {
            for (int j = s - 2 - i; j >= 0; j -= 1) {
                world[i + p.getX()][j + p.getY()] = Tileset.NOTHING;
            }
        }

        for (int i = 4 + (s - 2) * 3 - 1; i > 4 + (s - 2) * 3 - s; i -= 1) {
            for (int j = i - (4 + (s - 2) * 3) + s - 1; j >= 0; j -= 1) {
                world[i + p.getX()][j + p.getY()] = Tileset.NOTHING;
            }
        }

        for (int i = x; i < s - 1; i += 1) {
            for (int j = s * 2 - 1; j > s + i; j -= 1) {
                world[i + p.getX()][j + p.getY()] = Tileset.NOTHING;
            }
        }

        for (int i = 4 + (s - 2) * 3 - 1; i > 4 + (s - 2) * 3 - s; i -= 1) {
            for (int j = s * 2 - 1; j > s + (4 + (s - 2) * 3 - 1) - i; j -= 1) {
                world[i + p.getX()][j + p.getY()] = Tileset.NOTHING;
            }
        }
    }
     */

    private static void buildTop(TETile[][] world, Position p, int s, TETile t) {
        for (int y = s + 1; y < s * 2; y += 1) {
            for (int x = y - s; x < 4 + (s - 2) * 3 - y + s; x += 1) {
                world[x + p.getX()][y + p.getY()] = t;
            }
        }
    }

    private static void buildMiddle(TETile[][] world, Position p, int s, TETile t) {
        for (int y = s - 1; y <= s; y += 1) {
            for (int x = 0; x < 4 + (s - 2) * 3; x += 1) {
                world[x + p.getX()][y + p.getY()] = t;
            }
        }
    }

    private static void buildBottom(TETile[][] world, Position p, int s, TETile t) {
        for (int y = 0; y < s - 1; y += 1) {
            for (int x = s - 1 - y; x < 4 + (s - 2) * 3 + y - s + 1; x += 1) {
                world[x + p.getX()][y + p.getY()] = t;
            }
        }
    }

    public static void buildTesselation(TETile[][] world, Position p, int s, TETile t, int n) {
        for (int j = n, i = 0; j < n + 3; j += 1) {
            Position q = new Position(p.getX(), p.getY());
            q.setX(q.getX() + (2 * s - 1) * i);
            q.setY(q.getY() - s * i);
            buildVertical(world, q, s, t, j);
            i += 1;
        }

        for (int j = n + 1, i = 0; j > n - 1; j -= 1) {
            Position q = new Position(p.getX(), p.getY());
            q.setX(q.getX() + (2 * s - 1) * (i + 3));
            q.setY(q.getY() - s * (1 - i));
            buildVertical(world, q, s, t, j);
            i += 1;
        }
    }

    private static void buildVertical(TETile[][] world, Position p, int s, TETile t, int n) {
        for (int j = 0; j < n; j += 1) {
            p.setY(p.getY() + 2 * s);
            addHexagon(world, p, s, t);
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        for (int x = 20; x < 40; x += 1) {
            for (int y = 10; y < 20; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }


        Position position = new Position(41, 10);
        //addHexagon(world, position, 3, Tileset.AVATAR);
        buildTesselation(world, position, 4, Tileset.AVATAR, 2);

        // draws the world to the screen
        ter.renderFrame(world);
    }


}
