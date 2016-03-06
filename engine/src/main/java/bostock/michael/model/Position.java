package bostock.michael.model;


public final class Position {
    private final int x;
    private final int y;

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

    public Position move(final Direction direction) {
        int x = this.x;
        int y = this.y;

        switch (direction) {
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        return new Position(x, y);
    }

    public Position wrapToExtents(final Position min, final Position max) {
        int x = this.x;
        int y = this.y;

        if (x < min.x) {
            x = max.x;
        } else if (x > max.x) {
            x = min.x;
        }

        if (y < min.y) {
            y = max.y;
        } else if (y > max.y) {
            y = min.y;
        }

        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
