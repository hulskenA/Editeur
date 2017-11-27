package fil.coo.plugin.tools.langages;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class that reads a properties file and allows us to be able to
 * translate strings into different languages easily.
 * Usage:
 * First, call the open static method to open the correct properties file.
 * Then, call translate with the correct property name in parameter to get
 * your string in the correct language
 * Lastly, call close to close the property file.
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
public class Translator {
	private final Properties PROP = new Properties();
	private InputStream input;
	public static final Translator SINGLETON = new Translator();

	private Translator() {}

	/**
	 * Opens the correct properties file, using the Lang parameter. This must be
	 * imperatively executed before any calls to translate!
	 * @param lang The chosen language from the game
	 */
	public void open(File langFile) {
		input = null;

		try {

			InputStream input = new FileInputStream(langFile.getAbsoluteFile());

			// load a properties file
			PROP.load(input);

		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the string in the appropriate language defined during the call to
	 * the open static method of Translator.
	 * @param str The property name that will be extracted from the properties file
	 * @return T the string corresponding to that property name
	 */
	public String translate(String str) {
		return PROP.getProperty(str);
	}

	/**
	 * Closes the open properties text file.
	 */
	public void close() {
		if(input!=null){
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
