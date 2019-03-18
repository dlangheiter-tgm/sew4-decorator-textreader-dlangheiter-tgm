package decorator.textreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Worker implements TextReader {
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void write(String[] s) {
        System.out.print("Input:\t\t");
        try {
            s[0] = in.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(String[] s) {
        System.out.println("Output:\t\t" + s[0]);
    }
}
