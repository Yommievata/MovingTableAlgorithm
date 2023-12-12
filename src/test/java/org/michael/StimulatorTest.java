package org.michael;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StimulatorTest {

    @Before
    public void setUp() {
        Stimulator stimulator = new Stimulator(new Table(5, 5), new Movement(new int[]{2, 2}, new int[]{0, -1}));
    }

    @Test
    public void testMoveForward() {
        setInputStream("5 5 2 2 north\n1\n0");

        Stimulator.main(null);

        int[] finalPosition = Stimulator.getMovement().getCurrentPosition();
        int[] expectedPosition = new int[]{2, 1};
        assertArrayEquals("Incorrect final position", expectedPosition, finalPosition);
    }

    @Test
    public void testMoveBackward() {
        setInputStream("5 5 2 2 north\n2\n0");

        Stimulator.main(null);

        int[] finalPosition = Stimulator.getMovement().getCurrentPosition();
        int[] expectedPosition = new int[]{2, 3};
        assertArrayEquals("Incorrect final position", expectedPosition, finalPosition);
    }

    @Test
    public void testRotateClockwise() {
        setInputStream("5 5 2 2 north\n3\n0");

        Stimulator.main(null);

        String finalDirection = Stimulator.getDirectionString();
        assertEquals("Incorrect final direction","east", finalDirection);
    }

    @Test
    public void testRotateCounterClockwise() {
        setInputStream("5 5 2 2 north\n4\n0");

        Stimulator.main(null);

        String finalDirection = Stimulator.getDirectionString();
        assertEquals("Incorrect final direction", "west", finalDirection);
    }

    private void setInputStream(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}