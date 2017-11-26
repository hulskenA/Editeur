package fil.coo.plugin.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;


public class Tools {
  public static final String pathForClass = "plugins";
  public static final String pathForPlugin = "fil.coo.plugin.Plugin";
  public static final int delayTimer = 2000; // toutes les 2 secondes
  public static Map<String, String> settings = getSettings();

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

  public static Float getFONT_SIZE() {
    return new Float(settings.get("FONT_SIZE"));
  }

  private static Map<String, String> getSettings() {
    Map<String, String> res = new HashMap<String, String>();

    try{
      Properties myProperties = new Properties();
      InputStream input = new FileInputStream("resources/settings.properties");
      myProperties.load(input);
      res.put("LANG", myProperties.getProperty("LANG"));
      res.put("FONT_SIZE", myProperties.getProperty("FONT_SIZE"));
      input.close();
    } catch (Exception e) {
      System.out.println("Settings Error : can't loaded the settings' file");
      System.exit(1);
    }

    return res;
  }

  public static void saveSettings() {
    try{
      Properties myProperties = new Properties();
      OutputStream output = new FileOutputStream("resources/settings.properties");
      for (String key : settings.keySet())
        myProperties.setProperty(key, settings.get(key));
      myProperties.store(output, null);
      output.close();
    } catch (Exception e) {
      System.out.println("Settings Error : can't loaded the settings' file");
      System.exit(1);
    }
  }
}
