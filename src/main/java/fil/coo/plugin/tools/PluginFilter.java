package fil.coo.plugin.tools;

import java.io.File;
import java.io.FilenameFilter;


public class PluginFilter implements FilenameFilter {
		private final static String pathForClass = "plugins";
		private final static String pathForPlugin = "fil.coo.plugin.Plugin";

		public boolean accept(File dir, String name) {
			  if (name.endsWith(".class")) {
						try {
								Class<?> c = Class.forName(pathForClass + "." + name.substring(0, name.lastIndexOf(".class")));
								c.getConstructor();
								if (Class.forName(pathForPlugin).isAssignableFrom(c) && c.getPackage().toString().equals("package " + pathForClass))
										return true;
						} catch (Exception e) {}
	      }

				return false;
		}

}
