package fil.coo.plugin.tools.langages;

import java.io.File;
import java.io.FilenameFilter;


public class LangageFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			  if (name.endsWith(".properties"))
						return true;
        else
				    return false;
		}

}
