package fil.coo.checker.util;

import java.awt.event.ActionEvent;

public class FileEvent extends ActionEvent {
  protected String file;

  public FileEvent(String file) {
	  this.file = file;
  }

  public String getFile() {
	  return this.file;
  }
}
