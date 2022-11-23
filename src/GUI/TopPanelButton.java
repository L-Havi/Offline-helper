package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class TopPanelButton extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	Font defaultFont = new Font("Gill Sans MT",Font.BOLD,30);
    Color textColor = Color.decode("#ffffff");
    Color hoverTextColor = Color.decode("#000000");
    Color backgroundColor = Color.decode("#000000");
    Color hoverColor = Color.decode("#C0C0C0");
    
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Border whiteline = BorderFactory.createLineBorder(Color.white);
    
    public TopPanelButton(String s) {
        s = s.toUpperCase();
        this.setFocusPainted(false);
        this.setText(s);
        this.setBorder(whiteline);
        this.setPreferredSize(new Dimension(100,500));
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        this.setFont(defaultFont);
        this.setOpaque(false);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackgroundColor(hoverColor);
                setForegroundColor(hoverTextColor);
                setBorderColor(blackline);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackgroundColor(backgroundColor);
                setForegroundColor(textColor);
                setBorderColor(whiteline);
            }
        });
    }

    private void setBackgroundColor(Color color) {
    	this.setBackground(color); 
    }

    private void setForegroundColor(Color color) {
    	this.setForeground(color); 
    }
    
    private void setBorderColor(Border border) {
    	this.setBorder(border);
    }
    
    @Override public void mouseClicked(MouseEvent me) {}
    @Override public void mouseReleased(MouseEvent me) {}
    @Override public void mousePressed(MouseEvent me) {}
    @Override public void mouseEntered(MouseEvent me) {}
    @Override public void mouseExited(MouseEvent me) {}
    
}
