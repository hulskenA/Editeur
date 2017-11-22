package fil.coo;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilterC implements FilenameFilter {

	public boolean accept(File dir, String name) {
		return name.startsWith("C");
	}

}
