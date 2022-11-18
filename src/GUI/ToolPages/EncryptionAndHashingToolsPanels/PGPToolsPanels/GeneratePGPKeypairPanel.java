package GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
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

import org.bouncycastle.openpgp.PGPException;

import Cryptography.PGP.PGPActions.GenerateKeyPair;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class GeneratePGPKeypairPanel extends ContentPanelBase implements ActionListener {
	
	private GenerateKeyPair genKey = new GenerateKeyPair();
	
	JLabel title;
	
	JTextField userID, password;
	
	JPanel titlePanel, buttonPanel;
	
	JFileChooser fileChooser = new JFileChooser();
	
	JButton startButton = new TopPanelButton("Generate keypair");
	
	JButton chooseFileButton = new TopPanelButton("Choose keypair save location");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,15);
	
	File file;
	
	JLabel error = new JLabel();
	
	JLabel chosenFileLabel = new JLabel("Destination Folder: ");
	JLabel userIdLabel = new JLabel("User ID: ");
	JLabel passwordLabel = new JLabel("Password: ");
	
	public GeneratePGPKeypairPanel(String titleText) {
		
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
                chosenFileLabel.setText("Destination Folder: " + file.getAbsolutePath());
             }else{
                System.out.println("Open command canceled");
             }
			
		}
		
		if(e.getSource() == startButton && !userID.getText().isBlank() && !password.getText().isBlank() && file != null) {

			try {
				genKey.genKeyPair(userID.getText(), password.getText(), file.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Generated PGP keypair to Folder: " + file.getAbsolutePath() + "\n\nSave User ID & password to secure location" + "\n\nUser ID: " + userID.getText() + "\nPassword: " + password.getText() + "\n\nRemember to never share your private key with anyone!", "Generate PGP Keypair", JOptionPane.INFORMATION_MESSAGE);
			} catch (InvalidKeyException | NoSuchProviderException | SignatureException | NoSuchAlgorithmException
					| IOException | PGPException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error generating keypair", "Generate PGP Keypair", JOptionPane.ERROR_MESSAGE);
			}
			
            chosenFileLabel.setText("Destination Folder: ");
            file = null;
            userID.setText("");
            password.setText("");
			
		} else if(e.getSource() == startButton && userID.getText().isBlank() && !password.getText().isBlank()) {
			if(file != null) {
				error.setText("Input User ID for keypair!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input User ID & destination Folder for keypair!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && !userID.getText().isBlank() && password.getText().isBlank()) {
			if(file != null) {
				error.setText("Input password for keypair!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input password & destination Folder for keypair!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}
		} else if(e.getSource() == startButton && userID.getText().isBlank() && password.getText().isBlank()) {
			if(file != null) {
				error.setText("Input User ID & password for keypair!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
			}else {
				error.setText("Input User ID, password & destination Folder for keypair!");
				error.setFont(defaultFont);
				error.setForeground(Color.red);
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
		
		JPanel userIdPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel chooseSourceFolderPanel = new JPanel();
		JPanel chosenSourceFolderPanel = new JPanel();
		JPanel fillerPanel = new JPanel();
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
		chooseFileButton.setMargin(new Insets(20, 5, 5, 5));

		userIdLabel.setLabelFor(userID);
		userIdLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		userID = new JTextField();
		userID.setFont(defaultFont);
		userID.addActionListener(this);

		userIdPanel.setMaximumSize(buttonsize2);
		userIdPanel.setLayout(new GridLayout(0,3));
		userIdPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		userIdPanel.add(userIdLabel);
		userIdPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		userIdPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		userIdPanel.add(userID);
		
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
		
		chosenFileLabel.setFont(defaultFont);
		
		fillerPanel.add(new JLabel());
		
		chooseSourceFolderPanel.add(chooseFileButton);
		chosenSourceFolderPanel.add(chosenFileLabel);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(userIdPanel);
		bPanel.add(passwordPanel);
		bPanel.add(fillerPanel);
		bPanel.add(chooseSourceFolderPanel);
		bPanel.add(chosenSourceFolderPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		
		return bPanel;
	}
	
	
	
}
