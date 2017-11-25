package fil.coo.checker;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import fil.coo.checker.util.FileEvent;
import fil.coo.plugin.PluginFilter;


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

  	int delay = 1000; // toutes les secondes
  	ActionListener task = new ActionListenerChecker();
  	Timer timer = new Timer(delay, task);
  	timer.start();
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
  }

  public void fireFileRemoved(String file) {
    List<FileListener> listenersCopy = new ArrayList<FileListener>(this.listeners);
    for (FileListener listener : listenersCopy)
      listener.fileRemoved(new FileEvent(file));
  }


  public class ActionListenerChecker implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
          FilenameFilter filter = new PluginFilter();
          List<String> filesCopy = new ArrayList<String>(FileChecker.this.files);
          List<String> folders = Arrays.asList(FileChecker.this.folder.list(filter));

    	    for (String s : folders)
      	    	if (!filesCopy.contains(s)) {
      	    		   FileChecker.this.files.add(s);
      	           FileChecker.this.fireFileAdded(s);
    	    	   }

          for (String s : filesCopy) {
              if (!folders.contains(s)) {
                  FileChecker.this.files.remove(s);
                  FileChecker.this.fireFileRemoved(s);
              }
          }
    	}
  }

}
