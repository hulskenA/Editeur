package fil.coo.plugin.tools.events;

import fil.coo.plugin.tools.events.FileEvent;

/**
 * Defines the interface for a FileListener.
 * A FileListener will listen to a FileChecker (at least in our project). (See FileChecker).
 * - A file listener must be able to react when a file has been added 
 * - A file listener must be able to react when a file has been removed
 * @author VASILEV Martin, HULSKEN Alexandre
 *
 */
public interface FileListener {
	/**
	 * A FileListener must be able to react to
	 * a file being added to a directory (See FileChecker)
	 * @param file
	 */
  public void fileAdded(FileEvent file);
  
  /**
   * A FileListener must be able to react to
   * a file being removed from a directory (See FileChecker).
   * @param file
   */
  public void fileRemoved(FileEvent file);
}
