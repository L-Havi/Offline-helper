package WindowsResources;

public class CmdCommandScripts {

	private CurrentDirectory cd = new CurrentDirectory();
	private String commandText;

	public String getCmdCommandScript(int command) {

		if(command == 1) {
			commandText = cd.getDirectory();
		}	else {
			commandText = null;
		}

		return commandText;
	}

}
