package fil.coo.plugin.tools.events;

import java.awt.event.*;

import fil.coo.plugin.tools.Tools;
import fil.coo.plugin.exceptions.NoSuchSettingsFileException;
import fil.coo.plugin.tools.langages.Translator;


/**
 * This event is also a listener that closes the window and app
 * when the [x] is clicked on the graphical interface
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
public class CloseWindowEvent implements ActionListener {
	/**
	 * Saves the settings (Language, ...)
	 * And closes the window
	 */
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
