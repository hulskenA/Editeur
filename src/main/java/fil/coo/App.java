package fil.coo;

import java.io.File;

import fil.coo.plugin.graphical.GUI;
import fil.coo.plugin.tools.Tools;
import fil.coo.plugin.tools.FileChecker;
import fil.coo.plugin.tools.SimplePluginObserver;
import fil.coo.plugin.tools.PluginFilter;
import fil.coo.plugin.tools.langages.Translator;
import fil.coo.plugin.tools.langages.LangageFilter;
import fil.coo.plugin.exceptions.NoSuchFileLangageException;


public class App {

  public static void main(String[] args) throws NoSuchFileLangageException {
    LangageFilter langFilter = new LangageFilter();
    File langFile = new File(Tools.PATHFORLANGAGES);
    if (langFile.list(langFilter).length == 0) {
      throw new NoSuchFileLangageException(Tools.langagesExceptionMsg);
    } else {
      Translator.SINGLETON.open(new File(Tools.PATHFORLANGAGES + "/" + Tools.settings.get("LANG")));
    }


    FileChecker classChecker = new FileChecker(new PluginFilter(), new File("resources/plugins/"));
	FileChecker langagesChecker = new FileChecker(langFilter, langFile);

    GUI gui = new GUI();
    SimplePluginObserver observer = new SimplePluginObserver();

    classChecker.addListener(observer);
//    langagesChecker.addListener(observer);
    classChecker.addListener(gui.getPluginListener());
    langagesChecker.addListener(gui.getLangageListener());
  }

}
