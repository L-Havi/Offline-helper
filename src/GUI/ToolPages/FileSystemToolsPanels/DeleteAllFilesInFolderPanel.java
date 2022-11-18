package GUI.ToolPages.FileSystemToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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

import FIleSystemTools.ToolMenus.DeleteAllFilesInFolder;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import Utilities.FileExtension;
import Utilities.Lists.ExtensionList;
import Utilities.UserOutput.FindFiles;

public class DeleteAllFilesInFolderPanel extends ContentPanelBase {

	private DeleteAllFilesInFolder deleteFiles = new DeleteAllFilesInFolder();
	private FileExtension fileExtension = new FileExtension();
	private FindFiles findFiles = new FindFiles();
	
	ExtensionList extensionList = new ExtensionList();
	
	List <String> allExtensions = new ArrayList<>();
	
	JLabel title;
	
	JButton chooseSourceFolderButton = new TopPanelButton("Choose source folder");
	JButton chooseDestinationFolderButton = new TopPanelButton("Choose txt-file location");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JTextField extensions, newNameTextField;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JCheckBox includeSubfolders;
	
	JPanel titlePanel, buttonPanel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File sourcefile, destinationfile;
	
	JLabel error = new JLabel();
	
	JLabel chosenSourceFile = new JLabel("Source Folder: ");
	JLabel chosenDestinationFile = new JLabel("Save location for txt-file: ");
	
	public DeleteAllFilesInFolderPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == chooseSourceFolderButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                sourcefile = fileChooser.getSelectedFile();
                chosenSourceFile.setText("Source Folder: " + sourcefile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && sourcefile != null) {
			
			int subfolders = 0;
			if(includeSubfolders.isSelected()) {
				subfolders = 1;
			}
			
			if(extensions.getText().isBlank() || extensions.getText().equals("*")) {
				allExtensions = fileExtension.getUniqueFileExtensions(sourcefile.getAbsolutePath());
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
			            JOptionPane.showMessageDialog(null, "Inputted file extensions are not valid.", "Delete Files from Folder", JOptionPane.ERROR_MESSAGE);
			        } else {
			        	allExtensions = validExtensions;
			        }
			}
			
			String[] ext = allExtensions.toArray(new String[allExtensions.size()]);
			
			List<String> fileStrings = new ArrayList<>();

			try {
				fileStrings = findFiles.findFiles(Paths.get(sourcefile.getAbsolutePath()), ext, subfolders);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			int reply = JOptionPane.showConfirmDialog(null, "You are about to delete " + fileStrings.size() + " files \nfrom Folder " + sourcefile.getAbsolutePath() + "\n\n Are you sure you want to delete Files?", "Delete Files from Folder", JOptionPane.YES_NO_OPTION);
			
			if(fileStrings.size() > 0 && (reply == JOptionPane.YES_OPTION )) {
				deleteFiles.delete(sourcefile.getAbsolutePath(), fileStrings, subfolders);
				JOptionPane.showMessageDialog(null, "Successfully deleted "  + fileStrings.size() + " files \nfrom Folder " + sourcefile.getAbsolutePath(), "Delete Files from Folder", JOptionPane.INFORMATION_MESSAGE);
			}
			
			error.setText("");
			sourcefile = null;
			extensions.setText("");
			includeSubfolders.setSelected(false);
			chosenSourceFile.setText("Source Folder: ");
			
		} else if(e.getSource() == startButton && sourcefile == null) {
			error.setText("Choose Source Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} 
		
	}
	
	private JPanel getTitlePanel(String titleText) {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(600,50));
		
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
		
		JPanel chooseSourceFolderPanel = new JPanel();
		JPanel chosenSourceFolderPanel = new JPanel();
		JPanel extensionsPanel = new JPanel();
		JPanel includeSubfoldersPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		chooseSourceFolderButton.addActionListener(this);
		startButton.addActionListener(this);
		
		chooseSourceFolderButton.setPreferredSize(buttonsize2);
		startButton.setPreferredSize(buttonsize);
		
		chooseSourceFolderButton.setFont(defaultFont);
		startButton.setFont(defaultFont);
		
		JLabel textboxLabel = new JLabel("Set extensions to include (Default: all filetypes)");
		textboxLabel.setLabelFor(extensions);
		extensions = new JTextField();
		extensions.setFont(defaultFont);
		extensions.addActionListener(this);

		chosenSourceFile.setFont(defaultFont);
		chosenDestinationFile.setFont(defaultFont);
		textboxLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		chooseSourceFolderPanel.add(chooseSourceFolderButton);
		chosenSourceFolderPanel.add(chosenSourceFile);
		
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
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseSourceFolderPanel);
		bPanel.add(chosenSourceFolderPanel);
		bPanel.add(extensionsPanel);
		bPanel.add(includeSubfoldersPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
