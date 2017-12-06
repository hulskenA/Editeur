package fil.coo.plugin.tools;

import fil.coo.plugin.tools.events.FileListener;
import fil.coo.plugin.tools.events.FileEvent;

/**
 * The SimplePluginObserver only prints to the standard output
 * when a new file has been detected or a file has been removed from
 * the corresponding directory (See FileListener & FileChecker).
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
public class SimplePluginObserver implements FileListener {

	/*
	 * (non-Javadoc)
	 * @see fil.coo.plugin.tools.events.FileListener#fileAdded(fil.coo.plugin.tools.events.FileEvent)
	 */
  public void fileAdded(FileEvent file) {
    System.out.println("nouveau fichier : " + file.getSource() + " détecté");
  }

  /*
   * (non-Javadoc)
   * @see fil.coo.plugin.tools.events.FileListener#fileRemoved(fil.coo.plugin.tools.events.FileEvent)
   */
  public void fileRemoved(FileEvent file) {
    System.out.println("fichier : " + file.getSource() + "supprimé détecté");
  }

}
