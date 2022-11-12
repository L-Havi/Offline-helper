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
import Utilities.Lists.ExtensionList;
import Utilities.UserOutput.FindFiles;
import WindowsResources.CreateFolderPaths;
import WindowsResources.MoveToFileExtensionFolder;
import WindowsResources.SourceFolder;

public class SortFilesByExtensionPanel extends ContentPanelBase implements ActionListener {

	private MoveToFileExtensionFolder moveToFileExtensionFolder = new MoveToFileExtensionFolder();

	private FileExtension fileExtension = new FileExtension();
	private CreateFolderPaths createFolderPaths = new CreateFolderPaths();
	private CreateFolder createFolder = new CreateFolder();
	private FindFiles findFiles = new FindFiles();
	
	ExtensionList extensionList = new ExtensionList();
	
	List <String> allExtensions = new ArrayList<>();
	
	JLabel title;
	
	JButton chooseFileButton = new TopPanelButton("Choose source folder");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JTextField extensions;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JPanel titlePanel, buttonPanel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file;
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Folder: ");
	
	public SortFilesByExtensionPanel(String titleText) {
		
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
		
		if(e.getSource() == startButton && file != null) {
			
			String sourceString = file.getAbsolutePath();
			
			String textFieldInput = extensions.getText();		
			
			if(textFieldInput.trim().length() == 0 || textFieldInput.trim().equals("*") || textFieldInput == null) {
				
				allExtensions = fileExtension.getUniqueFileExtensions(sourceString);
				
				List<String> path = new ArrayList<>();
				for(String extension : allExtensions) {
					String newPath = sourceString + "\\" + extension.toUpperCase();
					path.add(newPath);
				}
				createFolder.createFolders(path);
				for(String extension : allExtensions) {
					try {
						String[][] files = findFiles.findFilesByExtension(Paths.get(sourceString), extension);
				        moveToFileExtensionFolder.move(files);
				    } catch (IOException ex) {
				    	ex.printStackTrace();
				    }
				}
				
				JOptionPane.showMessageDialog(null, "Sorted chosen Files to own Folders in Folder:\n" + sourceString, "Sort Files by Extension", JOptionPane.INFORMATION_MESSAGE);
				
				sourceString = null;
				file = null;
				extensions.setText("");
				error.setText("");
				chosenFile.setText("Folder: ");
				
			} else {
				
				List<String> validExtensions = new ArrayList<String>();
				List<String> inputExtensions = new ArrayList<String>();
				
				inputExtensions = Arrays.asList(textFieldInput.split(","));
			        List<String> extensionsList = extensionList.getAllExtensions();
			        for(String chosenExtension : inputExtensions) {
				        	if (extensionsList.contains(chosenExtension.trim())) {
				        		validExtensions.add(chosenExtension.trim());
				        	}
			        }
			        if (!(validExtensions.size() > 0)) {
			            System.out.println("Inputted file extensions are not valid.");
			            JOptionPane.showMessageDialog(null, "Inputted file extensions are not valid.", "Sort Files by Extension", JOptionPane.ERROR_MESSAGE);
			        } else {
			        	allExtensions = validExtensions;
			        }
				
				List<String> path = new ArrayList<>();
				for(String extension : allExtensions) {
					String newPath = sourceString + "\\" + extension.toUpperCase();
					path.add(newPath);
				}

				if(path.size() > 0) {
					createFolder.createFolders(path);
					for(String extension : allExtensions) {
						try {
							String[][] files = findFiles.findFilesByExtension(Paths.get(sourceString), extension);
					        moveToFileExtensionFolder.move(files);
					    } catch (IOException ex) {
					    	ex.printStackTrace();
					    }
					}
					
					JOptionPane.showMessageDialog(null, "Sorted chosen Files to own Folders in Folder:\n" + sourceString, "Sort Files by Extension", JOptionPane.INFORMATION_MESSAGE);
					sourceString = null;
					file = null;
					extensions.setText("");
					error.setText("");
					chosenFile.setText("Folder: ");
				}
				
			}
			
		} else if(e.getSource() == startButton && file == null) {
			error.setText("Choose a Folder!");
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
		
		JLabel textboxLabel = new JLabel("Set extensions to sort (Default: all filetypes)");
		textboxLabel.setLabelFor(extensions);
		extensions = new JTextField();
		extensions.setFont(defaultFont);
		extensions.addActionListener(this);

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
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(extensionsPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
