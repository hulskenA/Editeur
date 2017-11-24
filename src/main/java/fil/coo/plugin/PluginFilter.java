package fil.coo.plugin;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {
		private final static String pathForClass = "fil.coo.plugins";

		public boolean accept(File dir, String name) {
			  if (name.endsWith(".class")) {
						try {
								Class<?> c = Class.forName(pathForClass + "." + name.substring(0, name.lastIndexOf(".class")));
								if (Class.forName("fil.coo.plugin.Plugin").isAssignableFrom(c) && c.getPackage().toString().equals("package " + pathForClass))
										return true;
						} catch (Exception e) {}
	      }

				return false;
		}

}
