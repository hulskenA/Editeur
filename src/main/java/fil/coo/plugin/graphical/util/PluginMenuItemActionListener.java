package fil.coo.plugin.graphical.util;

import java.awt.event.*;

import fil.coo.plugin.Plugin;


public abstract class PluginMenuItemActionListener implements ActionListener {
  protected Plugin plugin;

  public PluginMenuItemActionListener(Plugin plugin) {
    this.plugin = plugin;
  }

  public abstract void actionPerformed(ActionEvent e);
}
