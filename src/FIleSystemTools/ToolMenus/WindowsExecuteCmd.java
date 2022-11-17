package FIleSystemTools.ToolMenus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsExecuteCmd {

	public void executeCmd(String commandText) {
		ProcessBuilder builder = new ProcessBuilder();
		builder = new ProcessBuilder("cmd.exe", "/c", commandText);

        builder.redirectErrorStream(true);
        Process p;
		try {
			p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	        	try {
	        		line = r.readLine();
		            if (line == null) {
		            	break;
		            }
		            System.out.println(line);
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
