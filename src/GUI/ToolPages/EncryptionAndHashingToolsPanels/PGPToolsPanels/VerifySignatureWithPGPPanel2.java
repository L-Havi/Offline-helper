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

import Cryptography.PGP.PGPActions.PGPVerifySignature;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class VerifySignatureWithPGPPanel2 extends ContentPanelBase {

	private PGPVerifySignature verify = new PGPVerifySignature();
	
	JLabel title;
	
	JTextField password;
	
	JPanel titlePanel, buttonPanel;
	
	JFileChooser fileChooser = new JFileChooser();
	JFileChooser fileChooser2 = new JFileChooser();
	JFileChooser fileChooser3 = new JFileChooser();
	
	JButton startButton = new TopPanelButton("Verify signature");
	
	JButton choosePublicKeyButton = new TopPanelButton("Choose public key");
	JButton chooseSignatureButton = new TopPanelButton("Choose Signature");
	JButton chooseEncryptedFileButton = new TopPanelButton("Choose File");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(250,40);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,15);
	
	File publicKey, encryptedFile, encryptedFolder;
	
	JLabel error = new JLabel();
	
	JLabel resultLabel = new JLabel("");
	JLabel signatureFileLabel = new JLabel("Signature File: ");
	JLabel publicKeyLabel = new JLabel("Public Key: ");
	JLabel chosenFileLabel = new JLabel("File to be verified: ");
	
	public VerifySignatureWithPGPPanel2(String titleText) {
		
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
		if(e.getSource() == chooseEncryptedFileButton) {
			
			fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int option = fileChooser2.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	encryptedFile = fileChooser2.getSelectedFile();
            	chosenFileLabel.setText("File to be signed: " + encryptedFile.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		if(e.getSource() == chooseSignatureButton) {
			
			fileChooser3.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser3.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {
                    return "TXT Files (*.txt)";
                }});
			int option = fileChooser3.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            	encryptedFolder = fileChooser3.getSelectedFile();
            	signatureFileLabel.setText("Signature File: " + encryptedFolder.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && publicKey != null && encryptedFile != null && encryptedFolder != null) {
			int result = 0;
			try {
				result = verify.verifyAction(publicKey.getAbsolutePath(), encryptedFile.getAbsolutePath(), encryptedFolder.getAbsolutePath());
				if(result == 1) {
					resultLabel.setText("Signature is valid");
					resultLabel.setFont(defaultFont);
					resultLabel.setForeground(Color.green);
				} else {
					resultLabel.setText("Signature is not valid");
					resultLabel.setFont(defaultFont);
					resultLabel.setForeground(Color.red);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error verifying signature", "Verify signature with PGP", JOptionPane.ERROR_MESSAGE);
			}
			
			error.setText("");
			publicKey = null;
			encryptedFile = null;
			encryptedFolder = null;
			chosenFileLabel.setText("File to be verified: ");
			publicKeyLabel.setText("Public Key: ");
			signatureFileLabel.setText("Signature File: ");
			
		} else if(e.getSource() == startButton && publicKey == null && encryptedFile != null && encryptedFolder != null) {
			error.setText("Input Public Key!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && publicKey != null && encryptedFile == null && encryptedFolder != null) {
			error.setText("Input File to verify!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && publicKey != null && encryptedFile != null && encryptedFolder == null) {
			error.setText("Input Signature File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && publicKey != null && encryptedFile == null && encryptedFolder == null) {
			error.setText("Input File to verify & Signature File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && publicKey == null && encryptedFile != null && encryptedFolder == null) {
			error.setText("Input Public Key & signature File!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && publicKey == null && encryptedFile == null && encryptedFolder != null) {
			error.setText("Input Public Key & File to verify!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		} else if(e.getSource() == startButton && publicKey == null && encryptedFile == null && encryptedFolder == null) {
			error.setText("Input Public Key, File to verify & Signature File!");
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
		
		JPanel choosePublicKeyPanel = new JPanel();
		JPanel chosenPublicKeyPanel = new JPanel();
		JPanel chooseSignatureFilePanel = new JPanel();
		JPanel chosenSignatureFilePanel = new JPanel();
		JPanel chooseDestinationFolderPanel = new JPanel();
		JPanel chosenDestinationFolderPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel resultsPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		choosePublicKeyButton.addActionListener(this);
		choosePublicKeyButton.setPreferredSize(buttonsize2);
		choosePublicKeyButton.setFont(defaultFont);
		
		chooseSignatureButton.addActionListener(this);
		chooseSignatureButton.setPreferredSize(buttonsize2);
		chooseSignatureButton.setFont(defaultFont);

		chooseEncryptedFileButton.addActionListener(this);
		chooseEncryptedFileButton.setPreferredSize(buttonsize2);
		chooseEncryptedFileButton.setFont(defaultFont);
		
		
		chosenFileLabel.setFont(defaultFont);
		
		choosePublicKeyPanel.add(choosePublicKeyButton);
		chosenPublicKeyPanel.add(publicKeyLabel);
		
		chooseSignatureFilePanel.add(chooseSignatureButton);
		chosenSignatureFilePanel.add(signatureFileLabel);
		
		chooseDestinationFolderPanel.add(chooseEncryptedFileButton);
		chosenDestinationFolderPanel.add(chosenFileLabel);
		
		resultLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		resultsPanel.add(resultLabel);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(choosePublicKeyPanel);
		bPanel.add(chosenPublicKeyPanel);
		bPanel.add(chooseSignatureFilePanel);
		bPanel.add(chosenSignatureFilePanel);
		bPanel.add(chooseDestinationFolderPanel);
		bPanel.add(chosenDestinationFolderPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(resultsPanel);
		return bPanel;
	}
	
}
