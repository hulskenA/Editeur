package fil.coo.plugin.tools;

import fil.coo.plugin.tools.FileListener;


public class SimplePluginObserver implements FileListener {

  public void fileAdded(FileEvent file) {
    System.out.println("nouveau .class : " + file.getSource() + " détecté");
  }

  public void fileRemoved(FileEvent file) {
    System.out.println(".class : " + file.getSource() + "supprimé détecté");
  }

}
