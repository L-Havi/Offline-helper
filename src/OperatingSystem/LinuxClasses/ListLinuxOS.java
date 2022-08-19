package OperatingSystem.LinuxClasses;

public class ListLinuxOS {

	public boolean getLinuxOS(String userOS){
		String[] osList ={"Linux"}; 
		for(String operatingSystem : osList){
			 if(operatingSystem.equals(userOS)) {
				 return true;
			 }
		}  
		return false;
	}
	
}
