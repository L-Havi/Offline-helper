package App;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.MainFrame;

public class Main {
	
    public static void main(String[] args) {

    	try {
    	    UIManager.setLookAndFeel( new FlatDarkLaf() );
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize theme. Using fallback." );
    	}
    	
    	new MainFrame();
    	
    }

}
