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

import FIleSystemTools.ToolMenus.UnzipAllZipFilesInFolder;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class UnzipOrZipFilesPanel extends ContentPanelBase implements ActionListener {

	UnzipAllZipFilesInFolder unzipOrZip = new UnzipAllZipFilesInFolder();
	
	JLabel title;
	
	TitledBorder moveOrCopyLabel;
	
	JButton chooseSourceFolderButton = new TopPanelButton("Choose source folder");
	JButton chooseDestinationFolderButton = new TopPanelButton("Choose destination folder");
	JButton startButton = new TopPanelButton("Start");
	
	JRadioButton zip, unzip;
	
	ButtonGroup zipOrUnzipGroup;
	
	JFileChooser fileChooser = new JFileChooser();
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(400,50);
	
	JPanel titlePanel, buttonPanel;
	
	JTextField newName;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,25);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File sourcefile, destinationfile;
	
	JLabel error = new JLabel();
	
	JLabel chosenSourceFolder = new JLabel("Source Folder: ");
	JLabel chosenDestinationFolder = new JLabel("Destination Folder: ");
	
	public UnzipOrZipFilesPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == zip) {
			
			newName.setEnabled(true);
			
		}
		
		if(e.getSource() == unzip) {
			
			newName.setText("");
			newName.setEnabled(false);
			
		}
		
		if(e.getSource() == chooseSourceFolderButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                sourcefile = fileChooser.getSelectedFile();
                chosenSourceFolder.setText("Source Folder: " + sourcefile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == chooseDestinationFolderButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                destinationfile = fileChooser.getSelectedFile();
                chosenDestinationFolder.setText("Destination Folder: " + destinationfile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && sourcefile != null && destinationfile  != null && ((zip.isSelected() && !newName.getText().isBlank()) || unzip.isSelected())) {
			
			if(sourcefile.getAbsolutePath().contains(".zip") && unzip.isSelected()) {
				unzipOrZip.extractZip(sourcefile.getAbsolutePath(), destinationfile.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Unzipped File " + sourcefile.getAbsolutePath() + "\nto destination Folder " + destinationfile.getAbsolutePath(), "Unzip File", JOptionPane.INFORMATION_MESSAGE);
			} else if(!sourcefile.getAbsolutePath().contains(".zip") && unzip.isSelected()) {
				JOptionPane.showMessageDialog(null, "Choose a zip file to unzip!", "Unzip File", JOptionPane.ERROR_MESSAGE);
			} else if(zip.isSelected()) {
				String zipDestination = destinationfile.getAbsolutePath() + "\\" + newName.getText() + ".zip";
				unzipOrZip.createZipFile(sourcefile.getAbsolutePath(), zipDestination);
				JOptionPane.showMessageDialog(null, "Zipped File " + sourcefile.getAbsolutePath() + "\nto destination " + zipDestination, "Zip File", JOptionPane.INFORMATION_MESSAGE);
			}
			
			error.setText("");
			sourcefile = null;
			destinationfile = null;
			newName.setText("");
			chosenSourceFolder.setText("Source Folder: ");
			chosenDestinationFolder.setText("Destination Folder: ");
			
		} else if(e.getSource() == startButton && sourcefile == null && destinationfile  != null) {
			if(zip.isSelected() && newName.getText().isBlank()) {
				error.setText("Choose Source Folder & new name for zip File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Choose Source Folder!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && sourcefile != null && destinationfile  == null) {
			if(zip.isSelected() && newName.getText().isBlank()) {
				error.setText("Choose Destination Folder & new name for zip File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Choose Destination Folder!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && sourcefile == null && destinationfile  == null) {
			if(zip.isSelected() && newName.getText().isBlank()) {
				error.setText("Choose Source & Destination Folder & new name for zip File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Choose Source & Destination Folder!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && sourcefile != null && destinationfile  != null) {
			if(zip.isSelected() && newName.getText().isBlank()) {
				error.setText("Choose a new name for zip File!");
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
		
		JPanel sourceFolderPanel = new JPanel();
		JPanel destinationFolderPanel = new JPanel();
		JPanel chosenSourceFolderPanel = new JPanel();
		JPanel chosenDestinationFolderPanel = new JPanel();
		JPanel zipHolderPanel = new JPanel();
		JPanel zipOrUnzipPanel = new JPanel();
		JPanel newNamePanel = new JPanel();
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
		
		moveOrCopyLabel = BorderFactory.createTitledBorder("Zip/Unzip:");
		moveOrCopyLabel.setTitleJustification(TitledBorder.CENTER);

		zip = new JRadioButton("Zip");
		zip.setSelected(true);
		zip.addActionListener(this);
		unzip = new JRadioButton("Unzip");
		unzip.addActionListener(this);
		
		zipOrUnzipGroup = new ButtonGroup();
		
		zipOrUnzipGroup.add(zip);
		zipOrUnzipGroup.add(unzip);

		sourceFolderPanel.setLayout(new FlowLayout());
		
		chosenSourceFolderPanel.add(chosenSourceFolder);
		sourceFolderPanel.add(chooseSourceFolderButton);
		
		chosenDestinationFolderPanel.add(chosenDestinationFolder);
		destinationFolderPanel.add(chooseDestinationFolderButton);
		
		zipHolderPanel.setLayout(new GridLayout(0,5));
		
		zipOrUnzipPanel.add(zip);
		zipOrUnzipPanel.add(unzip);
		zipOrUnzipPanel.setBorder(moveOrCopyLabel);
		
		zipHolderPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		zipHolderPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		zipHolderPanel.add(zipOrUnzipPanel);
		zipHolderPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		zipHolderPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		
		JLabel newNameLabel = new JLabel("Set new name for zip File (Used only for zipping)");
		newName = new JTextField();
		newNameLabel.setLabelFor(newName);
		newName.setFont(defaultFont);
		newName.addActionListener(this);
		
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
		bPanel.add(sourceFolderPanel);
		bPanel.add(chosenSourceFolderPanel);
		bPanel.add(destinationFolderPanel);
		bPanel.add(chosenDestinationFolderPanel);
		bPanel.add(zipHolderPanel);
		bPanel.add(newNamePanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
