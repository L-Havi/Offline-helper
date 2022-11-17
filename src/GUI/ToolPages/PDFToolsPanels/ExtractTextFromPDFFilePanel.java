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

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import PdfTools.ActionChooseMenus.ExtractTextFromPdfAction;
import Utilities.ExtractPageNumbersFromString;

public class ExtractTextFromPDFFilePanel extends ContentPanelBase {

	ExtractPageNumbersFromString extract = new ExtractPageNumbersFromString();
	
	ExtractTextFromPdfAction extText = new ExtractTextFromPdfAction();
	
	JLabel title;
	
	JButton chooseFileButton = new TopPanelButton("Choose Output folder");
	JButton choosePdfsButton = new TopPanelButton("Choose PDF File");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JPanel titlePanel, buttonPanel;
	
	JTextField newName, pages;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file, pdfFile;
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Output Folder: ");
	JLabel pdfFiles = new JLabel("PDF File: ");
	
	public ExtractTextFromPDFFilePanel(String titleText) {
		
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
			int option = fileChooser2.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	if(fileChooser2.getSelectedFile().getName().endsWith(".pdf")) {
            		pdfFile = fileChooser2.getSelectedFile();
            		pdfFiles.setText("PDF File: " + pdfFile.getAbsolutePath());
            	} else {
            		error.setText("Only PDF Files are accepted!");
    				error.setFont(defaultFont);
    				error.setForeground(Color.red);
            	}
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		String name = newName.getText();
		
		if(e.getSource() == startButton && file != null && pdfFile != null && name != null) {
			
			int[] pagesArray = new int[0];
			
			if(!pages.getText().isBlank()) {
				pagesArray = extract.getPageArray(pages.getText());
			}	
			
			if(pagesArray == null && (pages.getText() != null || !pages.getText().isBlank())) {
				error.setText("Input valid page numbers! e.g. 1 or 1-4");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			} else {
				try {
					String saveFile = file + "\\" + name + ".txt";
					extText.extract(pdfFile.getAbsolutePath(), saveFile, pagesArray);
					JOptionPane.showMessageDialog(null, "Successfully extracted text from PDF to text File in " + file.getAbsolutePath(), "Extract Text from PDF File", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Failed to extract text from PDF to text File in " + file.getAbsolutePath(), "Extract Text from PDF File", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
				chosenFile.setText("Output Folder: ");
				pdfFiles.setText("PDF File: ");
				newName.setText("");
				pages.setText("");
				error.setText("");
				file = null;
				pdfFile = null;
				name = null;
			}
			
		} else if(e.getSource() == startButton && file == null && pdfFile != null && name != null) {
			error.setText("Choose Output Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && pdfFile == null && name != null) {
			error.setText("Choose a PDF to split!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && pdfFile != null && name == null) {
			error.setText("Choose a new name for output Files!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && pdfFile == null && name != null) {
			error.setText("Choose Output Folder & a PDF to split!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && pdfFile == null && name == null) {
			error.setText("Choose new name for output Files & a PDF to split!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && pdfFile != null && name == null) {
			error.setText("Choose output Folder & new name for Files!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && pdfFile == null && name == null) {
			error.setText("Choose output Folder, a PDF to split & new name for Files!");
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
		JPanel pagesPanel = new JPanel();
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
		
		JLabel pagesLabel = new JLabel("Choose included Pages: (Optional, default: All Pages)");
		pages = new JTextField();
		pagesLabel.setLabelFor(pages);
		pages.setFont(defaultFont);
		pages.addActionListener(this);
		
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
		
		pagesPanel.setMaximumSize(buttonsize2);
		pagesPanel.setLayout(new GridLayout(0,3));
		pagesPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		pagesPanel.add(pagesLabel);
		pagesPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		pagesPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		pagesPanel.add(pages);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(choosePdfsPanel);
		bPanel.add(chosenPdfsPanel);
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(newNamePanel);
		bPanel.add(pagesPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
