package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class TopPanelLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	Font defaultFont = new Font("Gill Sans MT",Font.BOLD,42);
    Color textColor = Color.decode("#ffffff");
    Color backgroundColor = Color.decode("#0D1B2A");
    Color hoverColor = Color.decode("#00aced");
    
    public TopPanelLabel(String s) {
        s = s.toUpperCase();
        this.setText(s);
        this.setPreferredSize(new Dimension(250,100));
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        this.setFont(defaultFont);
        this.setOpaque(true);
    }
	
}
