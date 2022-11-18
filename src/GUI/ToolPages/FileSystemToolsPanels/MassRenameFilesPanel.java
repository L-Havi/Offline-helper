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

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import Utilities.CreateFolder;
import Utilities.FileExtension;
import Utilities.RenameFiles;
import Utilities.Lists.ExtensionList;
import Utilities.UserOutput.FindFiles;
import WindowsResources.CreateFolderPaths;

public class MassRenameFilesPanel extends ContentPanelBase implements ActionListener {

	private FindFiles findFiles = new FindFiles();
	private RenameFiles renameFiles = new RenameFiles();
	
	private FileExtension fileExtension = new FileExtension();
	
	ExtensionList extensionList = new ExtensionList();
	
	
	List <String> allExtensions = new ArrayList<>();
	
	JLabel title;
	
	JButton chooseFileButton = new TopPanelButton("Choose source folder");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JCheckBox includeSubfolders;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JPanel titlePanel, buttonPanel;
	
	JTextField extensions, newName;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file;
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Folder: ");
	
	public MassRenameFilesPanel(String titleText) {
		
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
		
		String name = newName.getText();
		
		if(e.getSource() == startButton && file != null && name != null) {
			
			int subfolders = 0;
			if(includeSubfolders.isSelected()) {
				subfolders = 1;
			}
			
			String[] extensionsArray = {"*"};
			
			if(!extensions.getText().isBlank()) {
				
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
			            JOptionPane.showMessageDialog(null, "Inputted file extensions are not valid.", "Rename Files in Folder", JOptionPane.ERROR_MESSAGE);
			        } else {
			        	allExtensions = validExtensions;
			        }
				
			        extensionsArray = allExtensions.toArray(new String[allExtensions.size()]);
			        
			} 
			
			if(extensionsArray.length > 0) {
				try {
					List<String> fileList = findFiles.findFiles(Paths.get(file.getAbsolutePath()), extensionsArray, subfolders);
					renameFiles.renameFiles(name, fileList);
					JOptionPane.showMessageDialog(null, "Successfully renamed Files to " + name + " in Folder " + file.getAbsolutePath(), "Rename Files in Folder", JOptionPane.INFORMATION_MESSAGE);
				}catch (IOException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Failed to rename Files to " + name + " in Folder " + file.getAbsolutePath(), "Rename Files in Folder", JOptionPane.ERROR_MESSAGE);
		        }
			}
			
			file = null;
			name = null;
			newName.setText("");
			error.setText("");
			extensions.setText("");
			chosenFile.setText("Folder: ");
			
		} else if(e.getSource() == startButton && file == null && name != null) {
			error.setText("Choose a Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && name == null) {
			error.setText("Choose a new name for Files!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && name == null) {
			error.setText("Choose a Folder & new name for Files!");
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
		title.setPreferredSize(new Dimension(600, 50));
		
		titlePanel.add(title);
		
		return titlePanel;
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		
		JPanel chooseFilePanel = new JPanel();
		JPanel chosenFilePanel = new JPanel();
		JPanel extensionsPanel = new JPanel();
		JPanel includeSubfoldersPanel = new JPanel();
		JPanel newNamePanel = new JPanel();
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
		
		includeSubfolders = new JCheckBox("Include Subfolders");
		includeSubfolders.setFont(defaultFont);
		includeSubfolders.setSelected(false);
		includeSubfolders.addActionListener(this);
		
		includeSubfoldersPanel.add(includeSubfolders);
		
		JLabel textboxLabel = new JLabel("Set extensions to sort (Default: all filetypes)");
		extensions = new JTextField();
		textboxLabel.setLabelFor(extensions);
		extensions.setFont(defaultFont);
		extensions.addActionListener(this);

		JLabel newNameLabel = new JLabel("Set new name for Files");
		newName = new JTextField();
		newNameLabel.setLabelFor(newName);
		newName.setFont(defaultFont);
		newName.addActionListener(this);
		
		chosenFile.setFont(defaultFont);
		textboxLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		extensionsPanel.setMaximumSize(buttonsize2);
		extensionsPanel.setLayout(new GridLayout(0,3));
		extensionsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		extensionsPanel.add(textboxLabel);
		extensionsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		extensionsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		extensionsPanel.add(extensions);
		
		newNamePanel.setMaximumSize(buttonsize2);
		newNamePanel.setLayout(new GridLayout(0,3));
		newNamePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		newNamePanel.add(newNameLabel);
		newNamePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		newNamePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		newNamePanel.add(newName);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(extensionsPanel);
		bPanel.add(includeSubfoldersPanel);
		bPanel.add(newNamePanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
