package decorator.textreader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthenticationTest {

    @Mock
    private TextReader mockTextReader;

    @Before
    public void setup() {
        mockTextReader = mock(TextReader.class);
    }

    @Test(timeout = 50)
    public void write() {
        String password = "secretPassword";
        // Setup InputStream to use out stream
        InputStream fakeIn = new ByteArrayInputStream((password + "\n").getBytes());
        System.setIn(fakeIn);
        // Needs to be created after `System.setIn` is called
        Authentication authentication = new Authentication(mockTextReader);

        authentication.write(new String[] {"testing"});
        verify(mockTextReader).write(new String[] {password});
    }

    @Test(timeout = 50)
    public void read() {
        String input = "thisisaninput";
        String password = "secretPassword";
        // Setup InputStream to use out stream
        InputStream fakeIn = new ByteArrayInputStream((password + "\n").getBytes());
        System.setIn(fakeIn);
        // Needs to be created after `System.setIn` is called
        Authentication authentication = new Authentication(mockTextReader);

        authentication.read(new String[] {input});
        verify(mockTextReader).read(new String[] {input, password});
    }
}