package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class TopPanelButton extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	Font defaultFont = new Font("Gill Sans MT",Font.BOLD,48);
    Color textColor = Color.decode("#ffffff");
    Color backgroundColor = Color.decode("#0D1B2A");
    Color hoverColor = Color.decode("#00aced");
    Border blackline = BorderFactory.createLineBorder(Color.black);
    
    public TopPanelButton(String s) {
        s = s.toUpperCase();
        this.setFocusPainted(false);
        this.setText(s);
        this.setBorder(blackline);
        this.setPreferredSize(new Dimension(200,100));
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        this.setFont(defaultFont);
        this.setOpaque(true);
    }
    

    @Override public void mouseClicked(MouseEvent me) {}
    @Override public void mouseReleased(MouseEvent me) {}
    @Override public void mousePressed(MouseEvent me) {}
    
    @Override
    public void mouseEntered(MouseEvent e) { 
        if (e.getSource()==this) {  
            this.setBackground(this.hoverColor); 
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) { 
        if (e.getSource()==this) { 
            this.setBackground(this.backgroundColor); 
        }
    }

}
