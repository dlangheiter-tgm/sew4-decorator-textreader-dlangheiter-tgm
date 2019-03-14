package decorator.textreader;

public class Scrambling extends Decorator {
	private TextReader textReader;

	public Scrambling(TextReader textReader) {
		this.textReader = textReader;
	}


	public void write(String[] s) {
		System.out.println("encrypt:\t\t");
		String[] got = {new String()};
		textReader.write(got);
		System.out.println("Password: " + s[0] + "\tInput: " + got[0]);
	}

	public void read(String[] s) {

	}

}
