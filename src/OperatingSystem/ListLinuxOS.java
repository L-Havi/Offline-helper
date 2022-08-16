package OperatingSystem;

public class ListLinuxOS {

	public boolean getLinuxOS(String userOS){
		String[] osList ={"Linux"}; 
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
