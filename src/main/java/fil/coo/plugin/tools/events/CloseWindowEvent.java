package fil.coo.plugin.tools.events;

import java.awt.event.*;

import fil.coo.plugin.tools.Tools;
import fil.coo.plugin.exceptions.NoSuchSettingsFileException;
import fil.coo.plugin.tools.langages.Translator;


public class CloseWindowEvent implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      Tools.saveSettings();
    } catch (NoSuchSettingsFileException ex) {
      ex.printStackTrace();
      System.exit(1);
    } finally {
      Translator.SINGLETON.close();
    }

    System.exit(0);
  }
}
