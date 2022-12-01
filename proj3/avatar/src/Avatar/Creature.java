package Avatar;

import TileEngine.TETile;
import TileEngine.Tileset;

public class Creature {

    TETile[][] world;

    public Creature(TETile[][] world) {
        this.world = world;
    }

    public boolean isWall(int x, int y) {
        return world[x][y] == Tileset.WALL;
    }

    public boolean isLight(int x, int y) {
        return world[x][y] == Tileset.LIGHT;
    }

    public boolean isDoor(int x, int y) {
        return world[x][y] == Tileset.LOCKED_DOOR;
    }
}
