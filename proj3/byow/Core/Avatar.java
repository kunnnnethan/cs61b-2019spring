package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class Avatar {
    TERenderer ter = new TERenderer();
    static int currX;
    static int currY;
    TETile tempTileset;
    TETile[][] world;

    public Avatar(TETile[][] world) {
        currX = Build.positions.get(0).getX();
        currY = Build.positions.get(0).getY();
        this.world = world;
        init();
    }

    public void init() {
        tempTileset = world[currX][currY];
        currentState();
        ter.renderFrame(world);
        tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
    }

    public void currentState() {
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

    public void moveBefore() {
        if (Animation.move.equals(Animation.Movement.UP)) {
            world[currX][currY] = Animation.AVATAR_U1;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_U2;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
        if (Animation.move.equals(Animation.Movement.DOWN)) {
            world[currX][currY] = Animation.AVATAR_D1;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_D2;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
        if (Animation.move.equals(Animation.Movement.LEFT)) {
            world[currX][currY] = Animation.AVATAR_L1;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_L2;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
        if (Animation.move.equals(Animation.Movement.RIGHT)) {
            world[currX][currY] = Animation.AVATAR_R1;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_R2;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
    }

    public void moveAfter() {
        if (Animation.move.equals(Animation.Movement.UP)) {
            world[currX][currY] = Animation.AVATAR_U3;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_U4;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
        if (Animation.move.equals(Animation.Movement.DOWN)) {
            world[currX][currY] = Animation.AVATAR_D3;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_D4;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
        if (Animation.move.equals(Animation.Movement.LEFT)) {
            world[currX][currY] = Animation.AVATAR_L3;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_L4;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
        if (Animation.move.equals(Animation.Movement.RIGHT)) {
            world[currX][currY] = Animation.AVATAR_R3;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            StdDraw.pause(10);
            world[currX][currY] = Animation.AVATAR_R4;
            ter.renderFrame(world);
            tileInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
        }
    }

    public void up() {
        Animation.setMove(Animation.Movement.UP);
        moveBefore();
        world[currX][currY] = tempTileset;
        if (isWall(currX, currY + 1)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        currY = currY + 1;
        tempTileset = world[currX][currY];
        moveAfter();
        world[currX][currY] = tempTileset;
        init();
    }

    public void down() {
        Animation.setMove(Animation.Movement.DOWN);
        moveBefore();
        world[currX][currY] = tempTileset;
        if (isWall(currX, currY - 1)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        currY = currY - 1;
        tempTileset = world[currX][currY];
        moveAfter();
        world[currX][currY] = tempTileset;
        init();
    }

    public void left() {
        Animation.setMove(Animation.Movement.LEFT);
        moveBefore();
        world[currX][currY] = tempTileset;
        if (isWall(currX - 1, currY)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        currX = currX - 1;
        tempTileset = world[currX][currY];
        moveAfter();
        world[currX][currY] = tempTileset;
        init();
    }

    public void right() {
        Animation.setMove(Animation.Movement.RIGHT);
        moveBefore();
        world[currX][currY] = tempTileset;
        if (isWall(currX + 1, currY)) {
            init();
            Animation.setMove(Animation.Movement.NONE);
            return;
        }
        currX = currX + 1;
        tempTileset = world[currX][currY];
        moveAfter();
        world[currX][currY] = tempTileset;
        init();
    }

    public boolean isWall(int x, int y) {
        return world[x][y] == Tileset.WALL;
    }

    private void tileInfo(Position mousePos) {
        if (mousePos.getY() > 29) {
            return;
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, Engine.HEIGHT - 1, world[mousePos.getX()][mousePos.getY()].description());
        StdDraw.show();
    }
}
