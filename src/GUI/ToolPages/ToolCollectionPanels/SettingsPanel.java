package GUI.ToolPages.ToolCollectionPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class SettingsPanel extends ContentPanelBase implements ActionListener {

	JLabel title;
	
	TitledBorder themeLabel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	JPanel titlePanel, buttonPanel;
	
	JButton confirmThemeButton;
	
	JRadioButton darkTheme, lightTheme, intellijTheme,draculaTheme;
	
	ButtonGroup themes;
	
	public SettingsPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e){    
		if(darkTheme.isSelected()){    
	    	try {
	    	    UIManager.setLookAndFeel( new FlatDarkLaf() );
	    	} catch( Exception ex ) {
	    	    System.err.println( "Failed to initialize theme. Using fallback." );
	    	}
		}    
		if(lightTheme.isSelected()){    
	    	try {
	    	    UIManager.setLookAndFeel( new FlatLightLaf() );
	    	} catch( Exception ex ) {
	    	    System.err.println( "Failed to initialize theme. Using fallback." );
	    	}
		}   
		if(intellijTheme.isSelected()){    
	    	try {
	    	    UIManager.setLookAndFeel( new FlatIntelliJLaf () );
	    	} catch( Exception ex ) {
	    	    System.err.println( "Failed to initialize theme. Using fallback." );
	    	}
		}    
		if(draculaTheme.isSelected()){    
	    	try {
	    	    UIManager.setLookAndFeel( new FlatDarculaLaf () );
	    	} catch( Exception ex ) {
	    	    System.err.println( "Failed to initialize theme. Using fallback." );
	    	}
		}   
		SwingUtilities.updateComponentTreeUI(this.getRootPane());
	}
	
	private JPanel getTitlePanel(String titleText) {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(400,50));
		
		title = new JLabel(titleText);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);
		title.setFont(titleFont);
		title.setPreferredSize(new Dimension(400, 50));
		
		titlePanel.add(title);
		
		return titlePanel;
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.PAGE_AXIS));
		
		JPanel radioButtons = new JPanel();
		
		themeLabel = BorderFactory.createTitledBorder("Choose theme");
		
		themes = new ButtonGroup();
		
		darkTheme = new JRadioButton("Dark Theme");
		darkTheme.setSelected(true);
		lightTheme = new JRadioButton("Light Theme");
		intellijTheme = new JRadioButton("IntelliJ Theme");
		draculaTheme = new JRadioButton("Dracula Theme");
		
		confirmThemeButton = new TopPanelButton("Change theme");
		confirmThemeButton.setPreferredSize(new Dimension(100,50));
		confirmThemeButton.addActionListener(this);
		
		themes.add(darkTheme);
		themes.add(lightTheme);
		themes.add(intellijTheme);
		themes.add(draculaTheme);

		radioButtons.add(darkTheme);
		radioButtons.add(lightTheme);
		radioButtons.add(intellijTheme);
		radioButtons.add(draculaTheme);
		radioButtons.setPreferredSize(new Dimension(150,50));
		radioButtons.setBorder(themeLabel);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		bPanel.add(radioButtons);
		bPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		bPanel.add(confirmThemeButton);
		bPanel.add(Box.createRigidArea(new Dimension(0, 250)));
		
		return bPanel;
	}
	
}
