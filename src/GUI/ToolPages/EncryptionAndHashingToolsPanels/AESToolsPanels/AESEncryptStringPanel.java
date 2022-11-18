package GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cryptography.AESEncryptionAndDecryption;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class AESEncryptStringPanel extends ContentPanelBase {

	private AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	
	JLabel title;
	
	JTextField encryptedString, password;
	
	JPanel titlePanel, buttonPanel;
	
	JButton startButton = new TopPanelButton("Encrypt");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	JLabel error = new JLabel();
	
	JLabel encryptedStringLabel = new JLabel("Text to be AES encrypted: ");
	JLabel passwordLabel = new JLabel("Password: ");
	
	String salt = "E1F53F35F353C253";
	String algorithm = "AES/CBC/PKCS5Padding";
	
	public AESEncryptStringPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton && !encryptedString.getText().isBlank()) {
			
			String encryptedText = "Decrypted text: ";
			
			if(!password.getText().trim().equals("")) {
				SecretKey key = null;
				try {
					key = aes.generateKey(128);
				} catch (NoSuchAlgorithmException e2) {
					e2.printStackTrace();
				}
				IvParameterSpec ivParameterSpec = aes.generateIv();
				String encryptedCipherText = null;
				try {
					encryptedCipherText = aes.encrypt(algorithm, encryptedString.getText(), key, ivParameterSpec);
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
						| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e1) {
					e1.printStackTrace();
				}
				encryptedText += encryptedCipherText;
			}else {
			    IvParameterSpec ivParameterSpec = aes.generateIv();
			    SecretKey key = null;
				try {
					key = aes.getKeyFromPassword(password.getText(),salt);
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
					e2.printStackTrace();
				}
			    String encryptedCipherText = null;
				try {
					encryptedCipherText = aes.encryptPasswordBased(encryptedString.getText(), key, ivParameterSpec);
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
						| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e1) {
					e1.printStackTrace();
				}
				encryptedText += encryptedCipherText;
			}
			
			JOptionPane.showMessageDialog(null, encryptedText, "Decrypt text", JOptionPane.INFORMATION_MESSAGE);
			
			error.setText("");
			password.setText("");
			encryptedString.setText("");
			
		} else if(e.getSource() == startButton && encryptedString.getText().isBlank()) {
			error.setText("Type a text to encrypt!");
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
		
		JPanel encryptedStringPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);

		encryptedStringLabel.setLabelFor(encryptedString);
		encryptedStringLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		encryptedString = new JTextField();
		encryptedString.setFont(defaultFont);
		encryptedString.addActionListener(this);

		encryptedStringPanel.setMaximumSize(buttonsize2);
		encryptedStringPanel.setLayout(new GridLayout(0,3));
		encryptedStringPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		encryptedStringPanel.add(encryptedStringLabel);
		encryptedStringPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		encryptedStringPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		encryptedStringPanel.add(encryptedString);
		
		passwordLabel.setLabelFor(encryptedString);
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
		bPanel.add(encryptedStringPanel);
		bPanel.add(passwordPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
}
