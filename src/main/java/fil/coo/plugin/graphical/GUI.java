package fil.coo.plugin.graphical;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import fil.coo.plugin.Plugin;
import fil.coo.plugin.tools.Tools;
import fil.coo.plugin.tools.PluginFilter;
import fil.coo.plugin.tools.events.FileEvent;
import fil.coo.plugin.tools.events.CloseWindowEvent;
import fil.coo.plugin.tools.events.HelperWindowActionListener;
import fil.coo.plugin.tools.events.FileListener;
import fil.coo.plugin.tools.langages.Translator;
import fil.coo.plugin.tools.langages.LangageFilter;
import fil.coo.plugin.graphical.util.PluginMenuItemActionListener;
import fil.coo.plugin.graphical.util.PluginHelpMenuItemActionListener;
import fil.coo.plugin.exceptions.NoSuchFileLangageException;

@SuppressWarnings("serial")
public class GUI extends JFrame implements FileListener {

	protected JTextArea text = new JTextArea();
	protected JMenuBar menuBar = new JMenuBar();
	protected JMenu filesMenu = new JMenu();
	protected JMenu settingsMenu = new JMenu();
	protected JMenu pluginsMenu = new JMenu();
	protected JMenu helpMenu = new JMenu();
	protected JMenu langagesSubMenu = new JMenu();
	protected JMenuItem openMenuItem = new JMenuItem();
	protected JMenuItem resetMenuItem = new JMenuItem();
	protected JMenuItem closeMenuItem = new JMenuItem();
	protected JMenuItem zoomMenuItem = new JMenuItem();
	protected JMenuItem unzoomMenuItem = new JMenuItem();
	protected JMenuItem helpApp = new JMenuItem();

	protected Map<String, JMenuItem> pluginsMenuItem = new HashMap<String, JMenuItem>();
	protected Map<String, JMenuItem> langagesMenuItem = new HashMap<String, JMenuItem>();
	protected Map<String, JMenuItem> pluginHelperMenuItem = new HashMap<String, JMenuItem>();

	protected void initTextFields() {
		this.setTitle(Translator.SINGLETON.translate("Plugin project"));
		this.filesMenu.setText(Translator.SINGLETON.translate("Files"));;
		this.settingsMenu.setText(Translator.SINGLETON.translate("Settings"));
		this.pluginsMenu.setText(Translator.SINGLETON.translate("Plugins"));
		this.helpMenu.setText(Translator.SINGLETON.translate("Help"));
		this.langagesSubMenu.setText(Translator.SINGLETON.translate("Langages"));
		this.openMenuItem.setText(Translator.SINGLETON.translate("Open"));
		this.resetMenuItem.setText(Translator.SINGLETON.translate("New File"));
		this.closeMenuItem.setText(Translator.SINGLETON.translate("Exit"));
		this.zoomMenuItem.setText(Translator.SINGLETON.translate("Zoom"));
		this.unzoomMenuItem.setText(Translator.SINGLETON.translate("Unzoom"));
		this.helpApp.setText(Translator.SINGLETON.translate("Help"));
	}

