package plugins;

import fil.coo.plugin.Plugin;


public class BananaCorp implements Plugin {
  public String transform(String s) {
    String res = new String();

    for (int i = 0; i < s.length(); i++) {
      res +=  "Banane ";
      if (Character.isSpace(s.charAt(i)))
        res += "\n";
    }

    return res;
  }

  public String getLabel() {
    return "Banana.Corp.";
  }

  public String helpMessage() {
    return "this plugin was edited by the Banana Corp.";
  }
}
