package OperatingSystem;

public class OperatingSystem {
    public String getOperatingSystem() {
        String os = System.getProperty("os.name");
        return os;
    }
}