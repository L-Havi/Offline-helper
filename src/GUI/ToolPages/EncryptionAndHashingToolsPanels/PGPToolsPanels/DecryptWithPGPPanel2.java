package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchProviderException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.bouncycastle.openpgp.PGPException;

import Cryptography.PGP.PGPActions.PGPDecrypt;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class DecryptWithPGPPanel2 extends ContentPanelBase {

	private PGPDecrypt pgpdecrypt = new PGPDecrypt();
	
	JLabel title;
	
	JTextField password;
	
	JPanel titlePanel, buttonPanel;
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	JFileChooser fileChooser3 = new JFileChooser();
	
	JButton startButton = new TopPanelButton("Decrypt");
	
	JButton choosePrivateKeyButton = new TopPanelButton("Choose private key");
	JButton chooseFolderButton = new TopPanelButton("Choose save Folder");
	JButton chooseEncryptedFileButton = new TopPanelButton("Choose encrypted File");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,15);
	
	File privateKey, encryptedFile, encryptedFolder;
	
	JLabel error = new JLabel();
	
	JLabel passwordLabel = new JLabel("Password: ");
	JLabel chosenFileLabel = new JLabel("Save Folder: ");
	JLabel privateKeyLabel = new JLabel("Private Key: ");
	JLabel encryptedFileLabel = new JLabel("Encrypted File: ");
	
	public DecryptWithPGPPanel2(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == choosePrivateKeyButton) {
			
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".dat");
                }

                @Override
                public String getDescription() {
                    return "DAT Files (*.dat)";
                }});
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	privateKey = fileChooser.getSelectedFile();
            	privateKeyLabel.setText("Private Key: " + privateKey.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		if(e.getSource() == chooseEncryptedFileButton) {
			
			fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser2.setAcceptAllFileFilterUsed(false);
			fileChooser2.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".dat");
                }

                @Override
                public String getDescription() {
                    return "DAT Files (*.dat)";
                }});
			int option = fileChooser2.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	encryptedFile = fileChooser2.getSelectedFile();
            	encryptedFileLabel.setText("Encrypted File: " + encryptedFile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		if(e.getSource() == chooseFolderButton) {
			
			fileChooser3.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser3.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	encryptedFolder = fileChooser3.getSelectedFile();
            	chosenFileLabel.setText("Save Folder: " + encryptedFolder.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && privateKey != null && encryptedFile != null && encryptedFolder != null && !password.getText().isBlank()) {

			try {
				pgpdecrypt.decrypt(privateKey.getAbsolutePath(), encryptedFile.getAbsolutePath(), encryptedFolder.getAbsolutePath(), password.getText());
				JOptionPane.showMessageDialog(null, "Decrypted File " + encryptedFile.getAbsolutePath() + " to Folder " + encryptedFolder.getAbsolutePath(), "Decrypt with PGP", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error decrypting file", "Decrypt with PGP", JOptionPane.ERROR_MESSAGE);
			}
			
			error.setText("");
			privateKey = null;
			encryptedFile = null;
			encryptedFolder = null;
			password.setText("");
			chosenFileLabel.setText("Target Folder: ");
			privateKeyLabel.setText("Private Key: ");
			encryptedFileLabel.setText("Encrypted File: ");
			
		} else if(e.getSource() == startButton && privateKey == null && encryptedFile != null ) {
			if(encryptedFolder != null) {
				error.setText("Input Private Key");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input Private Key & destination Folder for decrypted File");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && privateKey != null && encryptedFile == null ) {
			if(encryptedFolder != null) {
				error.setText("Input encrypted File");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input encrypted File &  destination Folder for decrypted File");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && privateKey == null && encryptedFile == null ) {
			if(encryptedFolder != null) {
				error.setText("Input Private Key & text File");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input Private Key, encrypted File & destination Folder for decrypted File");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} 
		if(e.getSource() == startButton && password.getText().isBlank()) {
			if(error.getText().isBlank()) {
				error.setText(error.getText() + "Input password");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText(error.getText() + " & password");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		}
	}
	
	private JPanel getTitlePanel(String titleText) {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(600,50));
		
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
		
		JPanel choosePrivateKeyPanel = new JPanel();
		JPanel chosenPrivateKeyPanel = new JPanel();
		JPanel chooseTextFilePanel = new JPanel();
		JPanel chosenTextFilePanel = new JPanel();
		JPanel chooseDestinationFolderPanel = new JPanel();
		JPanel chosenDestinationFolderPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		choosePrivateKeyButton.addActionListener(this);
		choosePrivateKeyButton.setPreferredSize(buttonsize2);
		choosePrivateKeyButton.setFont(defaultFont);
		
		chooseFolderButton.addActionListener(this);
		chooseFolderButton.setPreferredSize(buttonsize2);
		chooseFolderButton.setFont(defaultFont);

		chooseEncryptedFileButton.addActionListener(this);
		chooseEncryptedFileButton.setPreferredSize(buttonsize2);
		chooseEncryptedFileButton.setFont(defaultFont);
		
		
		chosenFileLabel.setFont(defaultFont);
		
		choosePrivateKeyPanel.add(choosePrivateKeyButton);
		chosenPrivateKeyPanel.add(privateKeyLabel);
		
		chooseTextFilePanel.add(chooseFolderButton);
		chosenTextFilePanel.add(chosenFileLabel);
		
		chooseDestinationFolderPanel.add(chooseEncryptedFileButton);
		chosenDestinationFolderPanel.add(encryptedFileLabel);
		
		passwordLabel.setLabelFor(password);
		passwordLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		password = new JTextField();
		password.setFont(defaultFont);
		password.addActionListener(this);

		passwordPanel.setMaximumSize(buttonsize2);
		passwordPanel.setLayout(new GridLayout(0,3));
		passwordPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		passwordPanel.add(passwordLabel);
		passwordPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		passwordPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		passwordPanel.add(password);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(choosePrivateKeyPanel);
		bPanel.add(chosenPrivateKeyPanel);
		bPanel.add(chooseDestinationFolderPanel);
		bPanel.add(chosenDestinationFolderPanel);
		bPanel.add(chooseTextFilePanel);
		bPanel.add(chosenTextFilePanel);
		bPanel.add(passwordPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
