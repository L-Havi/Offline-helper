package OperatingSystem;

public class ChooseOperatingSystem {

	OperatingSystem operatingSystem = new OperatingSystem();
	ListWindowsOS listWindowsOS = new ListWindowsOS();
	ListLinuxOS listLinuxOS = new ListLinuxOS();
	ListMacOS listMacOS = new ListMacOS();
	
	public int getOperatingSystem() {
		String operatingSystemString = operatingSystem.getOperatingSystem().trim();

		boolean isWindows = false;
	    boolean isLinux = false;
	    boolean isMac = false;
	        
	    int osInt = 3;
	        
	    if (operatingSystemString != null) {
	    	isWindows = listWindowsOS.getWindowsOS(operatingSystemString);
	    	isLinux = listLinuxOS.getLinuxOS(operatingSystemString);
	        isMac = listMacOS.getMacOS(operatingSystemString);
	        if(isWindows) {
	        	osInt = 0;
	        }else if(isLinux) {
	        	osInt = 1;
	        }else if(isMac) {
	        	osInt = 2;
	        }
	    } else {
	    	osInt = 3;
	    }
		return osInt;
	}
	
}
