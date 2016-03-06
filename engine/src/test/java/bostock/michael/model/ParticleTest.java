package bostock.michael.model;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class ParticleTest {

    @Test
    public void particle_is_initialised_correctly() {
        Particle particle = new Particle();

        assertThat(particle.isStuck(), is(false));
        assertThat(particle.getStuckOrder(), is(0));
    }

    @Test
    public void unstuck_particle_is_copied_correctly() {
        Particle original = new Particle();

        Particle copy = new Particle(original);

        assertThat(copy.isStuck(), is(false));
        assertThat(copy.getStuckOrder(), is(0));
        assertThat(copy, not(sameInstance(original)));
    }

    @Test
    public void stuck_particle_is_copied_correctly() {
        Particle original = new Particle();
        original.setStuck(1);

        Particle copy = new Particle(original);

        assertThat(copy.isStuck(), is(true));
        assertThat(copy.getStuckOrder(), is(1));
        assertThat(copy, not(sameInstance(original)));
    }

    @Test
    public void particle_is_stuck_correctly() {
        Particle particle = new Particle();

        particle.setStuck(3);

        assertThat(particle.isStuck(), is(true));
        assertThat(particle.getStuckOrder(), is(3));
    }
}