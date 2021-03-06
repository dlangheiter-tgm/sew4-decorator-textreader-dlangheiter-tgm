package decorator.textreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to read password from User
 */
public class Authentication extends Decorator {
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private TextReader textReader;

	public Authentication(TextReader textReader) {
		this.textReader = textReader;
	}

	public void write(String[] s) {
		System.out.print("PASSWORD:\t\t");
		try {
			s[0] = in.readLine();
			textReader.write(s);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void read(String[] s) {
		System.out.print("PASSWORD:\t\t");
		String[] sn = new String[2];
		sn[0] = s[0];
		try {
			sn[1] = in.readLine();
			textReader.read(sn);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
