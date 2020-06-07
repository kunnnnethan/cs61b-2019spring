package byow.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/avatar.png");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/wall.png");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/floor.png");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/grass.png");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black, "locked door", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/lockeddoor.png");
    public static final TETile LIGHT = new TETile('♠', Color.green, Color.black, "light source", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/light.png");
    public static final TETile MONSTER = new TETile('♠', Color.green, Color.black, "monster", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/monster.png");

}


