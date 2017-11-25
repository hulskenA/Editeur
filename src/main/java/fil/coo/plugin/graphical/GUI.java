package fil.coo.plugin.graphical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import fil.coo.plugin.Plugin;
import fil.coo.plugin.tools.FileListener;
import fil.coo.plugin.tools.FileEvent;
import fil.coo.plugin.graphical.util.PluginMenuItemActionListener;
import fil.coo.plugin.graphical.util.PluginHelpMenuItemActionListener;

@SuppressWarnings("serial")
public class GUI extends JFrame implements FileListener {

	protected JMenuBar menuBar = new JMenuBar();
	protected JMenu filesMenu = new JMenu("Files");
	protected JMenu settingsMenu = new JMenu("Settings");
	protected JMenu pluginsMenu = new JMenu("Plugins");
	protected JMenu helpMenu = new JMenu("Help");
	protected JMenuItem openMenuItem = new JMenuItem("Open");
	protected JMenuItem resetMenuItem = new JMenuItem("Reset");
	protected JMenuItem closeMenuItem = new JMenuItem("Exit");
	protected JMenuItem zoomMenuItem = new JMenuItem("Zoom");
	protected JMenuItem unzoomMenuItem = new JMenuItem("Unzoom");
	protected JMenuItem helpApp = new JMenuItem("Help");
	protected JTextArea text = new JTextArea();

	protected List<JMenuItem> pluginsMenuItem = new ArrayList<JMenuItem>();
	protected List<JMenuItem> pluginHelperMenuItem = new ArrayList<JMenuItem>();


	public GUI(String title) {
		// Generale de la fenetre
		super(title);
	  this.setLocationRelativeTo(null);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Ajout TextArea
		this.add(new JScrollPane(this.text));


		// Le Menu de la fenetre
		// Ajout des items Files
		this.filesMenu.add(this.openMenuItem);
	  this.filesMenu.add(this.resetMenuItem);
	  this.filesMenu.addSeparator();
	  this.filesMenu.add(this.closeMenuItem);

		// Comportement des items Files
	  this.openMenuItem.addActionListener(new OpenMenuItemActionListener());
		this.openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
	  this.closeMenuItem.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	   		System.exit(0);
	   	}
	  });
		this.closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
	  this.resetMenuItem.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		GUI.this.text.setText("");
	   	}
	  });
		this.resetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));

		// Ajout des items Settings
		this.settingsMenu.add(this.zoomMenuItem);
		this.settingsMenu.add(this.unzoomMenuItem);

		// Comportement des items Settings
		this.zoomMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font font = GUI.this.text.getFont();
				GUI.this.text.setFont(font.deriveFont((float)(font.getSize() * 1.25)));
			}
		});
		this.zoomMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_MASK));
		this.unzoomMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font font = GUI.this.text.getFont();
				GUI.this.text.setFont(font.deriveFont((float)(font.getSize() * 0.75)));
			}
		});
		this.unzoomMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_MASK));


		// Ajout des items Help
	  this.helpMenu.add(this.helpApp);
	  this.helpMenu.addSeparator();

		// Comportement des items Help
		this.helpApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Vous trouverez ici le message d'aide");
			}
		});
		this.helpApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));


		// Ajout des menus et de la bar à la fenetre
		this.menuBar.add(this.filesMenu);
	  this.menuBar.add(this.settingsMenu);
	  this.menuBar.add(this.pluginsMenu);
	  this.menuBar.add(this.helpMenu);
	  this.setJMenuBar(this.menuBar);

		// Rendre la fenetre visible
		this.setVisible(true);
	}


  public void fileAdded(FileEvent file) {
    String name = (String) file.getSource();
    try {
      Class<?> c = Class.forName("plugins." + name.substring(0, name.lastIndexOf(".class")));
      Plugin plugin = (Plugin)c.getConstructor().newInstance();

			JMenuItem item = new JMenuItem(plugin.getLabel());
			this.pluginsMenu.add(item);
			item.addActionListener(new PluginActionMenuItemActionListener(plugin));

			item = new JMenuItem("help to : " + plugin.getLabel());
			this.helpMenu.add(item);
			item.addActionListener(new PluginHelpMenuItemActionListener(plugin));
    } catch (Exception e) {}
  }

  public void fileRemoved(FileEvent file) {}


	protected class PluginActionMenuItemActionListener extends PluginMenuItemActionListener {
	  public PluginActionMenuItemActionListener(Plugin plugin) {
	    super(plugin);
	  }

	  @Override
	  public void actionPerformed(ActionEvent e) {
	    GUI.this.text.setText(this.plugin.transform(GUI.this.text.getText()));
	  }
	}

	protected class OpenMenuItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
    		chooser.setFileFilter(new FileNameExtensionFilter("txt files", "txt"));
    		chooser.setCurrentDirectory(new File("./"));
    		int status = chooser.showOpenDialog(new JFrame());
    		if (status == JFileChooser.APPROVE_OPTION) {
    			File selectedFile = chooser.getSelectedFile();
    			GUI.this.text.setText(readFile(selectedFile.getAbsolutePath()));
    		}
		}

		private String readFile(String fileName) {
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

}
