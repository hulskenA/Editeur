package fil.coo.plugin.tools.events;

import java.util.EventObject;


@SuppressWarnings("serial")
public class FileEvent extends EventObject {
	public FileEvent(String file) {
		super (file);
	}
}
