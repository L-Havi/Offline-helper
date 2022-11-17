package GUI.ToolPages.PDFToolsPanels;

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
import javax.swing.filechooser.FileFilter;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import PdfTools.ActionChooseMenus.MergePdfAction;

public class MergePDFFilesPanel extends ContentPanelBase {

	MergePdfAction mergepdf = new MergePdfAction();
	
	JLabel title;
	
	JButton chooseFileButton = new TopPanelButton("Choose Output folder");
	JButton choosePdfsButton = new TopPanelButton("Choose merged PDF Files");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JPanel titlePanel, buttonPanel;
	
	JTextField newName;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file;
	
	File[] pdfFilesArray;
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Output Folder: ");
	JLabel pdfFiles = new JLabel("PDF Files: ");
	
	
	public MergePDFFilesPanel(String titleText) {
		
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
            	chosenFile.setText("Output Folder: " + file.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == choosePdfsButton) {
			fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser2.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
                }

                @Override
                public String getDescription() {
                    return "PDF Files (*.pdf)";
                }});
			fileChooser2.setMultiSelectionEnabled(true);
			int option = fileChooser2.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	pdfFilesArray = fileChooser2.getSelectedFiles();
            	String choice = "";
            	int counter = 1;
            	for(File pdf : pdfFilesArray) {
            		if(counter == 1) {
            			choice += pdf.getName();
            		} else {
            			choice += ", " + pdf.getName();
            		}
            		
            		counter++;
            	}
            	pdfFiles.setText("PDF Files: " + choice);
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		String name = newName.getText();
		
		if(e.getSource() == startButton && file != null && (pdfFilesArray.length >= 2) && name != null) {
			
			String destination = file.getAbsolutePath() + "\\" + name + ".pdf";
			
			try {
				mergepdf.merge(destination, pdfFilesArray);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			File confirm = new File(destination);
			if(confirm.exists()) {
				JOptionPane.showMessageDialog(null, "Successfully created merged PDF " + destination, "Merge PDF Files", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create merged PDF " + destination, "Merge PDF Files", JOptionPane.ERROR_MESSAGE);
			}
			
			chosenFile.setText("Output Folder: ");
			pdfFiles.setText("PDF Files: ");
			newName.setText("");
			error.setText("");
			file = null;
			pdfFilesArray = null;
			name = null;
			
		} else if(e.getSource() == startButton && file == null && (pdfFilesArray.length >= 2) && name != null) {
			error.setText("Choose Output Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && (pdfFilesArray.length < 2) && name != null) {
			error.setText("Choose at least 2 PDFs to merge!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && (pdfFilesArray.length >= 2) && name == null) {
			error.setText("Choose a new name for output File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && (pdfFilesArray.length < 2) && name != null) {
			error.setText("Choose Output Folder & at least 2 PDFs to merge!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && (pdfFilesArray.length < 2) && name == null) {
			error.setText("Choose new name for output File & at least 2 PDFs to merge!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && (pdfFilesArray.length >= 2) && name == null) {
			error.setText("Choose output Folder & new name for File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && (pdfFilesArray.length < 2) && name == null) {
			error.setText("Choose output Folder, at least 2 PDFs to merge & new name for File!");
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
		JPanel choosePdfsPanel = new JPanel();
		JPanel chosenPdfsPanel = new JPanel();
		JPanel newNamePanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		chooseFileButton.addActionListener(this);
		choosePdfsButton.addActionListener(this);
		startButton.addActionListener(this);
		
		chooseFileButton.setPreferredSize(buttonsize2);
		choosePdfsButton.setPreferredSize(buttonsize2);
		startButton.setPreferredSize(buttonsize);
		
		chooseFileButton.setFont(defaultFont);
		choosePdfsButton.setFont(defaultFont);
		startButton.setFont(defaultFont);

		JLabel newNameLabel = new JLabel("Set name for output File");
		newName = new JTextField();
		newNameLabel.setLabelFor(newName);
		newName.setFont(defaultFont);
		newName.addActionListener(this);
		
		chosenFile.setFont(defaultFont);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		pdfFiles.setFont(defaultFont);
		
		choosePdfsPanel.add(choosePdfsButton);
		chosenPdfsPanel.add(pdfFiles);
		
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
		bPanel.add(choosePdfsPanel);
		bPanel.add(chosenPdfsPanel);
		bPanel.add(newNamePanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
	
}
