package decorator.textreader;


import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScramblingTest {

    @Mock
    TextReader mockTextReader;

    @Test
    public void readCorrectPassword() {
        mockTextReader = mock(TextReader.class);

        String input = "this is a test";
        String password = "thisisapassword";
        String encrypted = Encryption.encrypt(input, password);

        Scrambling s = new Scrambling(mockTextReader);

        s.read(new String[] {encrypted, password});

        verify(mockTextReader).read(new String[] {input});
    }
}