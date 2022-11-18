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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Cryptography.PasswordActions.GeneratePassword;
import GUI.ContentPanelBase;
import GUI.TopPanelButton;

public class CreateRandomPasswordPanel extends ContentPanelBase implements ActionListener, ChangeListener {

	private GeneratePassword generatepass = new GeneratePassword();
	
	JLabel title;	
	
	static final int LENGTH_MIN = 0;
	static final int LENGTH_MAX = 50;
	static final int LENGTH_INIT = 12; 
	
	TitledBorder themeLabel;
	
	JPanel titlePanel, buttonPanel;
	JButton startButton = new TopPanelButton("Generate");
	
	JCheckBox includeLowercase, includeUppercase, includeNumbers, includeSpecial;
	
	Dimension buttonsize = new Dimension(100,50);
	Dimension buttonsize2 = new Dimension(300,50);
	
	JTextField password;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,30);
	
	Font defaultFont = new Font("Gill Sans MT",Font.PLAIN,20);
	
	int passwordLength = LENGTH_INIT;
	
	JLabel error = new JLabel();
	
	JLabel passwordLengthLabel = new JLabel("Password Length: 12");
	JLabel passwordLabel = new JLabel("Password: ");
	
	public CreateRandomPasswordPanel(String titleText) {
		
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
		
		if(e.getSource() == startButton && (includeLowercase.isSelected() || includeUppercase.isSelected() || includeNumbers.isSelected() || includeSpecial.isSelected())) {
			
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
			
			String finalPassword = generatepass.generatePassword(passwordLength, includeLowercaseInt, includeUppercaseInt, includeNumbersInt, includeSpecialInt);
			password.setText(finalPassword);
			
			error.setText("");
			
		} else if(e.getSource() == startButton && !(includeLowercase.isSelected() || includeUppercase.isSelected() || includeNumbers.isSelected() || includeSpecial.isSelected())) {
			error.setText("Choose at least 1 type of characters to use in password!");
			error.setFont(defaultFont);
			error.setForeground(Color.red);
		}
		
	}
	
	public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        passwordLength = (int)source.getValue();
	        passwordLengthLabel.setText("Password Length: " + passwordLength);
	    }
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		
		JPanel passwordLengthLabelPanel = new JPanel();
		JPanel passwordLengthPanel = new JPanel();
		JPanel checkBoxPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		
		bPanel.setMaximumSize(new Dimension(200,200));
		
		bPanel.setLayout(new GridLayout(0,1));

		passwordLengthLabel.setFont(defaultFont);
		passwordLengthLabelPanel.add(passwordLengthLabel);
		
		JSlider passwordLengthSlider = new JSlider(JSlider.HORIZONTAL,
                LENGTH_MIN, LENGTH_MAX, LENGTH_INIT);
		passwordLengthSlider.addChangeListener(this);
		
		passwordLengthSlider.setMajorTickSpacing(10);
		passwordLengthSlider.setMinorTickSpacing(1);
		passwordLengthSlider.setPaintTicks(true);
		passwordLengthSlider.setPaintLabels(true);
		
		passwordLengthPanel.add(passwordLengthSlider);
		
		themeLabel = BorderFactory.createTitledBorder("Characters used");
		themeLabel.setTitleJustification(TitledBorder.CENTER);
		
		checkBoxPanel.setLayout(new GridLayout(0,4));
		
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		
		includeLowercase = new JCheckBox("Include lowercase letters");
		includeLowercase.setFont(defaultFont);
		includeLowercase.setSelected(true);
		includeLowercase.addActionListener(this);
		
		checkBoxPanel.add(includeLowercase);
		
		includeUppercase = new JCheckBox("Include uppercase letters");
		includeUppercase.setFont(defaultFont);
		includeUppercase.setSelected(true);
		includeUppercase.addActionListener(this);
		
		checkBoxPanel.add(includeUppercase);
		
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		
		includeNumbers = new JCheckBox("Include numbers");
		includeNumbers.setFont(defaultFont);
		includeNumbers.setSelected(true);
		includeNumbers.addActionListener(this);
		
		checkBoxPanel.add(includeNumbers);
		
		includeSpecial = new JCheckBox("Include special characters");
		includeSpecial.setFont(defaultFont);
		includeSpecial.setSelected(true);
		includeSpecial.addActionListener(this);
		
		checkBoxPanel.add(includeSpecial);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		checkBoxPanel.setBorder(themeLabel);

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
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(buttonsize2);
		startButton.setFont(defaultFont);
		
		startPanel.add(startButton);
		
		bPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		bPanel.add(passwordLengthLabelPanel);
		bPanel.add(passwordLengthPanel);
		bPanel.add(checkBoxPanel);
		bPanel.add(errorPanel);
		bPanel.add(startPanel);
		bPanel.add(passwordPanel);
		bPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		return bPanel;
	}
	
	
	
	
}
