package plugins;

import fil.coo.plugin.Plugin;


public class TestPasOk2 implements Plugin {
  public TestPasOk2 (String f) {}

  public String transform(String s) {
    return s;
  }

  public String getLabel() {
    return "";
  }

  public String helpMessage() {
    return "";
  }
}
