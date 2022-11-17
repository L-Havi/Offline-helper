package GUI.ToolPages.FileSystemToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FIleSystemTools.ToolMenus.MassChangeFileType;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import Utilities.CreateFolder;
import Utilities.FileExtension;
import Utilities.Lists.ExtensionList;
import Utilities.UserOutput.FindFiles;
import WindowsResources.CreateFolderPaths;

public class MassChangeFiletypesPanel extends ContentPanelBase implements ActionListener {

	private FileExtension fileExtension = new FileExtension();
	private FindFiles findFiles = new FindFiles();
	private MassChangeFileType massChange = new MassChangeFileType();
	
	ExtensionList extensionList = new ExtensionList();
	
	List <String> allExtensions = new ArrayList<>();
	
	JLabel title;
	
	JButton chooseFileButton = new TopPanelButton("Choose source folder");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JTextField extensions, newExtension;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JCheckBox includeSubfolders;
	
	JPanel titlePanel, buttonPanel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file;
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Folder: ");
	
	public MassChangeFiletypesPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == chooseFileButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                chosenFile.setText("Folder: " + file.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && file != null && !newExtension.getText().isBlank()) {
			
			int subfolders = 0;
			if(includeSubfolders.isSelected()) {
				subfolders = 1;
			}
			
			String newExtensionName = null;
			
			if(extensions.getText().isBlank() || extensions.getText().equals("*")) {
				allExtensions = fileExtension.getUniqueFileExtensions(file.getAbsolutePath());
			} else {
				
				List<String> validExtensions = new ArrayList<String>();
				List<String> inputExtensions = new ArrayList<String>();
				
				inputExtensions = Arrays.asList(extensions.getText().split(","));
			        List<String> extensionsList = extensionList.getAllExtensions();
			        for(String chosenExtension : inputExtensions) {
				        	if (extensionsList.contains(chosenExtension.trim())) {
				        		validExtensions.add(chosenExtension.trim());
				        	}
			        }
			        if (!(validExtensions.size() > 0)) {
			            System.out.println("Inputted file extensions are not valid.");
			            JOptionPane.showMessageDialog(null, "Inputted file extensions are not valid.", "Mass change File types", JOptionPane.ERROR_MESSAGE);
			        } else {
			        	allExtensions = validExtensions;
			        }
			}		
			
			String[] ext = allExtensions.toArray(new String[allExtensions.size()]);
			
			List<String> validExtensions = new ArrayList<String>();
			List<String> inputExtensions = new ArrayList<String>();
			
			inputExtensions = Arrays.asList(newExtension.getText().split(","));
	        List<String> extensionsList = extensionList.getAllExtensions();
	        for(String chosenExtension : inputExtensions) {
		        	if (extensionsList.contains(chosenExtension.trim())) {
		        		validExtensions.add(chosenExtension.trim());
		        	}
	        }
	        if (validExtensions.size() != 1) {
	            System.out.println("Inputted file extensions are not valid.");
	            JOptionPane.showMessageDialog(null, "Please input only 1 valid file extension for new extension", "Mass change File types", JOptionPane.ERROR_MESSAGE);
	        } else {
	        	newExtensionName = newExtension.getText();
	        }
	        if(newExtensionName != null) {
				List<String> fileStrings = new ArrayList<>();
				try {
					fileStrings = findFiles.findFiles(Paths.get(file.getAbsolutePath()), ext, subfolders);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				if(fileStrings.size() > 0) {
					for(String file : fileStrings) {
						File f = new File(file);
						File newFile = massChange.changeExtension(f,newExtensionName);
						f.renameTo(newFile);
					}
					
				}
				JOptionPane.showMessageDialog(null, "File extensions changed to " + newExtensionName + "\nin Folder " + file.getAbsolutePath(), "Mass change File types", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	        	JOptionPane.showMessageDialog(null, "No Files to change extension found!", "Mass change File types", JOptionPane.ERROR_MESSAGE);
	        }
			
			subfolders = 0;
			newExtension.setText("");
			extensions.setText("");
			file = null;
			error.setText("");
			includeSubfolders.setSelected(false);
			chosenFile.setText("Folder: ");
	        
		} else if(e.getSource() == startButton && file == null && !newExtension.getText().isBlank()) {
			error.setText("Choose a Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && newExtension.getText().isBlank()) {
			error.setText("Choose a File extension!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && newExtension.getText().isBlank()) {
			error.setText("Choose a File extension & Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		}
		
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
		
		JPanel chooseFilePanel = new JPanel();
		JPanel chosenFilePanel = new JPanel();
		JPanel extensionsPanel = new JPanel();
		JPanel includeSubfoldersPanel = new JPanel();
		JPanel newExtensionPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		chooseFileButton.addActionListener(this);
		startButton.addActionListener(this);
		
		chooseFileButton.setPreferredSize(buttonsize2);
		startButton.setPreferredSize(buttonsize);
		
		chooseFileButton.setFont(defaultFont);
		startButton.setFont(defaultFont);
		
		JLabel textboxLabel = new JLabel("Set extensions to include (Default: all filetypes)");
		textboxLabel.setLabelFor(extensions);
		extensions = new JTextField();
		extensions.setFont(defaultFont);
		extensions.addActionListener(this);

		chosenFile.setFont(defaultFont);
		textboxLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		JLabel textboxLabel2 = new JLabel("Set new extension");
		textboxLabel2.setLabelFor(newExtension);
		
		newExtension = new JTextField();
		newExtension.setFont(defaultFont);
		newExtension.addActionListener(this);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		extensionsPanel.setMaximumSize(buttonsize2);
		extensionsPanel.setLayout(new GridLayout(0,3));
		extensionsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		extensionsPanel.add(textboxLabel);
		extensionsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		extensionsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		extensionsPanel.add(extensions);
		
		includeSubfolders = new JCheckBox("Include Subfolders");
		includeSubfolders.setFont(defaultFont);
		includeSubfolders.setSelected(false);
		includeSubfolders.addActionListener(this);
		
		includeSubfoldersPanel.add(includeSubfolders);
		
		newExtensionPanel.setMaximumSize(buttonsize2);
		newExtensionPanel.setLayout(new GridLayout(0,3));
		newExtensionPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		newExtensionPanel.add(textboxLabel2);
		newExtensionPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		newExtensionPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		newExtensionPanel.add(newExtension);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(extensionsPanel);
		bPanel.add(includeSubfoldersPanel);
		bPanel.add(newExtensionPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
