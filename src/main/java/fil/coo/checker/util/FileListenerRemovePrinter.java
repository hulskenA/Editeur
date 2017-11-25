package fil.coo.checker.util;

import fil.coo.checker.FileListener;


public class FileListenerRemovePrinter implements FileListener {

	public void fileAdded(FileEvent file) {}

	public void fileRemoved(FileEvent file) {
		System.out.println(".class : " + file.getSource() + "supprimé détecté");
	}

}
