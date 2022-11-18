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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import Utilities.FileCountAndSize;
import Utilities.FileExtension;
import Utilities.Lists.ExtensionList;
import Utilities.UserOutput.FindFiles;
import Utilities.UserOutput.OutputPaths;

public class GetFolderSizeAndFileCountPanel extends ContentPanelBase implements ActionListener {

	private FileCountAndSize fileCountAndSize = new FileCountAndSize();
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
	
	public GetFolderSizeAndFileCountPanel(String titleText) {
		
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
		
		if(e.getSource() == chooseDestinationFolderButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                destinationfile = fileChooser.getSelectedFile();
                chosenDestinationFile.setText("Destination Folder: " + destinationfile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && sourcefile != null && destinationfile  != null && !newNameTextField.getText().isBlank()) {
			
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
			            JOptionPane.showMessageDialog(null, "Inputted file extensions are not valid.", "Get Folder Size & File count", JOptionPane.ERROR_MESSAGE);
			        } else {
			        	allExtensions = validExtensions;
			        }
			}
			
			String[] ext = allExtensions.toArray(new String[allExtensions.size()]);
			
			List<String> fileStrings = new ArrayList<>();
			boolean save = true;
			
			try {
				fileStrings = findFiles.findFiles(Paths.get(sourcefile.getAbsolutePath()), ext, subfolders);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if(fileStrings.size() > 0) {
				try {
					fileCountAndSize.getFileCountAndSize(fileStrings, save, sourcefile.getAbsolutePath(), destinationfile.getAbsolutePath(), newNameTextField.getText());
		            JOptionPane.showMessageDialog(null, "Successfully created txt-file containing \n" + sourcefile.getAbsolutePath() + " size & File count", "Get Folder Size & File count", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
	            JOptionPane.showMessageDialog(null, "No path found in Folder " + sourcefile.getAbsolutePath(), "Get Folder Size & File count", JOptionPane.ERROR_MESSAGE);
			}
			
			
			error.setText("");
			sourcefile = null;
			destinationfile = null;
			extensions.setText("");
			includeSubfolders.setSelected(false);
			newNameTextField.setText("");
			chosenSourceFile.setText("Source Folder: ");
			chosenDestinationFile.setText("Destination Folder: ");
			
		} else if(e.getSource() == startButton && sourcefile == null && destinationfile  != null) {
			if(newNameTextField.getText().isBlank()) {
				error.setText("Choose Source Folder & new name for txt-File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Choose Source Folder!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && sourcefile != null && destinationfile  == null) {
			if(newNameTextField.getText().isBlank()) {
				error.setText("Choose Destination Folder & new name for txt-File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Choose Destination Folder!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && sourcefile == null && destinationfile  == null) {
			if(newNameTextField.getText().isBlank()) {
				error.setText("Choose Source & Destination Folder & new name for txt-File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Choose Source & Destination Folder!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		}
		
	}
	
	private JPanel getTitlePanel(String titleText) {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		
		title = new JLabel(titleText);
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		titlePanel.add(title);
		
		return titlePanel;
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		
		JPanel chooseSourceFolderPanel = new JPanel();
		JPanel chosenSourceFolderPanel = new JPanel();
		JPanel chooseDestinationFolderPanel = new JPanel();
		JPanel chosenDestinationFolderPanel = new JPanel();
		JPanel extensionsPanel = new JPanel();
		JPanel includeSubfoldersPanel = new JPanel();
		JPanel newName = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		chooseSourceFolderButton.addActionListener(this);
		chooseDestinationFolderButton.addActionListener(this);
		startButton.addActionListener(this);
		
		chooseSourceFolderButton.setPreferredSize(buttonsize2);
		chooseDestinationFolderButton.setPreferredSize(buttonsize2);
		startButton.setPreferredSize(buttonsize);
		
		chooseSourceFolderButton.setFont(defaultFont);
		chooseDestinationFolderButton.setFont(defaultFont);
		startButton.setFont(defaultFont);
		
		JLabel textboxLabel = new JLabel("Set extensions to include (Default: all filetypes)");
		textboxLabel.setLabelFor(extensions);
		extensions = new JTextField();
		extensions.setFont(defaultFont);
		extensions.addActionListener(this);

		chosenSourceFile.setFont(defaultFont);
		chosenDestinationFile.setFont(defaultFont);
		textboxLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		JLabel textboxLabel2 = new JLabel("Set a name for txt-file");
		textboxLabel2.setLabelFor(newNameTextField);
		
		newNameTextField = new JTextField();
		newNameTextField.setFont(defaultFont);
		newNameTextField.addActionListener(this);
		
		chooseSourceFolderPanel.add(chooseSourceFolderButton);
		chosenSourceFolderPanel.add(chosenSourceFile);
		
		chooseDestinationFolderPanel.add(chooseDestinationFolderButton);
		chosenDestinationFolderPanel.add(chosenDestinationFile);
		
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
		
		newName.setMaximumSize(buttonsize2);
		newName.setLayout(new GridLayout(0,3));
		newName.add(Box.createRigidArea(new Dimension(0, 1)));
		newName.add(textboxLabel2);
		newName.add(Box.createRigidArea(new Dimension(0, 1)));
		newName.add(Box.createRigidArea(new Dimension(0, 1)));
		newName.add(newNameTextField);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseSourceFolderPanel);
		bPanel.add(chosenSourceFolderPanel);
		bPanel.add(chooseDestinationFolderPanel);
		bPanel.add(chosenDestinationFolderPanel);
		bPanel.add(extensionsPanel);
		bPanel.add(includeSubfoldersPanel);
		bPanel.add(newName);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
	
}
