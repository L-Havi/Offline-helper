package LinuxResources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteLinuxScripts {

	public void executeCommand(String commandText) {
        String s;
        Process p;
		try {
			p = Runtime.getRuntime().exec(commandText);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
            	System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
}
