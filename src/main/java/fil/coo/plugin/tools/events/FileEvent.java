package fil.coo.plugin.tools.events;

import java.util.EventObject;


/**
 * Defines what information a FileEvent must hold.
 * A FileEvent holds a string containing the name of the
 * file that the event concerns.
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
@SuppressWarnings("serial")
public class FileEvent extends EventObject {
	
	/**
	 * Constructor for a FileEvent.
	 * @param file The file that this event concerns
	 */
	public FileEvent(String file) {
		super (file);
	}
}
