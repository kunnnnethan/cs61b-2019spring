package TileEngine;
import java.awt.*;

public class Animation {
    public static Movement move = Movement.FALSE;
    public static final TETile AVATAR_U0 = new TETile('@', Color.white, Color.black, "you", "Image/up1.png");
    public static final TETile AVATAR_U1 = new TETile('@', Color.white, Color.black, "you", "Image/up2.png");
    public static final TETile AVATAR_U2 = new TETile('@', Color.white, Color.black, "you", "Image/up3.png");
    public static final TETile AVATAR_D0 = new TETile('@', Color.white, Color.black, "you", "Image/down1.png");
    public static final TETile AVATAR_D1 = new TETile('@', Color.white, Color.black, "you", "Image/down2.png");
    public static final TETile AVATAR_D2 = new TETile('@', Color.white, Color.black, "you", "Image/down3.png");
    public static final TETile AVATAR_L0 = new TETile('@', Color.white, Color.black, "you", "Image/left1.png");
    public static final TETile AVATAR_L1 = new TETile('@', Color.white, Color.black, "you", "Image/left2.png");
    public static final TETile AVATAR_L2 = new TETile('@', Color.white, Color.black, "you", "Image/left3.png");
    public static final TETile AVATAR_R0 = new TETile('@', Color.white, Color.black, "you", "Image/right1.png");
    public static final TETile AVATAR_R1 = new TETile('@', Color.white, Color.black, "you", "Image/right2.png");
    public static final TETile AVATAR_R2 = new TETile('@', Color.white, Color.black, "you", "Image/right3.png");

    public enum Movement {
        FALSE,
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN,
    }

    public static void setMove(Movement m) {
        move = m;
    }
}
