package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.util.List;

import static byow.Core.Avatar.currX;
import static byow.Core.Avatar.currY;

public class Monster extends Creature {

    int monsX;
    int monsY;
    TETile tempTileset;
    Graph floor;

    public Monster(TETile[][] world) {
        super(world);
        monsX = Build.positions.get(Build.positions.size() - 1).getX();
        monsY = Build.positions.get(Build.positions.size() - 1).getY();
        buildGraph();
        init();
    }

    public void buildGraph() {
        floor = new Graph();
        for (int x = 0; x < Engine.WIDTH; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                if (world[x][y] == Tileset.FLOOR || world[x][y] == Tileset.GRASS
                        || world[x][y] == Tileset.LIGHT || world[x][y] == Tileset.LOCKED_DOOR) {
                    floor.add(new Point(x, y));
                }
            }
        }
    }

    public void hunt() {
        List<Point> shortestPath = new AStarSolver(floor, new Point(monsX, monsY), new Point(currX, currY), 100).solution();
        Point nearest = new Point((int) shortestPath.get(1).getX(), (int) shortestPath.get(1).getY());

            Point up = new Point(monsX, monsY + 1);
            Point down = new Point(monsX, monsY - 1);
            Point left = new Point(monsX - 1, monsY);
            Point right = new Point(monsX + 1, monsY);

            if (nearest.equals(up)) {
                System.out.println("up");
                up();
            }
            if (nearest.equals(down)) {
                System.out.println("down");
                down();
            }
            if (nearest.equals(left)) {
                System.out.println("left");
                left();
            }
            if (nearest.equals(right)) {
                System.out.println("right");
                right();
            }
    }

    public void init() {
        tempTileset = world[monsX][monsY];
        if (tempTileset.equals(Animation.AVATAR_U0) || tempTileset.equals(Animation.AVATAR_D0)
        || tempTileset.equals(Animation.AVATAR_L0) || tempTileset.equals(Animation.AVATAR_R0)) {
            Engine.gameover = true;
            return;
        }
        world[monsX][monsY] = Tileset.MONSTER;
    }

    public void up() {
        world[monsX][monsY] = tempTileset;
        if (isWall(monsX, monsY + 1)) {
            return;
        }
        monsY = monsY + 1;
        init();
    }

    public void down() {
        world[monsX][monsY] = tempTileset;
        if (isWall(monsX, monsY - 1)) {
            return;
        }
        monsY = monsY - 1;
        init();
    }

    public void left() {
        world[monsX][monsY] = tempTileset;
        if (isWall(monsX - 1, monsY)) {
            return;
        }
        monsX = monsX - 1;
        init();
    }

    public void right() {
        world[monsX][monsY] = tempTileset;
        if (isWall(monsX + 1, monsY)) {
            return;
        }
        monsX = monsX + 1;
        init();
    }
}
