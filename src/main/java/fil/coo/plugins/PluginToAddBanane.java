package plugins;

import fil.coo.plugin.Plugin;


public class PluginToAddBanane implements Plugin {
  public String transform(String s) {
    return s+"\nBanane";
  }

  public String getLabel() {
    return "Banane";
  }

  public String helpMessage() {
    return "Added banane to the text field";
  }
}
