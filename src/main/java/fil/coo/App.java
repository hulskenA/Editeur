package fil.coo;

import java.io.File;
import java.util.Map;

import fil.coo.plugin.graphical.GUI;
import fil.coo.plugin.tools.Tools;
import fil.coo.plugin.tools.FileChecker;
import fil.coo.plugin.tools.SimplePluginObserver;
import fil.coo.plugin.tools.PluginFilter;
import fil.coo.plugin.tools.langages.Translator;
import fil.coo.plugin.tools.langages.LangageFilter;


public class App {

  public static void main(String[] args) {
    LangageFilter langFilter = new LangageFilter();
    File langFile = new File(Tools.PATHFORLANGAGES);
    if (langFile.list(langFilter).length == 0) {
      System.out.println("Langage Error : No langage's file found in resources");
      System.exit(1);
    } else {
      Translator.SINGLETON.open(new File(Tools.PATHFORLANGAGES + "/" + Tools.settings.get("LANG")));
    }


    FileChecker classChecker = new FileChecker(new PluginFilter(), new File("resources/" + Tools.PACKAGEFORPLUGIN));

	  FileChecker langagesChecker = new FileChecker(langFilter, langFile);
    GUI gui = new GUI();
    SimplePluginObserver observer = new SimplePluginObserver();

    classChecker.addListener(observer);
    classChecker.addListener(gui);
    langagesChecker.addListener(observer);
    langagesChecker.addListener(gui);
  }

}
