package fil.coo.plugin;


/**
 * 
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
public interface Plugin {
  public String transform(String s);
  public String getLabel();
  public String helpMessage();
}
