package GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cryptography.AESEncryptionAndDecryption;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class AESEncryptFilePanel extends ContentPanelBase {

	private AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	
	JLabel title;
	
	JTextField  password;
	
	JPanel titlePanel, buttonPanel;
	
	JFileChooser fileChooser = new JFileChooser();
	
	JButton startButton = new TopPanelButton("Encrypt");
	
	JButton chooseFileButton = new TopPanelButton("Choose file to AES encrypt");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,15);
	
	File file;
	
	JLabel error = new JLabel();
	
	JLabel encryptedFileLabel = new JLabel("File: ");
	JLabel passwordLabel = new JLabel("Password: ");
	
	String salt = "E1F53F35F353C253";
	String algorithm = "AES/CBC/PKCS5Padding";
	
	public AESEncryptFilePanel(String titleText) {
		
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
			
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                encryptedFileLabel.setText("File: " + file.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && file != null) {
			
			if(!password.getText().trim().equals("")) {
				SecretKey key = null;
				try {
					key = aes.generateKey(128);
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
			    IvParameterSpec ivParameterSpec = aes.generateIv();
			    File inputFile = new File(file.getAbsolutePath());
				int i = file.getAbsolutePath().lastIndexOf("\\");
	        	String[] a =  {file.getAbsolutePath().substring(0, i), file.getAbsolutePath().substring(i)};
	        	int j = a[1].lastIndexOf(".");
	        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
			    File encryptedFile = new File(a[0] + b[0] + ".encrypted");
			    try {
					aes.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
						| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException
						| IOException e1) {
					e1.printStackTrace();
				}
			}else {
				SecretKey key = null;
				try {
					key = aes.getKeyFromPassword(password.getText(),salt);
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    IvParameterSpec ivParameterSpec = aes.generateIv();
			    File inputFile = new File(file.getAbsolutePath());
				int i = file.getAbsolutePath().lastIndexOf("\\");
	        	String[] a =  {file.getAbsolutePath().substring(0, i), file.getAbsolutePath().substring(i)};
	        	int j = a[1].lastIndexOf(".");
	        	String[] b =  {a[1].substring(0, j-1), a[1].substring(j)};
			    File encryptedFile = new File(a[0] + b[0] + ".encrypted");
			    try {
					aes.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
						| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException
						| IOException e1) {
					e1.printStackTrace();
				}
			}
			
			JOptionPane.showMessageDialog(null, "Succesfully encrypted file " + file.getAbsolutePath(), "Encrypt text", JOptionPane.INFORMATION_MESSAGE);
			
			error.setText("");
			password.setText("");
			encryptedFileLabel.setText("File: ");
			file = null;
			
		} else if(e.getSource() == startButton && file == null) {
			error.setText("Choose a File to encrypt!");
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
		JPanel passwordPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);

		chooseFileButton.addActionListener(this);
		chooseFileButton.setPreferredSize(buttonsize2);
		chooseFileButton.setFont(defaultFont);
		
		encryptedFileLabel.setFont(defaultFont);
		
		chooseFilePanel.add(chooseFileButton);
		chosenFilePanel.add(encryptedFileLabel);
		
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
		bPanel.add(chooseFilePanel);
		bPanel.add(chosenFilePanel);
		bPanel.add(passwordPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
