package fil.coo.plugin.tools.events;

import java.util.EventObject;


public class FileEvent extends EventObject {
	public FileEvent(String file) {
		super (file);
	}
}
