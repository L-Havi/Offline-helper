package LinuxResources;

public class LinuxCommandLineScripts {
	public String getDirectory() {
		return "ls";
	}

	public String getCmdCommandScript(int command) {
		String commandText = "";
		if(command == 1){
			commandText = getDirectory();
		}
		return commandText;
	}
}
