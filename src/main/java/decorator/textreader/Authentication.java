package decorator.textreader;

import java.io.IOException;

public class Authentication extends Decorator {
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

	}

}
