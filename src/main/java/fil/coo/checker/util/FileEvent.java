package fil.coo.checker.util;

public class FileEvent extends EventObject {
  protected String file;

  public FileEvent(String file) {
    this.file = file;
  }

  public String getFile() {
    return this.file;
  }
}
