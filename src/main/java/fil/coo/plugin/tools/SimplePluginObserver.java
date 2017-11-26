package fil.coo.plugin.tools;

import fil.coo.plugin.tools.events.FileListener;
import fil.coo.plugin.tools.events.FileEvent;


public class SimplePluginObserver implements FileListener {

  public void fileAdded(FileEvent file) {
    System.out.println("nouveau fichier : " + file.getSource() + " détecté");
  }

  public void fileRemoved(FileEvent file) {
    System.out.println("fichier : " + file.getSource() + "supprimé détecté");
  }

}
