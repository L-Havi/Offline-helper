package GUI.ToolPages.EncryptionAndHashingToolsPanels.PasswordToolsPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Cryptography.PasswordActions.GeneratePassword;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class CheckPasswordStrengthPanel extends ContentPanelBase implements ActionListener {

	private GeneratePassword generatepass = new GeneratePassword();
	
	JLabel title;	
	
	JTextField password, hashedText;
	
	TitledBorder themeLabel;
	
	JPanel titlePanel, buttonPanel;
	JButton startButton = new TopPanelButton("Check strength");
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,30);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	JLabel error = new JLabel();
	
	JLabel passwordLabel = new JLabel("Password: ");
	JLabel passwordresultsLabel = new JLabel("Password strength results: ");
	JLabel usedPasswordLabel = new JLabel("Password: ");
	JLabel passwordLengthLabel = new JLabel("");
	JLabel containsLowercaseLabel = new JLabel("");
	JLabel containsUppercaseLabel = new JLabel("");
	JLabel containsNumbersLabel = new JLabel("");
	JLabel containsSpecialLabel = new JLabel("");
	JLabel includedInCommonPasswordsLabel = new JLabel("", SwingConstants.CENTER);
	JLabel bruteForceTimeLabel = new JLabel("", SwingConstants.CENTER);
	
	public CheckPasswordStrengthPanel(String titleText) {
		
		this.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		titlePanel = getTitlePanel(titleText);
		
		buttonPanel = getButtonPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton && !password.getText().isBlank()) {
			
			String[] results = generatepass.checkPasswordStrength(password.getText());
			
			usedPasswordLabel.setText(results[0]);
			passwordLengthLabel.setText(results[1]);
			containsLowercaseLabel.setText(results[2]);
			containsUppercaseLabel.setText(results[3]);
			containsNumbersLabel.setText(results[4]);
			containsSpecialLabel.setText(results[5]);
			includedInCommonPasswordsLabel.setText(results[6]);
			bruteForceTimeLabel.setText(results[7]);
			
		} else if(e.getSource() == startButton && password.getText().isBlank()) {
			error.setText("Type a password to check!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
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
		
		JPanel passwordPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel resultsPanel = new JPanel();
		JPanel bruteForcePanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));
		
		themeLabel = BorderFactory.createTitledBorder("Results");
		themeLabel.setTitleJustification(TitledBorder.CENTER);
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
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

		resultsPanel.setLayout(new GridLayout(0,4));
		bruteForcePanel.setLayout(new GridLayout(0,1));
		
		passwordresultsLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		usedPasswordLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		passwordLengthLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		containsLowercaseLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		containsUppercaseLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		containsNumbersLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		containsSpecialLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,13));
		includedInCommonPasswordsLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		bruteForceTimeLabel.setFont(new Font("Gill Sans MT",Font.PLAIN,15));
		
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(passwordresultsLabel);
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(usedPasswordLabel);
		resultsPanel.add(passwordLengthLabel);
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(containsLowercaseLabel);
		resultsPanel.add(containsUppercaseLabel);
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		resultsPanel.add(containsNumbersLabel);
		resultsPanel.add(containsSpecialLabel);
		resultsPanel.add(Box.createRigidArea(new Dimension(0, 1)));

		bruteForcePanel.add(includedInCommonPasswordsLabel);
		bruteForcePanel.add(bruteForceTimeLabel);
		
		resultsPanel.setBorder(themeLabel);
		
		errorPanel.add(error);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(passwordPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(resultsPanel);
		bPanel.add(bruteForcePanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		return bPanel;
	}
	
}
