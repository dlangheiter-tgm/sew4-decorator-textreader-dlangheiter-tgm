package decorator.textreader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class WorkerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test(timeout = 50)
    public void write() {
        String input = "this is a long input that should test the system";
        // Setup InputStream to use out stream
        InputStream fakeIn = new ByteArrayInputStream((input + "\n").getBytes());
        System.setIn(fakeIn);
        // Needs to be created after `System.setIn` is called
        Worker worker = new Worker();

        String[] got = {"should not be here after"};
        worker.write(got);
        assertArrayEquals(new String[] {input}, got);
    }

    @Test(timeout = 50)
    public void read() {
        String ouput = "this is a long output that should test the system";
        String expected = String.format("Output:\t\t" + ouput + "%n");

        Worker worker = new Worker();
        worker.read(new String[] {ouput});
        assertEquals(expected, outContent.toString());
    }
}