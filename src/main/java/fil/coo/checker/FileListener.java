package fil.coo.checker;

import fil.coo.checker.util.FileEvent;

public interface FileListener {
  public void fileAdded(FileEvent file);
  public void fileRemoved(FileEvent file);
}
