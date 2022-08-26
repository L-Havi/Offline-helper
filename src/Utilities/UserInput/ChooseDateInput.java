package Utilities.UserInput;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class ChooseDateInput {

	Scanner scanner = new Scanner(System.in);
	String actionChoice;
	boolean isValidName = false;
	
	public Date getFullDateWithTime() {
		Date date = new Date();
		while(!isValidName) {
			System.out.println("Input new date in format dd-MM-yyyy HH:mm:ss (e.g. 31-11-1998 23:37:50)");
			actionChoice = scanner.nextLine();
			boolean isValid = isValidFullDateFormat(actionChoice);
			if(isValid == true) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
				try {
					date = formatter.parse(actionChoice);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				isValidName = true;
			} else {
				System.out.println("Extension was not recognized. Please try again");
			}
		}
		return date;
	}
	
    private boolean isValidFullDateFormat(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

	
}
