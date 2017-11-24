package fil.coo;

import java.io.File;

import fil.coo.checker.FileChecker;
import fil.coo.checker.util.FileListenerAddPrinter;
import fil.coo.checker.util.FileListenerRemovePrinter;
import fil.coo.checker.util.FileNameFilterClass;

public class App {

  public static void main(String[] args) {
	  FileChecker checker = new FileChecker(new FileNameFilterClass(), new File("./resources"));
    checker.addListener(new FileListenerAddPrinter());
	  checker.addListener(new FileListenerRemovePrinter());

	  while (true);
  }

}
