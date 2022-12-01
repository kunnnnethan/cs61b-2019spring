package Avatar;

import TileEngine.TETile;
import TileEngine.Tileset;
import TileEngine.Animation;

public class Avatar extends Creature{
    static int currX;
    static int currY;
    TETile tempTileset;

    public Avatar(TETile[][] world) {
        super(world);
        currX = Build.positions.get(0).getX();
        currY = Build.positions.get(0).getY();
        init();
    }

    public void init() {
        tempTileset = world[currX][currY];
        currentState();
    }

    public void up() {
        world[currX][currY] = tempTileset;
        if (isWall(currX, currY + 1)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        if (isDoor(currX, currY + 1)) {
            Engine.win = true;
            Engine.gameover = true;
            return;
        }
        currY = currY + 1;
        if (isLight(currX, currY)) {
            Engine.dark = false;
        }
        init();
    }

    public void down() {
        world[currX][currY] = tempTileset;
        if (isWall(currX, currY - 1)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        if (isDoor(currX, currY - 1)) {
            Engine.win = true;
            Engine.gameover = true;
            return;
        }
        currY = currY - 1;
        if (isLight(currX, currY)) {
            Engine.dark = false;
        }
        init();
    }

    public void left() {
        world[currX][currY] = tempTileset;
        if (isWall(currX - 1, currY)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        if (isDoor(currX - 1, currY)) {
            Engine.win = true;
            Engine.gameover = true;
            return;
        }
        currX = currX - 1;
        if (isLight(currX, currY)) {
            Engine.dark = false;
        }
        init();
    }

    public void right() {
        world[currX][currY] = tempTileset;
        if (isWall(currX + 1, currY)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        if (isDoor(currX + 1, currY)) {
            Engine.win = true;
            Engine.gameover = true;
            return;
        }
        currX = currX + 1;
        if (isLight(currX, currY)) {
            Engine.dark = false;
        }
        init();
    }

    //-------------helper-------------
    private void currentState() {
        if (Animation.move.equals(Animation.Movement.NONE)) {
            world[currX][currY] = Tileset.AVATAR;
        }
        if (Animation.move.equals(Animation.Movement.UP)) {
            world[currX][currY] = Animation.AVATAR_U0;
        }
        if (Animation.move.equals(Animation.Movement.DOWN)) {
            world[currX][currY] = Animation.AVATAR_D0;
        }
        if (Animation.move.equals(Animation.Movement.LEFT)) {
            world[currX][currY] = Animation.AVATAR_L0;
        }
        if (Animation.move.equals(Animation.Movement.RIGHT)) {
            world[currX][currY] = Animation.AVATAR_R0;
        }
    }
}
