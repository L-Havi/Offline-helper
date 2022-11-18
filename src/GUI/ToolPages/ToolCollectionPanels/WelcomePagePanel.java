package GUI.ToolPages.ToolCollectionPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ContentPanelBase;
public class WelcomePagePanel extends ContentPanelBase {

	JLabel title;
	
	Font titleFont = new Font("Sans Serif",Font.BOLD,36);
	
	Font textFont = new Font("Sans Serif",Font.PLAIN,20);
	
	Font toolsFont = new Font("Sans Serif",Font.BOLD,20);
	
	JPanel titlePanel, buttonPanel;
	
	private int currentSecond;
    private Calendar calendar;
    
	private final SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm");
	
	JLabel info, tools, filesystem, encryption, pdf, sysinfo, currentTime;
	
	public WelcomePagePanel(String titleText) {
		
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
		
		title = new JLabel("Offline Helper");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		titlePanel.add(title);
		
		return titlePanel;
	}
	
	private JPanel getButtonPanel() {
		
		JPanel bPanel = new JPanel();
		JPanel optionsPanel = new JPanel();
		
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.PAGE_AXIS));
		optionsPanel.setLayout(new GridLayout(0, 4));
		
		JLabel currentTimeLabel = new JLabel("Current Time:");
		currentTimeLabel.setBorder(BorderFactory.createEmptyBorder(100,10,10,10));
		currentTimeLabel.setAlignmentX(CENTER_ALIGNMENT);
		currentTime = new JLabel("");
		currentTime.setBorder(BorderFactory.createEmptyBorder(0,10,20,10));
		currentTime.setAlignmentX(CENTER_ALIGNMENT);
		currentTime.setFont(toolsFont);
		currentTimeLabel.setFont(textFont);
		start();
		
		info = new JLabel("Choose a tool from the Menubar. Current tool collections are listed below.");
		info.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		info.setAlignmentX(CENTER_ALIGNMENT);
		tools = new JLabel("Tool collections:");
		tools.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		tools.setAlignmentX(CENTER_ALIGNMENT);
		filesystem = new JLabel("1. File System Tools");
		filesystem.setAlignmentX(CENTER_ALIGNMENT);
		filesystem.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		encryption = new JLabel("2. Encryption & Hashing Tools");
		encryption.setAlignmentX(CENTER_ALIGNMENT);
		encryption.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pdf = new JLabel("3. PDF Tools");
		pdf.setAlignmentX(CENTER_ALIGNMENT);
		pdf.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		sysinfo = new JLabel("4. System Information Tools");
		sysinfo.setAlignmentX(CENTER_ALIGNMENT);
		sysinfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		info.setFont(textFont);
		tools.setFont(textFont);
		
		filesystem.setFont(toolsFont);
		encryption.setFont(toolsFont);
		pdf.setFont(toolsFont);
		sysinfo.setFont(toolsFont);
		
		bPanel.add(currentTimeLabel);
		bPanel.add(currentTime);
		bPanel.add(info);
		bPanel.add(tools);
		bPanel.add(filesystem);
		bPanel.add(encryption);
		bPanel.add(pdf);
		bPanel.add(sysinfo);
		bPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		return bPanel;
	}

    private void reset(){
        calendar = Calendar.getInstance();
        currentSecond = calendar.get(Calendar.SECOND);
    }
	
	private void start() {
		reset();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask(){
            public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                currentTime.setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
                currentSecond++;
            }
        }, 0, 1000 );
	}
	
}
