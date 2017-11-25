package fil.coo.plugin.graphical.util;

import java.awt.event.*;
import javax.swing.JOptionPane;

import fil.coo.plugin.Plugin;
import fil.coo.plugin.graphical.util.PluginMenuItemActionListener;


public class PluginHelpMenuItemActionListener extends PluginMenuItemActionListener {
  public PluginHelpMenuItemActionListener(Plugin plugin) {
    super(plugin);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(null, "HELP (" + this.plugin.getLabel() + ")\n\n" + this.plugin.helpMessage());
  }
}
