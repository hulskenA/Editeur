package fil.coo.plugin.tools;

import fil.coo.plugin.tools.FileEvent;


public interface FileListener {
  public void fileAdded(FileEvent file);
  public void fileRemoved(FileEvent file);
}
