package fil.coo.plugin.tools.events;

import java.awt.event.*;
import javax.swing.JOptionPane;

import fil.coo.plugin.tools.langages.Translator;


public class HelperWindowActionListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(null, Translator.SINGLETON.translate("helper"));
  }
}
