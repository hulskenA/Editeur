package fil.coo.plugin.tools;

import java.io.File;
import java.io.FilenameFilter;

import fil.coo.plugin.tools.Tools;


public class PluginFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			  if (name.endsWith(".class")) {
						try {
								Class<?> c = Class.forName(Tools.pathForClass + "." + name.substring(0, name.lastIndexOf(".class")));
								c.getConstructor();
								if (Class.forName(Tools.pathForPlugin).isAssignableFrom(c) && c.getPackage().toString().equals("package " + Tools.pathForClass))
										return true;
						} catch (Exception e) {}
	      }

				return false;
		}

}
