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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import PdfTools.ActionChooseMenus.ConvertPdfAction;

public class ConvertPDFFileToAnotherFormatPanel extends ContentPanelBase {

	ConvertPdfAction convertAction = new ConvertPdfAction();
	
	JLabel title;
	
	String[] extensionStrings = { "html", "jpeg", "jpg", "gif", "tiff", "png", "docx" };
	
	JButton chooseFileButton = new TopPanelButton("Choose Output folder");
	JButton choosePdfsButton = new TopPanelButton("Choose PDF File");
	JButton startButton = new TopPanelButton("Start");
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JPanel titlePanel, buttonPanel;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	File file, pdfFile;
	
	JComboBox convertFormat = new JComboBox(extensionStrings);
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("Output Folder: ");
	JLabel pdfFiles = new JLabel("PDF File: ");
	JLabel convertLabel = new JLabel("New Format: ");
	
	public ConvertPDFFileToAnotherFormatPanel(String titleText) {
		
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
		
		if(e.getSource() == startButton && file != null && pdfFile != null) {
			
			String chosenConvert = (String)convertFormat.getSelectedItem();
			
			if(chosenConvert.equals("html")) {
				try {
					convertAction.generateHTMLFromPDF(pdfFile.getAbsolutePath(), pdfFile.getName(), file.getAbsolutePath());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else if(chosenConvert.equals("jpeg") || chosenConvert.equals("jpg") || chosenConvert.equals("gif") || chosenConvert.equals("tiff") || chosenConvert.equals("png")){
				try {
					convertAction.generateImageFromPDF(pdfFile.getAbsolutePath(), chosenConvert, pdfFile.getName(), file.getAbsolutePath());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}else {
				try {
					convertAction.generateDocxFromPDF(pdfFile.getAbsolutePath(), pdfFile.getName(), file.getAbsolutePath());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			chosenFile.setText("Output Folder: ");
			pdfFiles.setText("PDF File: ");
			error.setText("");
			file = null;
			pdfFile = null;
			
		} else if(e.getSource() == startButton && file == null && pdfFile != null) {
			error.setText("Choose Output Folder!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file != null && pdfFile == null) {
			error.setText("Choose a PDF to convert!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && pdfFile == null) {
			error.setText("Choose Output Folder & a PDF to convert!");
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
		JPanel chooseFormatPanel = new JPanel();
		JPanel chosenFormatPanel = new JPanel();
		JPanel choosePdfsPanel = new JPanel();
		JPanel chosenPdfsPanel = new JPanel();
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
		
		convertFormat.setSelectedIndex(0);
		convertFormat.setPreferredSize(buttonsize2);
		convertFormat.addActionListener(this);
		
		convertLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		chosenFormatPanel.add(convertLabel);
		chooseFormatPanel.add(convertFormat);
		
		chosenFile.setFont(defaultFont);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		pdfFiles.setFont(defaultFont);
		
		choosePdfsPanel.add(choosePdfsButton);
		chosenPdfsPanel.add(pdfFiles);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));

		bPanel.add(choosePdfsPanel);
		bPanel.add(chosenPdfsPanel);
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(chosenFormatPanel);
		bPanel.add(chooseFormatPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
}
