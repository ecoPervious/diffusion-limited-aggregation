package bostock.michael.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class PositionTest {

    @Test
    public void position_is_initialised_correctly() {
        Position position = new Position(2, 4);

        assertThat(position.getX(), is(2));
        assertThat(position.getY(), is(4));
    }

    @Test
    public void position_is_moved_up_correctly() {
        Position position = new Position(2, 4);
        Position moved = position.move(Direction.UP);

        assertThat(moved.getX(), is(2)); // Should not change
        assertThat(moved.getY(), is(5));
    }

    @Test
    public void position_is_moved_down_correctly() {
        Position position = new Position(2, 4);
        Position moved = position.move(Direction.DOWN);

        assertThat(moved.getX(), is(2)); // Should not change
        assertThat(moved.getY(), is(3));
    }

    @Test
    public void position_is_moved_left_correctly() {
        Position position = new Position(2, 4);
        Position moved = position.move(Direction.LEFT);

        assertThat(moved.getX(), is(1));
        assertThat(moved.getY(), is(4)); // Should not change
    }

    @Test
    public void position_is_moved_right_correctly() {
        Position position = new Position(2, 4);
        Position moved = position.move(Direction.RIGHT);

        assertThat(moved.getX(), is(3));
        assertThat(moved.getY(), is(4)); // Should not change
    }

    @Test
    public void position_is_wrapped_horizontally_if_x_is_less_than_min() {
        Position position = new Position(-1, 4);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(10));
        assertThat(wrapped.getY(), is(4)); // Should not change
    }

    @Test
    public void position_is_wrapped_horizontally_if_x_is_greater_than_max() {
        Position position = new Position(11, 4);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(0));
        assertThat(wrapped.getY(), is(4)); // Should not change
    }

    @Test
    public void position_is_wrapped_vertically_if_y_is_less_than_min() {
        Position position = new Position(4, -1);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(4)); // Should not change
        assertThat(wrapped.getY(), is(10));
    }

    @Test
    public void position_is_wrapped_vertically_if_y_is_greater_than_max() {
        Position position = new Position(4, 11);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(4)); // Should not change
        assertThat(wrapped.getY(), is(0));
    }

    @Test
    public void position_is_wrapped_horizontally_and_vertically_if_x_and_y_are_less_than_min() {
        Position position = new Position(-1, -1);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(10));
        assertThat(wrapped.getY(), is(10));
    }

    @Test
    public void position_is_wrapped_horizontally_and_vertically_if_x_and_y_are_greater_than_max() {
        Position position = new Position(11, 11);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(0));
        assertThat(wrapped.getY(), is(0));
    }

    @Test
    public void position_is_not_wrapped_horizontally_or_vertically_if_x_and_y_are_within_extents() {
        Position position = new Position(2, 2);
        Position min = new Position(0, 0);
        Position max = new Position(10, 10);

        Position wrapped = position.wrapToExtents(min, max);

        assertThat(wrapped.getX(), is(2)); // Should not change
        assertThat(wrapped.getY(), is(2)); // Should not change
    }
}