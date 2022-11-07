package OperatingSystem.MacClasses;

public class ListMacOS {

	public boolean getMacOS(String userOS){
		String[] osList ={"mac"};
		for(String operatingSystem : osList){
			 if(operatingSystem.equals(userOS.toLowerCase())) {
				 return true;
			 }
		}
		return false;
	}

}
