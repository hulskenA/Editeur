package fil.coo.checker.util;

import fil.coo.checker.FileListener;


public class FileListenerAddPrinter implements FileListener {

	public void fileAdded(FileEvent file) {
		System.out.println("nouveau .class : " + file.getSource() + " détecté");
	}

	public void fileRemoved(FileEvent file) {}

}
