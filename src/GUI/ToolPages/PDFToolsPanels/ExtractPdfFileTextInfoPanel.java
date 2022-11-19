package GUI.ToolPages.PDFToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import Cryptography.PasswordActions.GeneratePassword;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import PdfTools.ActionChooseMenus.ExtractPdfFileTextInfo;

public class ExtractPdfFileTextInfoPanel extends ContentPanelBase implements ActionListener {
	
	ExtractPdfFileTextInfo extInfo = new ExtractPdfFileTextInfo();
	
	JLabel title;	
	
	TitledBorder themeLabel;
	
	JPanel titlePanel, buttonPanel;
	JButton startButton = new TopPanelButton("Extract");
	
	JButton chooseFileButton = new TopPanelButton("Choose PDF File");
	
	JCheckBox includeLowercase, includeUppercase, includeNumbers, includeSpecial;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JTextField password;
	
	JFileChooser fileChooser = new JFileChooser();
	
	File file;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,30);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	JLabel error = new JLabel();
	
	JLabel chosenFile = new JLabel("PDF File: ");
	
	public ExtractPdfFileTextInfoPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == chooseFileButton) {
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
            		file = fileChooser.getSelectedFile();
            		chosenFile.setText("PDF File: " + file.getAbsolutePath());
            	} else {
            		error.setText("Only PDF Files are accepted!");
    				error.setFont(defaultFont);
    				error.setForeground(Color.red);
            	}
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && file != null && (includeLowercase.isSelected() || includeUppercase.isSelected() || includeNumbers.isSelected() || includeSpecial.isSelected())) {
			
			int includeLowercaseInt = 1;
			int includeUppercaseInt = 1;
			int includeNumbersInt = 1;
			int includeSpecialInt = 1;
			
			if(includeLowercase.isSelected() == false) {
				includeLowercaseInt = 0;
			}
			if(includeUppercase.isSelected() == false) {
				includeUppercaseInt = 0;
			}
			if(includeNumbers.isSelected() == false) {
				includeNumbersInt = 0;
			}
			if(includeSpecial.isSelected() == false) {
				includeSpecialInt = 0;
			}
			
			String txtFileLocation = extInfo.getInfo(file.getAbsolutePath(), includeLowercaseInt, includeUppercaseInt, includeNumbersInt, includeSpecialInt);
			
			File test = new File(txtFileLocation);
			
			if(test.exists()) {
				JOptionPane.showMessageDialog(null, "Successfully created a text File " + txtFileLocation + " containing text data from " + file.getAbsolutePath(), "Extract Text data from PDF File", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to extract text data from PDF to text File in " + file.getAbsolutePath(), "Extract Text data from PDF File", JOptionPane.ERROR_MESSAGE);
			}
			
			file = null;
			chosenFile.setText("PDF File: ");
			error.setText("");
			
		} else if(e.getSource() == startButton && file != null && !(includeLowercase.isSelected() || includeUppercase.isSelected() || includeNumbers.isSelected() || includeSpecial.isSelected())) {
			error.setText("Choose at least 1 data extraction option!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && (includeLowercase.isSelected() || includeUppercase.isSelected() || includeNumbers.isSelected() || includeSpecial.isSelected())) {
			error.setText("Choose a PDF File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && file == null && !(includeLowercase.isSelected() || includeUppercase.isSelected() || includeNumbers.isSelected() || includeSpecial.isSelected())) {
			error.setText("Choose a PDF File & at least 1 data extraction option!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		}
		
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		JPanel chooseFilePanel = new JPanel();
		JPanel chosenFilePanel = new JPanel();
		JPanel checkBoxPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		
		chooseFileButton.addActionListener(this);
		chooseFileButton.setPreferredSize(buttonsize2);
		chooseFileButton.setFont(defaultFont);
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		themeLabel = BorderFactory.createTitledBorder("Extracted data options");
		themeLabel.setTitleJustification(TitledBorder.CENTER);
		
		checkBoxPanel.setLayout(new GridLayout(0,4));
		
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		
		includeLowercase = new JCheckBox("Get page count");
		includeLowercase.setFont(defaultFont);
		includeLowercase.setSelected(true);
		includeLowercase.addActionListener(this);
		
		checkBoxPanel.add(includeLowercase);
		
		includeUppercase = new JCheckBox("Get word count");
		includeUppercase.setFont(defaultFont);
		includeUppercase.setSelected(true);
		includeUppercase.addActionListener(this);
		
		checkBoxPanel.add(includeUppercase);
		
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		
		includeNumbers = new JCheckBox("Get sentence count");
		includeNumbers.setFont(defaultFont);
		includeNumbers.setSelected(true);
		includeNumbers.addActionListener(this);
		
		chosenFile.setFont(defaultFont);
		
		checkBoxPanel.add(includeNumbers);
		
		includeSpecial = new JCheckBox("Get character count");
		includeSpecial.setFont(defaultFont);
		includeSpecial.setSelected(true);
		includeSpecial.addActionListener(this);
		
		checkBoxPanel.add(includeSpecial);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkBoxPanel.setBorder(themeLabel);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		errorPanel.add(error);
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(checkBoxPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		return bPanel;
	}
	
	
	
	
}
