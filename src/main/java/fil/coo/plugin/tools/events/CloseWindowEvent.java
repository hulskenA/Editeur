package fil.coo.plugin.tools.events;

import java.awt.event.*;

import fil.coo.plugin.tools.Tools;


public class CloseWindowEvent implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    Tools.saveSettings();
    System.exit(0);
  }
}
