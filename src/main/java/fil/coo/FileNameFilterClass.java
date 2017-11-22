package fil.coo;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilterClass implements FilenameFilter {

	public boolean accept(File dir, String name) {
		return name.endsWith("class");
	}

}
