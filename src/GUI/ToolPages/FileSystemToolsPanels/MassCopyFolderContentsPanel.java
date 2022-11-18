package GUI.ToolPages.FileSystemToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import Utilities.UserOutput.ListDirectoryFiles;
import WindowsResources.CopyFilesToAnotherFolder;
import WindowsResources.MoveFilesToAnotherFolder;

public class MassCopyFolderContentsPanel extends ContentPanelBase implements ActionListener {

	private MoveFilesToAnotherFolder moveFilesToAnotherFolder = new MoveFilesToAnotherFolder();
	private CopyFilesToAnotherFolder copyFilesToAnotherFolder = new CopyFilesToAnotherFolder();
	private ListDirectoryFiles listDirectoryFiles = new ListDirectoryFiles();
	
	JLabel title;
	
	TitledBorder moveOrCopyLabel;
	
	JButton chooseSourceFolderButton = new TopPanelButton("Choose source folder");
	JButton chooseDestinationFolderButton = new TopPanelButton("Choose destination folder");
	JButton startButton = new TopPanelButton("Start");
	
	JRadioButton move, copy;
	
	ButtonGroup moveOrCopyGroup;
	
	JFileChooser fileChooser = new JFileChooser();
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(400,50);
	
	JPanel titlePanel, buttonPanel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,25);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File sourcefile, destinationfile;
	
	JLabel error = new JLabel();
	
	JLabel chosenSourceFolder = new JLabel("Source Folder: ");
	JLabel chosenDestinationFolder = new JLabel("Destination Folder: ");
	
	public MassCopyFolderContentsPanel(String titleText) {
		
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
                chosenSourceFolder.setText("Folder: " + sourcefile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == chooseDestinationFolderButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                destinationfile = fileChooser.getSelectedFile();
                chosenDestinationFolder.setText("Folder: " + destinationfile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && sourcefile != null && destinationfile  != null) {
			
			String sourceFolderString = sourcefile.getAbsolutePath();
			String destinationFolderString = destinationfile.getAbsolutePath();
			
			String[] sourceExtensions = new String[0];
			String[][] destinationFiles = new String[0][0];
			
			sourceExtensions = listDirectoryFiles.getDirectoryFiles(sourceFolderString);
			destinationFiles = listDirectoryFiles.getFolderPaths(sourceFolderString, destinationFolderString, sourceExtensions);
			
			if(move.isSelected()) {
				moveFilesToAnotherFolder.move(destinationFiles);
			} else{
				copyFilesToAnotherFolder.copy(destinationFiles);
			}
			
			sourcefile = null;
			destinationfile = null;
			error.setText("");
			chosenSourceFolder.setText("Source Folder: ");
			chosenDestinationFolder.setText("Destination Folder: ");
			
		} else if(e.getSource() == startButton && sourcefile == null && destinationfile  != null) {
			error.setText("Choose Source Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && sourcefile != null && destinationfile  == null) {
			error.setText("Choose Destination Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && sourcefile == null && destinationfile  == null) {
			error.setText("Choose Source & Destination Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
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
		
		JPanel sourceFolderPanel = new JPanel();
		JPanel chosenSourceFolderPanel = new JPanel();
		JPanel destinationFolderPanel = new JPanel();
		JPanel chosenDestinationFolderPanel = new JPanel();
		JPanel moveOrCopyPanel = new JPanel();
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
		chosenDestinationFolder.setFont(defaultFont);
		chosenSourceFolder.setFont(defaultFont);
		startButton.setFont(defaultFont);
		
		moveOrCopyLabel = BorderFactory.createTitledBorder("Move/copy:");
		moveOrCopyLabel.setTitleJustification(TitledBorder.CENTER);

		move = new JRadioButton("Move");
		move.setSelected(true);
		copy = new JRadioButton("Copy");
		
		moveOrCopyGroup = new ButtonGroup();
		
		moveOrCopyGroup.add(move);
		moveOrCopyGroup.add(copy);

		sourceFolderPanel.setLayout(new FlowLayout());
		
		chosenSourceFolderPanel.add(chosenSourceFolder);
		sourceFolderPanel.add(chooseSourceFolderButton);
		
		chosenDestinationFolderPanel.add(chosenDestinationFolder);
		destinationFolderPanel.add(chooseDestinationFolderButton);
		
		moveOrCopyPanel.add(move);
		moveOrCopyPanel.add(copy);
		moveOrCopyPanel.setBorder(moveOrCopyLabel);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(sourceFolderPanel);
		bPanel.add(chosenSourceFolderPanel);
		bPanel.add(destinationFolderPanel);
		bPanel.add(chosenDestinationFolderPanel);
		bPanel.add(moveOrCopyPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
	
}
