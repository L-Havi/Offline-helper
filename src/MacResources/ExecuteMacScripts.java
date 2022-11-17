package MacResources;

import java.io.IOException;

public class ExecuteMacScripts {

	public void executeCommand(String commandText) {
		try {
			Runtime.getRuntime().exec(commandText);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
