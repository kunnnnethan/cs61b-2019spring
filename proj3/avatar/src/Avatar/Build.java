package Avatar;

import TileEngine.TETile;
import TileEngine.Tileset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Build {
    static LinkedList<Position> positions; // ordered list of random points
    int numberOfPoint;

    public Build() {
        numberOfPoint = 15 + Engine.seed.nextInt(25);
    }

    public TETile[][] build(TETile[][] initialWorldFrame) {
        // build Nothing in background
        buildBackGround(initialWorldFrame);

        // build random point
        buildRandomPoint();

        // build vertical hall
        buildVertical(initialWorldFrame);

        // build horizontal hall
        buildHorizontal(initialWorldFrame);

        // build random room
        buildRoom(initialWorldFrame);

        //build long vertical line
        //buildLongHorizontal(initialWorldFrame);
        //buildLongHorizontal(initialWorldFrame);

        //build wall
        buildWall(initialWorldFrame);

        // build grass
        buildGrass(initialWorldFrame);

        // build locked door
        buildLockedDoor(initialWorldFrame);

        // build light source
        buildLightSource(initialWorldFrame);

                // clean weird walls
                cleanTrash(initialWorldFrame);

        //point(initialWorldFrame);
        return initialWorldFrame;
    }


    public void buildBackGround(TETile[][] world) {
        for (int x = 0; x < Engine.WIDTH; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void buildRandomPoint() {
        positions = new LinkedList<>();
        PriorityQueue<Position> tempPositions = new PriorityQueue<>();

        for (int i = 0; i < numberOfPoint; i += 1) {
            int locationX = 2 + Engine.seed.nextInt((int) Math.round(Engine.WIDTH * 0.9));
            int locationY = 2 + Engine.seed.nextInt((int) Math.round(Engine.HEIGHT * 0.9));
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

    public void buildRoom(TETile[][] world) {
        for (int i = 0; i < numberOfPoint - Engine.seed.nextInt(3); i += 1) {
            Position p = positions.get(Engine.seed.nextInt(numberOfPoint));
            int width = Engine.seed.nextInt(5);
            int height = Engine.seed.nextInt(5);
            for (int x = -width; x < width; x += 1) {
                for (int y = -height; y < height; y += 1) {
                    int X = x + p.getX();
                    int Y = y + p.getY();
                    if (X < 1) {
                        X = 1;
                    }
                    if (X > Engine.WIDTH - 2) {
                        X = Engine.WIDTH - 2;
                    }
                    if (Y < 1) {
                        Y = 1;
                    }
                    if (Y > Engine.HEIGHT - 2) {
                        Y = Engine.HEIGHT - 2;
                    }
                    world[X][Y] = Tileset.FLOOR;
                }
            }
        }
    }

    public void buildWall(TETile[][] world) {
        for (int x = 0; x < Engine.WIDTH; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                if (world[x][y] == Tileset.FLOOR) {
                    continue;
                }
                int UP = y + 1;
                int DOWN = y - 1;
                int LEFT = x - 1;
                int RIGHT = x + 1;
                if (UP > Engine.HEIGHT - 1) {
                    UP = Engine.HEIGHT - 1;
                }
                if (DOWN < 0) {
                    DOWN = 0;
                }
                if (LEFT < 0) {
                    LEFT = 0;
                }
                if (RIGHT > Engine.WIDTH - 1) {
                    RIGHT = Engine.WIDTH - 1;
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
        for (int x = 0; x < Engine.WIDTH; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                if (world[x][y] == Tileset.FLOOR) {
                    if (Engine.seed.nextInt(4) == 0){
                            world[x][y] = Tileset.GRASS;
                    }
                }
            }
        }
    }

    public void buildLockedDoor(TETile[][] world) {
        ArrayList<Position> p = new ArrayList<>();
        for (int x = (int) (Engine.WIDTH * 0.5); x < Engine.WIDTH; x += 1) {
            for (int y = (int) (Engine.HEIGHT * 0.5); y < Engine.HEIGHT; y += 1) {
                if (world[x][y] == Tileset.WALL) {
                    int UP = y + 1;
                    int DOWN = y - 1;
                    int LEFT = x - 1;
                    int RIGHT = x + 1;
                    if (UP > Engine.HEIGHT - 1) {
                        UP = Engine.HEIGHT - 1;
                    }
                    if (DOWN < 0) {
                        DOWN = 0;
                    }
                    if (LEFT < 0) {
                        LEFT = 0;
                    }
                    if (RIGHT > Engine.WIDTH - 1) {
                        RIGHT = Engine.WIDTH - 1;
                    }

                    // avoid door showing up in a weird place
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
        int random = Engine.seed.nextInt(p.size());
        world[p.get(random).getX()][p.get(random).getY()] = Tileset.LOCKED_DOOR;
    }

    public void buildLightSource(TETile[][] world) {
        world[positions.get(2).getX()][positions.get(2).getY()] = Tileset.LIGHT;
    }

    public void cleanTrash(TETile[][] world) {
        for (int x = 0; x < Engine.WIDTH; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                if (world[x][y] == Tileset.WALL) {
                    int UP = y + 1;
                    int DOWN = y - 1;
                    int LEFT = x - 1;
                    int RIGHT = x + 1;
                    if (UP > Engine.HEIGHT - 1) {
                        UP = Engine.HEIGHT - 1;
                    }
                    if (DOWN < 0) {
                        DOWN = 0;
                    }
                    if (LEFT < 0) {
                        LEFT = 0;
                    }
                    if (RIGHT > Engine.WIDTH - 1) {
                        RIGHT = Engine.WIDTH - 1;
                    }

                    // avoid walls are isolated in the middle
                    if ((world[x][UP] == Tileset.FLOOR && world[x][DOWN] == Tileset.FLOOR)
                            || (world[LEFT][y] == Tileset.FLOOR && world[RIGHT][y] == Tileset.FLOOR)) {
                        world[x][y] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

    // ----------------useless----------------
    public void buildLongHorizontal(TETile[][] world) {
        switch (Engine.seed.nextInt(3)){
            case 0 :
                int LEFT = positions.get(Engine.seed.nextInt(numberOfPoint - 10)).getX();
                int RIGHT = (int) Math.round(Engine.seed.nextInt(Engine.WIDTH) * 0.8) + 5;
                if (LEFT > RIGHT) {
                    int temp = LEFT;
                    LEFT = RIGHT;
                    RIGHT = temp;
                }
                if (LEFT < 1) {
                    LEFT = 1;
                }
                if (RIGHT > Engine.WIDTH - 2) {
                    RIGHT = Engine.WIDTH - 2;
                }
                int y = (int) Math.round(Engine.seed.nextInt(Engine.HEIGHT) * 0.8) + 1;
                for (int n = LEFT; n < RIGHT; n += 1) {
                    world[n][y] = Tileset.FLOOR;
                    world[n][y + 1] = Tileset.FLOOR;
                    world[n][y + 2] = Tileset.FLOOR;
                }
            case 1 :
                LEFT = positions.get(Engine.seed.nextInt(numberOfPoint - 10)).getX();
                RIGHT = (int) Math.round(Engine.seed.nextInt(Engine.WIDTH) * 0.8) + 5;
                if (LEFT > RIGHT) {
                    int temp = LEFT;
                    LEFT = RIGHT;
                    RIGHT = temp;
                }
                if (LEFT < 1) {
                    LEFT = 1;
                }
                if (RIGHT > Engine.WIDTH - 2) {
                    RIGHT = Engine.WIDTH - 2;
                }
                y = (int) Math.round(Engine.seed.nextInt(Engine.HEIGHT) * 0.8) + 1;
                for (int n = LEFT; n < RIGHT; n += 1) {
                    world[n][y] = Tileset.FLOOR;
                    world[n][y + 1] = Tileset.FLOOR;
                }
            case 2 :
                LEFT = positions.get(Engine.seed.nextInt(numberOfPoint - 10)).getX();
                RIGHT = (int) Math.round(Engine.seed.nextInt(Engine.WIDTH) * 0.8) + 5;
                if (LEFT > RIGHT) {
                    int temp = LEFT;
                    LEFT = RIGHT;
                    RIGHT = temp;
                }
                if (LEFT < 1) {
                    LEFT = 1;
                }
                if (RIGHT > Engine.WIDTH - 2) {
                    RIGHT = Engine.WIDTH - 2;
                }
                y = (int) Math.round(Engine.seed.nextInt(Engine.HEIGHT) * 0.8) + 1;
                for (int n = LEFT; n < RIGHT; n += 1) {
                    world[n][y] = Tileset.FLOOR;
                }
        }
    }
}
