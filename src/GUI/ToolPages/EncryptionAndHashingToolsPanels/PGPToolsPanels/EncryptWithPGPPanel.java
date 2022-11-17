package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.bouncycastle.openpgp.PGPException;

import Cryptography.PGP.PGPActions.PGPEncrypt;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class EncryptWithPGPPanel extends ContentPanelBase {

	private PGPEncrypt pgpencrypt = new PGPEncrypt();
	
	JLabel title;
	
	JPanel titlePanel, buttonPanel;
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	JFileChooser fileChooser3 = new JFileChooser();
	
	JButton startButton = new TopPanelButton("Encrypt");
	
	JButton choosePublicKeyButton = new TopPanelButton("Choose public key");
	JButton chooseTextFileButton = new TopPanelButton("Choose text File");
	JButton chooseEncryptedFileButton = new TopPanelButton("Choose save location");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,15);
	
	File publicKey, txtFile, encryptedFolder;
	
	JLabel error = new JLabel();
	
	JLabel chosenFileLabel = new JLabel("Text File: ");
	JLabel publicKeyLabel = new JLabel("Public Key: ");
	JLabel encryptedFileLabel = new JLabel("Encrypted File save Folder: ");
	
	public EncryptWithPGPPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
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
		if(e.getSource() == chooseTextFileButton) {
			
			fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser2.setAcceptAllFileFilterUsed(false);
			fileChooser2.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {
                    return "Text Files (*.txt)";
                }});
			int option = fileChooser2.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	txtFile = fileChooser2.getSelectedFile();
            	chosenFileLabel.setText("Text File: " + txtFile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		if(e.getSource() == chooseEncryptedFileButton) {
			
			fileChooser3.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser3.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	encryptedFolder = fileChooser3.getSelectedFile();
            	encryptedFileLabel.setText("Encrypted File save Folder: " + encryptedFolder.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && publicKey != null && txtFile != null && encryptedFolder != null) {

			try {
				pgpencrypt.encrypt(publicKey.getAbsolutePath(), encryptedFolder.getAbsolutePath(), txtFile.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Encrypted text File " + txtFile.getAbsolutePath() + " to Folder " + encryptedFolder.getAbsolutePath(), "Encrypt with PGP", JOptionPane.INFORMATION_MESSAGE);
			} catch (NoSuchProviderException | IOException | PGPException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error encrypting file", "Encrypt with PGP", JOptionPane.ERROR_MESSAGE);
			}
			
			publicKey = null;
			txtFile = null;
			encryptedFolder = null;
			
		} else if(e.getSource() == startButton && publicKey == null && txtFile != null ) {
			if(encryptedFolder != null) {
				error.setText("Input Public Key!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input Public Key & destination Folder for encrypted File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && publicKey != null && txtFile == null ) {
			if(encryptedFolder != null) {
				error.setText("Input text File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input text File &  destination Folder for encrypted File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && publicKey == null && txtFile == null ) {
			if(encryptedFolder != null) {
				error.setText("Input Public Key & text File!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input Public Key, text File & destination Folder for encrypted File!");
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
		
		JPanel choosePublicKeyPanel = new JPanel();
		JPanel chosenPublicKeyPanel = new JPanel();
		JPanel chooseTextFilePanel = new JPanel();
		JPanel chosenTextFilePanel = new JPanel();
		JPanel chooseDestinationFolderPanel = new JPanel();
		JPanel chosenDestinationFolderPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		choosePublicKeyButton.addActionListener(this);
		choosePublicKeyButton.setPreferredSize(buttonsize2);
		choosePublicKeyButton.setFont(defaultFont);
		
		chooseTextFileButton.addActionListener(this);
		chooseTextFileButton.setPreferredSize(buttonsize2);
		chooseTextFileButton.setFont(defaultFont);

		chooseEncryptedFileButton.addActionListener(this);
		chooseEncryptedFileButton.setPreferredSize(buttonsize2);
		chooseEncryptedFileButton.setFont(defaultFont);
		
		
		chosenFileLabel.setFont(defaultFont);
		
		choosePublicKeyPanel.add(choosePublicKeyButton);
		chosenPublicKeyPanel.add(publicKeyLabel);
		
		chooseTextFilePanel.add(chooseTextFileButton);
		chosenTextFilePanel.add(chosenFileLabel);
		
		chooseDestinationFolderPanel.add(chooseEncryptedFileButton);
		chosenDestinationFolderPanel.add(encryptedFileLabel);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(choosePublicKeyPanel);
		bPanel.add(chosenPublicKeyPanel);
		bPanel.add(chooseTextFilePanel);
		bPanel.add(chosenTextFilePanel);
		bPanel.add(chooseDestinationFolderPanel);
		bPanel.add(chosenDestinationFolderPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}

}
