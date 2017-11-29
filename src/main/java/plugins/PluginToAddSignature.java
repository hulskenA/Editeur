package plugins;

import fil.coo.plugin.Plugin;


public class PluginToAddSignature implements Plugin {
  public String transform(String s) {
    return s+"\n\nPresented by Martin VASILEV & Alexandre HULSKEN";
  }

  public String getLabel() {
    return "Signature";
  }

  public String helpMessage() {
    return "Added our signature to the text field";
  }
}
