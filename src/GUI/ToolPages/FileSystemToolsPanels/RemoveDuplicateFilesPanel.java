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

import javax.swing.*;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import Utilities.RemoveDuplicateFiles;

import java.util.List;

public class RemoveDuplicateFilesPanel extends ContentPanelBase implements ActionListener {

	RemoveDuplicateFiles removeDuplicateFiles = new RemoveDuplicateFiles();
	
	JLabel title;
	JButton chooseFileButton = new TopPanelButton("Choose source folder");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JCheckBox includeSubfolders;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JPanel titlePanel, buttonPanel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file;
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Folder: ");
	
	public RemoveDuplicateFilesPanel(String titleText) {
		
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
			int subfolders = 0;
			if(includeSubfolders.isSelected()) {
				subfolders = 1;
			}
			
			List<String> outputString = removeDuplicateFiles.removeDuplicates(sourceString, subfolders);

			String outputText = "";
			
			for(String item: outputString) {
				outputText = outputText + item;
			}
			
			JOptionPane.showMessageDialog(null, outputText, "Delete Duplicate Files", JOptionPane.INFORMATION_MESSAGE);
			
			sourceString = null;
			subfolders = 0;
			file = null;
			error.setText("");
			includeSubfolders.setSelected(false);
			chosenFile.setText("Folder: ");
		} else if(e.getSource() == startButton && file == null) {
			error.setText("Choose a Folder!");
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
		
		JPanel chooseFilePanel = new JPanel();
		JPanel chosenFilePanel = new JPanel();
		JPanel includeSubfoldersPanel = new JPanel();
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

		chosenFile.setFont(defaultFont);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		includeSubfoldersPanel.add(includeSubfolders);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(includeSubfoldersPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
