package fil.coo.plugin.tools.events;

import fil.coo.plugin.tools.events.FileEvent;


public interface FileListener {
  public void fileAdded(FileEvent file);
  public void fileRemoved(FileEvent file);
}
