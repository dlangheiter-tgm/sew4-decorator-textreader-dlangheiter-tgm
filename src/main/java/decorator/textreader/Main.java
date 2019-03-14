package decorator.textreader;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("main:\t\tlet's start!");
        TextReader stream = new Authentication(new Scrambling(new Worker()));
        String[] str = {new String()};
        stream.write(str);
        System.out.print("DEBUG: ");
        System.out.println(Arrays.toString(str));
        System.out.println("main:\t\t" + str[0]);
        stream.read(str);
    }
}
