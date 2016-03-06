package bostock.michael.model;


import bostock.michael.random.Randomizer;

import java.util.Arrays;

public class Particles {
    private final Particle[][] particles;
    private final int width;
    private final int height;

    public Particles(int width, int height) {
        this.width = width;
        this.height = height;
        this.particles = new Particle[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                particles[x][y] = new Particle();
            }
        }
    }

    public Particles(final Particles other) {
        this.width = other.width;
        this.height = other.height;
        this.particles = new Particle[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                particles[x][y] = new Particle(other.particles[x][y]);
            }
        }
    }

    public boolean isParticleStuck(final Position position) {
        boolean particleStuck = false;
        if (positionIsValid(position)) {
            particleStuck = getParticle(position).isStuck();
        }
        return particleStuck;
    }

    public int getStuckOrder(final Position position) {
        int stuckOrder = 0;
        if (positionIsValid(position)) {
            stuckOrder = getParticle(position).getStuckOrder();
        }
        return stuckOrder;
    }

    public Position getRandomPosition() {
        return new Position(Randomizer.randomBetween(0, width - 1), Randomizer.randomBetween(0, height - 1));
    }

    public Position move(final Position position, final Direction direction) {
        return wrapPositionToExtents(position.move(direction));
    }

    public boolean particleShouldStick(final Position position) {
        final Position above = move(position, Direction.UP);
        final Position below = move(position, Direction.DOWN);
        final Position left = move(position, Direction.LEFT);
        final Position right = move(position, Direction.RIGHT);
        return !isParticleStuck(position) && (isParticleStuck(above) || isParticleStuck(below) || isParticleStuck(left) || isParticleStuck(right));
    }

    public void stick(final Position position, final int stuckOrder) {
        if (positionIsValid(position)) {
            getParticle(position).setStuck(stuckOrder);
        }
    }

    public int getNumStuckParticles() {
        return (int) Arrays.stream(particles).flatMap(Arrays::stream).filter(Particle::isStuck).count();
    }

    private Particle getParticle(final Position position) {
        return particles[position.getX()][position.getY()];
    }

    private Position wrapPositionToExtents(final Position position) {
        return position.wrapToExtents(new Position(0, 0), new Position(width - 1, height - 1));
    }

    private boolean positionIsValid(final Position position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                builder.append(getParticle(new Position(x, y)).toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
