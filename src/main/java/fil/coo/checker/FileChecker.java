package fil.coo.checker;

import java.io.File;


public class FileChecker {

  protected File folder;
  protected FileNameFilter filter;

  protected List<FileListener> listeners;
  protected List<String> files;

  public FileChecker(FileNameFilter filter, File folder) {
    this.filter = filter;
    this.folder = folder;

    this.listeners = new ArrayList<FileListener>();
    this.files = new ArrayList<String>();
  }

  public void addListener(FileListener listener) {
    this.listeners.add(listener);
  }

  public void removeListener(FileListener listener) {
    this.listener.remove(listener);
  }

  public void fireFileAdded(String file) {
    List<FileListener> listenersCopy = new ArrayList<FileListener>().addAll(this.listeners);
    for (FileListener listener : listenersCopy)
      listener.fileAdded(new FileEvent(file));
  }

  public void startChecker() {
    int delay = 10000; // toutes les 10 secondes
		ActionListener task = new ActionListenerChecker();
		Timer timer = new Timer(delay, task);
		timer.start();

		while (true);
  }


  public static class ActionListenerChecker implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      for (String s : folder.list(new FileNameFilterC()))
        if (!FileChecker.this.files.contains(s)) {
          FileChecker.this.files.add(s);
          FileChecker.this.fireFileAdded(s);
        }
    }
  }

}
