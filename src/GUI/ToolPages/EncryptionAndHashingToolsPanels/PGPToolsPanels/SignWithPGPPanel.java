package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import Cryptography.PGP.PGPActions.PGPCreateSignature;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class SignWithPGPPanel extends ContentPanelBase {

	private PGPCreateSignature pgpsignature = new PGPCreateSignature();
	
	JLabel title;
	
	JTextField password;
	
	JPanel titlePanel, buttonPanel;
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	JFileChooser fileChooser3 = new JFileChooser();
	
	JButton startButton = new TopPanelButton("Sign");
	
	JButton choosePrivateKeyButton = new TopPanelButton("Choose private key");
	JButton choosePublicKeyButton = new TopPanelButton("Choose public key");
	JButton chooseFolderButton = new TopPanelButton("Choose save Folder");
	JButton chooseEncryptedFileButton = new TopPanelButton("Choose File");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(250,40);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,15);
	
	File privateKey, publicKey, encryptedFile, encryptedFolder;
	
	JLabel error = new JLabel();
	
	JLabel passwordLabel = new JLabel("Password: ");
	JLabel chosenFileLabel = new JLabel("Save Folder: ");
	JLabel privateKeyLabel = new JLabel("Private Key: ");
	JLabel publicKeyLabel = new JLabel("Public Key: ");
	JLabel encryptedFileLabel = new JLabel("File to be signed: ");
	
	public SignWithPGPPanel(String titleText) {
		
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
		if(e.getSource() == choosePublicKeyButton) {
			
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
            	publicKey = fileChooser.getSelectedFile();
            	publicKeyLabel.setText("Public Key: " + publicKey.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		if(e.getSource() == chooseEncryptedFileButton) {
			
			fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int option = fileChooser2.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	encryptedFile = fileChooser2.getSelectedFile();
            	encryptedFileLabel.setText("File to be signed: " + encryptedFile.getAbsolutePath());
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
		
		if(e.getSource() == startButton && privateKey != null && publicKey != null && encryptedFile != null && encryptedFolder != null && !password.getText().isBlank()) {
			
			try {
				pgpsignature.signAction(encryptedFolder.getAbsolutePath(), encryptedFile.getAbsolutePath(), privateKey.getAbsolutePath(), publicKey.getAbsolutePath(), password.getText());
				JOptionPane.showMessageDialog(null, "Created signature for File " + encryptedFile.getAbsolutePath() + " to Folder " + encryptedFolder.getAbsolutePath(), "Sign with PGP", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error signing file", "Sign with PGP", JOptionPane.ERROR_MESSAGE);
			}
			
			error.setText("");
			privateKey = null;
			publicKey = null;
			encryptedFile = null;
			encryptedFolder = null;
			password.setText("");
			chosenFileLabel.setText("Target Folder: ");
			privateKeyLabel.setText("Private Key: ");
			publicKeyLabel.setText("Public Key: ");
			encryptedFileLabel.setText("Encrypted File: ");
			
		} else if(e.getSource() == startButton && privateKey == null && encryptedFile != null ) {
			if(encryptedFolder != null) {
				if(publicKey != null) {
					error.setText("Input Private Key");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText("Input Private Key & Public Key");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}else {
				if(publicKey != null) {
					error.setText("Input Private Key & destination Folder for decrypted File");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText("Input Private Key, Public Key & destination Folder for decrypted File");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}
		} else if(e.getSource() == startButton && privateKey != null && encryptedFile == null ) {
			if(encryptedFolder != null) {
				if(publicKey != null) {
					error.setText("Input File to be signed");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText("Input Public Key & File to be signed");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}else {
				if(publicKey != null) {
					error.setText("Input File to be signed & destination Folder for signature File");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText("Input Public Key, File to be signed & destination Folder for signature File");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}
		} else if(e.getSource() == startButton && privateKey == null && encryptedFile == null ) {
			if(encryptedFolder != null) {
				if(publicKey != null) {
					error.setText("Input Private Key & File to be signed");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText("Input Private Key, Public Key & File to be signed");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}else {
				if(publicKey != null) {
					error.setText("Input Private Key, File to be signed & destination Folder for signature File");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText("Input Private Key, Public Key, File to be signed & destination Folder for signature File");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}
		} 
		if(e.getSource() == startButton && password.getText().isBlank()) {
			if(error.getText().isBlank()) {
				if(publicKey != null) {
					error.setText(error.getText() + "Input password");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}else {
					error.setText(error.getText() + "Input password & Public Key");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
			}else {
				if(publicKey != null) {
					error.setText(error.getText() + " & password");
					error.setFont(defaultFont);
					error.setForeground(Color.red);
				}
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
		
		JPanel choosePrivateKeyPanel = new JPanel();
		JPanel chosenPrivateKeyPanel = new JPanel();
		JPanel choosePublicKeyPanel = new JPanel();
		JPanel chosenPublicKeyPanel = new JPanel();
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
		
		choosePublicKeyButton.addActionListener(this);
		choosePublicKeyButton.setPreferredSize(buttonsize2);
		choosePublicKeyButton.setFont(defaultFont);
		
		chooseFolderButton.addActionListener(this);
		chooseFolderButton.setPreferredSize(buttonsize2);
		chooseFolderButton.setFont(defaultFont);

		chooseEncryptedFileButton.addActionListener(this);
		chooseEncryptedFileButton.setPreferredSize(buttonsize2);
		chooseEncryptedFileButton.setFont(defaultFont);
		
		
		chosenFileLabel.setFont(defaultFont);
		
		choosePrivateKeyPanel.add(choosePrivateKeyButton);
		chosenPrivateKeyPanel.add(privateKeyLabel);
		
		choosePublicKeyPanel.add(choosePublicKeyButton);
		chosenPublicKeyPanel.add(publicKeyLabel);
		
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
		bPanel.add(choosePublicKeyPanel);
		bPanel.add(chosenPublicKeyPanel);
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
