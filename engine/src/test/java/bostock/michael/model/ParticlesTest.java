package bostock.michael.model;


import org.junit.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class ParticlesTest {

    @Test
    public void particles_are_initialised_correctly() {
        Particles particles = new Particles(10, 10);

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Position position = new Position(x, y);
                assertThat(particles.isParticleStuck(position), is(false));
                assertThat(particles.getStuckOrder(position), is(0));
            }
        }
    }

    @Test
    public void particle_gets_stuck_correctly() {
        Particles particles = new Particles(10, 10);

        Position stuckPosition = new Position(5, 5);
        particles.stick(stuckPosition, 3);

        assertThat(particles.isParticleStuck(stuckPosition), is(true));
        assertThat(particles.getStuckOrder(stuckPosition), is(3));
    }

    @Test
    public void random_particle_position_is_always_within_bounds() {
        Particles particles = new Particles(10, 10);

        for (int i = 0; i < 1000; i++) {
            Position random = particles.getRandomPosition();
            int x = random.getX();
            int y = random.getY();
            assertThat(x, greaterThanOrEqualTo(0));
            assertThat(x, lessThan(10));
            assertThat(y, greaterThanOrEqualTo(0));
            assertThat(y, lessThan(10));
        }
    }

    @Test
    public void moving_a_position_at_edge_of_particles_wraps_position() {
        Particles particles = new Particles(10, 10);

        Position top = new Position(0, 9);
        Position bottom = new Position(0, 0);
        Position left = new Position(0, 1);
        Position right = new Position(9, 1);

        assertThat(particles.move(top, Direction.UP).getY(), is(0));
        assertThat(particles.move(bottom, Direction.DOWN).getY(), is(9));
        assertThat(particles.move(left, Direction.LEFT).getX(), is(9));
        assertThat(particles.move(right, Direction.RIGHT).getX(), is(0));
    }

    @Test
    public void particle_should_stick_if_particle_above_is_stuck() {
        Particles particles = new Particles(10, 10);

        particles.stick(new Position(0, 1), 1);

        assertThat(particles.particleShouldStick(new Position(0, 0)), is(true));
    }

    @Test
    public void particle_should_stick_if_particle_below_is_stuck() {
        Particles particles = new Particles(10, 10);

        particles.stick(new Position(0, 0), 1);

        assertThat(particles.particleShouldStick(new Position(0, 1)), is(true));
    }

    @Test
    public void particle_should_stick_if_particle_to_left_is_stuck() {
        Particles particles = new Particles(10, 10);

        particles.stick(new Position(1, 0), 1);

        assertThat(particles.particleShouldStick(new Position(0, 0)), is(true));
    }

    @Test
    public void particle_should_stick_if_particle_to_right_is_stuck() {
        Particles particles = new Particles(10, 10);

        particles.stick(new Position(0, 0), 1);

        assertThat(particles.particleShouldStick(new Position(1, 0)), is(true));
    }

    @Test
    public void particle_should_not_stick_if_no_surrounding_particle_is_stuck() {
        Particles particles = new Particles(10, 10);

        assertThat(particles.particleShouldStick(new Position(5, 5)), is(false));
    }
}