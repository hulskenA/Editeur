package plugins;

import fil.coo.plugin.Plugin;


public class ImTheOnlyTrust implements Plugin {
  public String transform(String s) {
    return "Notre projet est bien meilleur que les autres.\nJ'exige un 20 de moyenne.";
  }

  public String getLabel() {
    return "The only thrust";
  }

  public String helpMessage() {
    return "You can know the only trust to know in a life\nwith it !!";
  }
}
