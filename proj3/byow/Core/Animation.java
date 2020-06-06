package byow.Core;

import byow.TileEngine.TETile;

import java.awt.*;

public class Animation {
    static Movement move = Movement.FALSE;
    public static final TETile AVATAR_U0 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/up1.png");
    public static final TETile AVATAR_U1 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/up2.png");
    public static final TETile AVATAR_U2 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/up3.png");
    public static final TETile AVATAR_U3 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/up4.png");
    public static final TETile AVATAR_U4 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/up5.png");
    public static final TETile AVATAR_D0 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/down1.png");
    public static final TETile AVATAR_D1 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/down2.png");
    public static final TETile AVATAR_D2 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/down3.png");
    public static final TETile AVATAR_D3 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/down4.png");
    public static final TETile AVATAR_D4 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/down5.png");
    public static final TETile AVATAR_L0 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/left1.png");
    public static final TETile AVATAR_L1 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/left2.png");
    public static final TETile AVATAR_L2 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/left3.png");
    public static final TETile AVATAR_L3 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/left4.png");
    public static final TETile AVATAR_L4 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/left5.png");
    public static final TETile AVATAR_R0 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/right1.png");
    public static final TETile AVATAR_R1 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/right2.png");
    public static final TETile AVATAR_R2 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/right3.png");
    public static final TETile AVATAR_R3 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/right4.png");
    public static final TETile AVATAR_R4 = new TETile('@', Color.white, Color.black, "you", "/Users/kun/cs61b/cs61b-2019spring/proj3/byow/lab12/right5.png");

    enum Movement {
        FALSE,
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN;
    }

    public static void setMove(Movement m) {
        move = m;
    }
}
