package decorator.textreader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Class to encrypt/decrypt text.
 * Encryption: Supplied array needs to be the password, next decorator needs to be the text
 * Decryption: Supplied array needs to be [input, password], gives the decrypted text further
 */
public class Scrambling extends Decorator {
    private TextReader textReader;

    public Scrambling(TextReader textReader) {
        this.textReader = textReader;
    }


    public void write(String[] s) {
        String[] got = {new String()};
        textReader.write(got);

        System.out.println("encrypt:\t\t");

        s[0] = Encryption.encrypt(got[0], s[0]);
    }

    public void read(String[] s) {
        String out = Encryption.decrypt(s[0], s[1]);
        if (out == null) {
            System.out.print("ERROR:\t\tWrong password");
            return;
        }
        System.out.println("decrypt\t\t");
        textReader.read(new String[]{out});
    }



}
