package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu {
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	
	public Menu() {


		menuBar = new JMenuBar();

		menu = new JMenu("A Menu");
		menuBar.add(menu);

		menuItem = new JMenuItem("A text-only menu item");
		menu.add(menuItem);

		menuItem = new JMenuItem("Both text and icon");
		menu.add(menuItem);

		menuItem = new JMenuItem("asd");
		menu.add(menuItem);


		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");

		menuItem = new JMenuItem("An item in the submenu");
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		menu = new JMenu("Another Menu");
		menuBar.add(menu);

	}
	
}
