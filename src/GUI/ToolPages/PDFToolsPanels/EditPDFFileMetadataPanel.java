package GUI.ToolPages.PDFToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

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
import PdfTools.ActionChooseMenus.EditPdfMetadata;
import PdfTools.ActionChooseMenus.ExtractPdfMetadata;

public class EditPDFFileMetadataPanel extends ContentPanelBase {

	ExtractPdfMetadata extMetadata = new ExtractPdfMetadata();
	
	EditPdfMetadata editmetadata = new EditPdfMetadata();
	
	JButton saveButton = new TopPanelButton("Save");
	
	JLabel title;

	String[] info = new String[6];
	
	JButton choosePdfsButton = new TopPanelButton("Choose PDF File");
	
	JFileChooser fileChooser = new JFileChooser();
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,30);
	
	JPanel titlePanel, buttonPanel;
	
	JTextField author, creationDate, keywords, modifyDate, subject, titleField;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file, pdfFile;
	
	JLabel error = new JLabel();
	
	JLabel pdfFileLabel = new JLabel("PDF File: ");
	JLabel authorLabel = new JLabel("Author: ");
	JLabel creationDateLabel = new JLabel("Creation Date: ");
	JLabel keywordsLabel = new JLabel("Keywords: ");
	JLabel modifyDateLabel = new JLabel("Modify Date: ");
	JLabel subjectLabel = new JLabel("Subject: ");
	JLabel titleLabel = new JLabel("Title: ");
	
	public EditPDFFileMetadataPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == choosePdfsButton) {
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
                }

                @Override
                public String getDescription() {
                    return "PDF Files (*.pdf)";
                }});
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	if(fileChooser.getSelectedFile().getName().endsWith(".pdf")) {
            		pdfFile = fileChooser.getSelectedFile();
            		pdfFileLabel.setText("PDF File: " + pdfFile.getAbsolutePath());
            		
            		info = extMetadata.getPDFFileInformationArray(pdfFile.getAbsolutePath());
            		
            		if(info[0] != null) {
            			author.setText(info[0]);
            		}
            		if(info[1] != null) {
            			creationDate.setText(info[1]);
            		}
            		if(info[2] != null) {
            			keywords.setText(info[2]);
            		}
            		if(info[3] != null) {
            			modifyDate.setText(info[3]);
            		}
            		if(info[4] != null) {
            			subject.setText(info[4]);
            		}
            		if(info[5] != null) {
            			titleField.setText(info[5]);
            		}
            	} else {
            		error.setText("Only PDF Files are accepted!");
    				error.setFont(defaultFont);
    				error.setForeground(Color.red);
            	}
             }else{
                System.out.println("Open command canceled");
             }
            
		}
		
		if(e.getSource() == saveButton && pdfFile != null) {
			
			String[] newInfo = new String[4];
			
			newInfo[0] = author.getText();
			newInfo[1] = keywords.getText();
			newInfo[2] = subject.getText();
			newInfo[3] = titleField.getText();
			
			if(newInfo[0].equals(info[0]) && newInfo[1].equals(info[2]) && newInfo[2].equals(info[4]) && newInfo[3].equals(info[5])) {
				JOptionPane.showMessageDialog(null, "No data changed!", "Edit PDF metadata", JOptionPane.INFORMATION_MESSAGE);
			} else {
				editmetadata.setPDFFileInformation(pdfFile.getAbsolutePath(), newInfo[0], newInfo[1], newInfo[2], newInfo[3]);
				JOptionPane.showMessageDialog(null, "Succesfully edited PDF metadata:\n\nAuthor: " + newInfo[0] + "\nKeywords:" + newInfo[1] + "\nSubject:" + newInfo[2] + "\nTitle:" + newInfo[3], "Edit PDF metadata", JOptionPane.INFORMATION_MESSAGE);
			}
			
			pdfFile = null;
			pdfFileLabel.setText("PDF File: ");
			error.setText("");
			author.setText("");
			creationDate.setText("");
			keywords.setText("");
			modifyDate.setText("");
			subject.setText("");
			titleField.setText("");
			
		} else if(e.getSource() == saveButton && pdfFile == null) {
    		error.setText("Choose a PDF File to edit!");
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
		JPanel authorPanel = new JPanel();
		JPanel creationDatePanel = new JPanel();
		JPanel keywordsPanel = new JPanel();
		JPanel modifyDatePanel = new JPanel();
		JPanel subjectPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		bPanel.setLayout(new GridLayout(0,1));
		
		choosePdfsButton.addActionListener(this);
		choosePdfsButton.setPreferredSize(buttonsize2);
		choosePdfsButton.setFont(defaultFont);

		saveButton.addActionListener(this);
		saveButton.setPreferredSize(buttonsize2);
		saveButton.setFont(defaultFont);
		
		savePanel.add(saveButton);
		
		pdfFileLabel.setFont(defaultFont);
		
		chooseFilePanel.add(choosePdfsButton);
		chosenFilePanel.add(pdfFileLabel);
		
		author = new JTextField();
		authorLabel.setLabelFor(author);
		author.setFont(defaultFont);
		
		creationDate = new JTextField();
		creationDateLabel.setLabelFor(creationDate);
		creationDate.setEnabled(false);
		creationDate.setFont(defaultFont);
		
		keywords = new JTextField();
		keywordsLabel.setLabelFor(keywords);
		keywords.setFont(defaultFont);
		
		modifyDate = new JTextField();
		modifyDateLabel.setLabelFor(modifyDate);
		modifyDate.setEnabled(false);
		modifyDate.setFont(defaultFont);
		
		subject = new JTextField();
		subjectLabel.setLabelFor(subject);
		subject.setFont(defaultFont);
		
		titleField = new JTextField();
		titleLabel.setLabelFor(titleField);
		titleField.setFont(defaultFont);
		
		authorPanel.setMaximumSize(buttonsize2);
		authorPanel.setLayout(new GridLayout(0,3));
		authorPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		authorPanel.add(authorLabel);
		authorPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		authorPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		authorPanel.add(author);
		
		creationDatePanel.setMaximumSize(buttonsize2);
		creationDatePanel.setLayout(new GridLayout(0,3));
		creationDatePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		creationDatePanel.add(creationDateLabel);
		creationDatePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		creationDatePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		creationDatePanel.add(creationDate);
		
		keywordsPanel.setMaximumSize(buttonsize2);
		keywordsPanel.setLayout(new GridLayout(0,3));
		keywordsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		keywordsPanel.add(keywordsLabel);
		keywordsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		keywordsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		keywordsPanel.add(keywords);
		
		modifyDatePanel.setMaximumSize(buttonsize2);
		modifyDatePanel.setLayout(new GridLayout(0,3));
		modifyDatePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		modifyDatePanel.add(modifyDateLabel);
		modifyDatePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		modifyDatePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		modifyDatePanel.add(modifyDate);
		
		subjectPanel.setMaximumSize(buttonsize2);
		subjectPanel.setLayout(new GridLayout(0,3));
		subjectPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		subjectPanel.add(subjectLabel);
		subjectPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		subjectPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		subjectPanel.add(subject);
		
		titlePanel.setMaximumSize(buttonsize2);
		titlePanel.setLayout(new GridLayout(0,3));
		titlePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		titlePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		titlePanel.add(titleField);
		
		errorPanel.add(error);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(authorPanel);
		bPanel.add(creationDatePanel);
		bPanel.add(keywordsPanel);
		bPanel.add(modifyDatePanel);
		bPanel.add(subjectPanel);
		bPanel.add(titlePanel);
		bPanel.add(errorPanel);
		bPanel.add(savePanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		
		return bPanel;
	}
	
}
