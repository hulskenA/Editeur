package fil.coo.plugin.tools;

import java.io.File;
import java.io.FilenameFilter;

import fil.coo.plugin.tools.Tools;

/**
 * A plugin filter will check whether a given filename
 * corresponds to a plugin filename.
 * It will check :  
 * - Ends with .class
 * - Package it belongs to (Tools.PACKAGEFORPLUGIN)
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
public class PluginFilter implements FilenameFilter {
	/*
	 * (non-Javadoc)
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
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
