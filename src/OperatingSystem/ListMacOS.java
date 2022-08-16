package OperatingSystem;

public class ListMacOS {

	public boolean getMacOS(String userOS){
		String[] osList ={"mac"}; 
		for(String operatingSystem : osList){
			 System.out.println(operatingSystem);
			 System.out.println(userOS);
			 if(operatingSystem.equals(userOS.toLowerCase())) {
				 return true;
			 }
		}  
		return false;
	}
	
}
