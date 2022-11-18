package GUI.ToolPages.SystemInfoToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import GUI.ContentPanelBase;
import GUI.TopPanelButton;
import SystemInformation.OperatingSystemInfo;

public class GetHardwareInfoPanel extends ContentPanelBase {

	private OperatingSystemInfo osinfo = new OperatingSystemInfo();
	
	JLabel title;
	
	TitledBorder border;
	
	JRadioButton pdfFile, txtFile;
	
	ButtonGroup saveMethods;
	
	JButton chooseFileButton = new TopPanelButton("Choose save Folder");
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
	
	public GetHardwareInfoPanel(String titleText) {

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
	
			int save = 0;
			if(txtFile.isSelected()) {
				save = 1;
			}
			
			osinfo.printHardwareInfo(file.getAbsolutePath(), save);
			
			file = null;
			error.setText("");
			chosenFile.setText("Source Folder: ");
			
		} else if(e.getSource() == startButton && file == null) {
			error.setText("Choose Save Folder!");
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
		JPanel savePanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		chooseFileButton.addActionListener(this);
		startButton.addActionListener(this);
		
		chooseFileButton.setPreferredSize(buttonsize2);
		startButton.setPreferredSize(buttonsize);
		
		chooseFileButton.setFont(defaultFont);
		startButton.setFont(defaultFont);
		
		chosenFile.setFont(defaultFont);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(chosenFile);
		
		border = BorderFactory.createTitledBorder("Choose save File");
		border.setTitleJustification(TitledBorder.CENTER);
		
		saveMethods = new ButtonGroup();
		
		pdfFile = new JRadioButton("PDF File");
		pdfFile.setSelected(true);
		txtFile = new JRadioButton("Text File");
		
		
		saveMethods.add(pdfFile);
		saveMethods.add(txtFile);

		savePanel.add(pdfFile);
		savePanel.add(txtFile);
		savePanel.setPreferredSize(new Dimension(150,50));
		savePanel.setBorder(border);
		
		startPanel.add(startButton);
		
		bPanel.setLayout(new GridLayout(0,1));
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(savePanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
}