	protected void initMenu() {
		this.initTextFields();

		// Ajout des items Files
		this.filesMenu.add(this.openMenuItem);
	  this.filesMenu.add(this.resetMenuItem);
	  this.filesMenu.addSeparator();
	  this.filesMenu.add(this.closeMenuItem);

		// Ajout des items Settings
		this.settingsMenu.add(this.zoomMenuItem);
		this.settingsMenu.add(this.unzoomMenuItem);
		this.settingsMenu.addSeparator();
		this.settingsMenu.add(this.langagesSubMenu);

		// Ajout des items Help
	  this.helpMenu.add(this.helpApp);
	  this.helpMenu.addSeparator();

		// Ajout des menus et de la bar à la fenetre
		this.menuBar.add(this.filesMenu);
	  this.menuBar.add(this.settingsMenu);
	  this.menuBar.add(this.pluginsMenu);
	  this.menuBar.add(this.helpMenu);
	  this.setJMenuBar(this.menuBar);

		// Comportement des items Files
	  this.resetMenuItem.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		GUI.this.text.setText("");
	   	}
	  });
		this.resetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
	  this.openMenuItem.addActionListener(new OpenMenuItemActionListener());
		this.openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
	  this.closeMenuItem.addActionListener(new CloseWindowEvent());
		this.closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));

		// Comportement des items Settings
		this.zoomMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float sizeSaved = new Float(Tools.settings.get("FONT_SIZE"));
				Tools.settings.put("FONT_SIZE", Float.toString(Tools.getFONT_SIZE() * new Float(1.5)));

				new FontSizeActionListener().actionPerformed(e);
			}
		});
		this.zoomMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_MASK));
		this.unzoomMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tools.settings.put("FONT_SIZE", Float.toString(Tools.getFONT_SIZE() * new Float(0.5)));

				new FontSizeActionListener().actionPerformed(e);
			}
		});
		this.unzoomMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_MASK));

		// Comportement des items Help
		this.helpApp.addActionListener(new HelperWindowActionListener());//
		this.helpApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));
	}


	public GUI() {
		// Generale de la fenetre
		super();
	  this.setLocationRelativeTo(null);
		this.setSize(800, 600);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				new CloseWindowEvent().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST, "quit"));
			}
		});
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Ajout du TextArea
		this.text.setFont(this.text.getFont().deriveFont(Tools.getFONT_SIZE()));
		this.add(new JScrollPane(this.text));

		// Le Menu de la fenetre
		this.initMenu();

		// Rendre la fenetre visible
		this.setVisible(true);
	}


  public void fileAdded(FileEvent file) {
    String name = (String) file.getSource();
		JMenuItem item;
		// chercher une autre méthode si possible
		if (new PluginFilter().accept(new File("resources/" + Tools.PACKAGEFORPLUGIN), name)) {
	    try {
	      Class<?> c = Class.forName(Tools.PACKAGEFORPLUGIN + "." + name.substring(0, name.lastIndexOf(".class")));
	      Plugin plugin = (Plugin) c.getConstructor().newInstance();

				item = new JMenuItem(plugin.getLabel());
				this.pluginsMenu.add(item);
				this.pluginsMenuItem.put(name, item);
				item.addActionListener(new PluginActionMenuItemActionListener(plugin));

				item = new JMenuItem(plugin.getLabel());
				this.helpMenu.add(item);
				this.pluginHelperMenuItem.put(name, item);
				item.addActionListener(new PluginHelpMenuItemActionListener(plugin));
	    } catch (Exception e) {}
		}

		else if (new LangageFilter().accept(new File(Tools.PATHFORLANGAGES), name)) {
			item = new JMenuItem(name);
			try {
				item.addActionListener(new changeLangageActionListener());
				this.langagesSubMenu.add(item);
				this.langagesMenuItem.put(name, item);
			} catch	(Exception e) {}
		}
  }

  public void fileRemoved(FileEvent file) {
		String name = ((String) file.getSource());

		if (new PluginFilter().accept(new File("resources/" + Tools.PACKAGEFORPLUGIN), name)) {
			this.pluginsMenu.remove(this.pluginsMenuItem.get(name));
			this.pluginsMenuItem.remove(name);
			this.helpMenu.remove(this.pluginHelperMenuItem.get(name));
			this.pluginHelperMenuItem.remove(name);
		}

		else if (new LangageFilter().accept(new File(Tools.PATHFORLANGAGES), name)) {
			this.langagesSubMenu.remove(this.langagesMenuItem.get(name));
			this.langagesMenuItem.remove(name);
		}
	}


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
    	chooser.setFileFilter(new FileNameExtensionFilter(Translator.SINGLETON.translate("text files"), "txt"));
    	chooser.setCurrentDirectory(new File("./"));
    	int status = chooser.showOpenDialog(new JFrame());
    	if (status == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = chooser.getSelectedFile();
    		GUI.this.text.setText(Tools.readFile(selectedFile.getAbsolutePath()));
    	}
		}
	}

	protected class FontSizeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Font font = GUI.this.text.getFont();
			GUI.this.text.setFont(font.deriveFont(Float.parseFloat(Tools.settings.get("FONT_SIZE"))));
		}
	}

	protected class changeLangageActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String langFile = ((JMenuItem) e.getSource()).getText();
			Tools.settings.put("LANG", langFile);
			Translator.SINGLETON.close();
			try {
				Translator.SINGLETON.open(new File(Tools.PATHFORLANGAGES + "/" + langFile));
			} catch (NoSuchFileLangageException ex) {
				ex.printStackTrace();
				System.exit(1);
			}

			GUI.this.initTextFields();
		}
	}

}
