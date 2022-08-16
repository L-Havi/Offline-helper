package OperatingSystem;

public class ListWindowsOS {
	
	public boolean getWindowsOS(String userOS){
		String[] osList ={"Windows 11", "Windows 10","Windows 8", "Windows 8.1", "Windows Vista", "Windows 7", "Windows XP", "Windows 2000", "Windows 2003", "Windows 98", "Windows 95", "Windows NT", "Windows Me"}; 
		for(String operatingSystem : osList){
			 System.out.println(operatingSystem);
			 System.out.println(userOS);
			 if(operatingSystem.equals(userOS)) {
				 return true;
			 }
		}  
		return false;
	}
	
}
