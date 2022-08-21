package SystemInformation;

public class OperatingSystemInfo {

	public void printOsInfo() {
        //Operating system name
        System.out.println("Operating System name: " + System.getProperty("os.name"));

        //Operating system version
        System.out.println("Operating System version: " + System.getProperty("os.version"));

        //Operating system architecture
        System.out.println("Operating System Architecture: " + System.getProperty("os.arch"));
        
        //User account name
        System.out.println("User Account name: " + System.getProperty("user.name"));
	}
	
	public void javaInfo() {
        //JRE version number
        System.out.println("Java Version: " + System.getProperty("java.version")); 

        //Installation directory for Java Runtime Environment (JRE)
        System.out.println("JRE Installation Folder: " + System.getProperty("java.home")); 
        
        System.out.println("JRE Vendor Name: " + System.getProperty("java.vendor")); 
        
        System.out.println("JRE Vendor URL: " + System.getProperty("java.vendor.url")); 
	}
}
