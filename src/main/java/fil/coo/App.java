package fil.coo;

import java.io.File;

import fil.coo.plugin.graphical.GUI;
import fil.coo.plugin.tools.FileChecker;
import fil.coo.plugin.tools.SimplePluginObserver;
import fil.coo.plugin.tools.PluginFilter;


public class App {

  public static void main(String[] args) {
	  FileChecker checker = new FileChecker(new PluginFilter(), new File("resources/plugins"));
    checker.addListener(new SimplePluginObserver());

	  checker.addListener(new GUI("Plugin project"));
  }

}
