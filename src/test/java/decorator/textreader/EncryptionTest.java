package decorator.textreader;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionTest {

    @Test
    public void decryptRightPassword() {
        String input = "This is a input";
        String password = "thisisalongpassword";

        assertEquals(Encryption.decrypt(Encryption.encrypt(input, password), password), input);
    }

    @Test
    public void decryptWrongPassword() {
        String input = "This is a input";
        String password = "thisisalongpassword";

        assertNull(Encryption.decrypt(Encryption.encrypt(input, password), password + "test"));
    }
}