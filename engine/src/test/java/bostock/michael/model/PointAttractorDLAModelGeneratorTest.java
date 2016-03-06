package bostock.michael.model;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PointAttractorDLAModelGeneratorTest {

    @Test
    public void initial_point_is_stuck_on_initialisation() {
        PointAttractorDLAModelGenerator generator = new PointAttractorDLAModelGenerator(new Position(5, 5), 10, 10);

        assertThat(generator.getInitialParticles().isParticleStuck(new Position(5, 5)), is(true));
        assertThat(generator.getInitialParticles().getNumStuckParticles(), is(1));
    }

    @Test
    public void stuck_order_starts_at_one_and_increments_by_one() {
        PointAttractorDLAModelGenerator generator = new PointAttractorDLAModelGenerator(new Position(5, 5), 10, 10);

        for (int i = 1; i <= 10; i++) {
            assertThat(generator.getStuckOrder(), is(i));
        }
    }

    @Test
    public void same_number_of_points_requested_end_up_being_stuck_after_growing() {
        PointAttractorDLAModelGenerator generator = new PointAttractorDLAModelGenerator(new Position(50, 50), 100, 100);

        int numParticles = 1000;
        Particles finalParticles = generator.grow(numParticles);
        System.out.println(finalParticles);

        assertThat(finalParticles.getNumStuckParticles(), is(numParticles));
    }
}