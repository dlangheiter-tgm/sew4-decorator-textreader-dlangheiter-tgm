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
    private static String salt = "ssshhhhhhhhhhh!!!!";

    public Scrambling(TextReader textReader) {
        this.textReader = textReader;
    }


    public void write(String[] s) {
        String[] got = {new String()};
        textReader.write(got);

        System.out.println("encrypt:\t\t");

        s[0] = encrypt(got[0], s[0]);
    }

    public void read(String[] s) {
        String out = decrypt(s[0], s[1]);
        if (out == null) {
            System.out.print("ERROR:\t\tWrong password");
            return;
        }
        System.out.println("decrypt\t\t");
        textReader.read(new String[]{out});
    }

    public String encrypt(String input, String password) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes("UTF-8")));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String input, String password) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(input)));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

}
