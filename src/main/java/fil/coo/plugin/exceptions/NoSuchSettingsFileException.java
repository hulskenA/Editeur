package fil.coo.plugin.exceptions;

/**
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
 @SuppressWarnings("serial")
public class NoSuchSettingsFileException extends Exception {
	public NoSuchSettingsFileException(String msg) {
		super(msg);
	}
}
