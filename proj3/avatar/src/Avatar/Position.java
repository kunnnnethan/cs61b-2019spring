package Avatar;

public class Position implements Comparable<Position>{

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Position p) {
        if ((Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2)) - (Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)) < 0) {
            return 1;
        } else if ((Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2)) - (Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)) > 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + getX() + " , " + getY() + ")";
    }
}
