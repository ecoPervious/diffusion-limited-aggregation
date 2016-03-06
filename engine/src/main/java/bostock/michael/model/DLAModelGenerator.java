package bostock.michael.model;


import bostock.michael.random.Randomizer;

public interface DLAModelGenerator {
    default Particles grow(final int numParticles) {
        Particles particles = new Particles(getInitialParticles()); // Copy the initial particles to work on
        for (int i = 0; i < numParticles; i++) {
            Position position = getRandomPosition(particles);
            while (!particles.isParticleStuck(position)) {
                position = move(position, particles);
                if (particleShouldStick(position, particles)) {
                    particles.stick(position, getStuckOrder());
                }
            }
        }
        return particles;
    }

    int getStuckOrder();

    default boolean particleShouldStick(final Position position, final Particles particles) {
        return particles.particleShouldStick(position);
    }

    default Position move(final Position position, final Particles particles) {
        return particles.move(position, Randomizer.randomDirection());
    }

    default Position getRandomPosition(final Particles particles) {
        return particles.getRandomPosition();
    }

    Particles getInitialParticles();
}
