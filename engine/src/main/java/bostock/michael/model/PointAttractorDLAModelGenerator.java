package bostock.michael.model;

public class PointAttractorDLAModelGenerator implements DLAModelGenerator {
    private int stuckOrder;
    private final Particles initialParticles;

    PointAttractorDLAModelGenerator(final Position initialPoint, final int width, final int height) {
        initialParticles = new Particles(width, height);
        initialParticles.stick(initialPoint, getStuckOrder());
    }

    @Override
    public int getStuckOrder() {
        return stuckOrder++;
    }

    @Override
    public Particles getInitialParticles() {
        return initialParticles;
    }
}
