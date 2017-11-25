package fil.coo.plugin.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class Tools {
  public static final String pathForClass = "plugins";
  public static final String pathForPlugin = "fil.coo.plugin.Plugin";
  public static final int delayTimer = 2000; // toutes les 2 secondes

  private Tools() {}

  public static String readFile(String fileName) {
    File source = new File(fileName);
    BufferedReader in = null;
    String res = "";
    String text;

    try {
      in = new BufferedReader(new FileReader(source));
      while ((text = in.readLine()) != null)
        res += text + "\n";
      in.close();
    } catch (Exception e) {}

    return res;
  }

}
