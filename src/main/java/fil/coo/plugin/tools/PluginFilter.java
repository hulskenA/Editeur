package fil.coo.plugin.tools;

import java.io.File;
import java.io.FilenameFilter;

import fil.coo.plugin.tools.Tools;


public class PluginFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			  if (name.endsWith(".class")) {
						try {
								Class<?> c = Class.forName(Tools.PACKAGEFORPLUGIN + "." + name.substring(0, name.lastIndexOf(".class")));
								c.getConstructor();
								if (Class.forName(Tools.PATHFORPLUGIN).isAssignableFrom(c) && c.getPackage().getName().equals(Tools.PACKAGEFORPLUGIN))
									return true;
						} catch (Exception e) {}
	      }

				return false;
		}

}
