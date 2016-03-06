package bostock.michael.model;


public class Particle {
    private boolean stuck;
    private int stuckOrder;

    public Particle() {

    }

    public Particle(final Particle other) {
        if (other.isStuck()) {
            this.setStuck(other.getStuckOrder());
        }
    }

    public boolean isStuck() {
        return stuck;
    }

    public int getStuckOrder() {
        return stuckOrder;
    }

    public void setStuck(final int stuckOrder) {
        this.stuck = true;
        this.stuckOrder = stuckOrder;
    }

    @Override
    public String toString() {
        return (stuck ? "*" : " ");
    }
}
