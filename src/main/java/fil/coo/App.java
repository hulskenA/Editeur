package fil.coo;

import java.io.File;

import fil.coo.GUI;
import fil.coo.checker.FileChecker;
import fil.coo.checker.util.SimplePluginObserver;
import fil.coo.plugin.PluginFilter;


public class App {

  public static void main(String[] args) {
	  FileChecker checker = new FileChecker(new PluginFilter(), new File("./target/classes/fil/coo/plugins"));
    checker.addListener(new SimplePluginObserver());

	  checker.addListener(new GUI("Plugin project"));
  }

}
