package fil.coo.checker;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import fil.coo.checker.util.FileEvent;
import fil.coo.checker.util.FileNameFilterClass;

import java.util.ArrayList;


public class FileChecker {

  protected File folder;
  protected FilenameFilter filter;

  protected List<FileListener> listeners;
  protected List<String> files;

  public FileChecker(FilenameFilter filter, File folder) {
	  this.filter = filter;
	  this.folder = folder;

	  this.listeners = new ArrayList<FileListener>();
	  this.files = new ArrayList<String>();
  }

  public void addListener(FileListener listener) {
	  this.listeners.add(listener);
  }

  public void removeListener(FileListener listener) {
	  this.listeners.remove(listener);
  }

  public void fireFileAdded(String file) {
	  List<FileListener> listenersCopy = new ArrayList<FileListener>(this.listeners);
	  for (FileListener listener : listenersCopy)
		  listener.fileAdded(new FileEvent(file));
	  this.files.add(file);
  }

  public void startChecker() {
	  int delay = 10000; // toutes les 10 secondes
	  ActionListener task = new ActionListenerChecker();
	  Timer timer = new Timer(delay, task);
	  timer.start();
  }


  public class ActionListenerChecker implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    for (String s : folder.list(new FileNameFilterClass()))
	    	if (!FileChecker.this.files.contains(s)) {
	    		FileChecker.this.files.add(s);
	          	FileChecker.this.fireFileAdded(s);
	    	}
	}
  }

}
