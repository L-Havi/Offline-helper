package SystemInformation;

import java.util.Map;

public class EnvironmentVariables {
	public void getAllEnvironmentVariables() {
        Map<String, String> map = System.getenv();
        System.out.println("System's environment variables");
		System.out.println("------------------------------------------------------------------------");
        map.entrySet().forEach(System.out::println);
	}
}
