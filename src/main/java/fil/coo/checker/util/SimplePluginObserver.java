package fil.coo.checker.util;

import fil.coo.checker.FileListener;


public class SimplePluginObserver implements FileListener {

  public void fileAdded(FileEvent file) {
    System.out.println("nouveau .class : " + file.getSource() + " détecté");
  }

  public void fileRemoved(FileEvent file) {
    System.out.println(".class : " + file.getSource() + "supprimé détecté");
  }

}
