package SystemInformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import Utilities.Lists.SubnetList;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.PowerSource;
import oshi.hardware.Sensors;
import oshi.hardware.SoundCard;
import oshi.hardware.UsbDevice;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

public class OperatingSystemInfo {

    SystemInfo systemInfo = new SystemInfo();
    OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
    OperatingSystem.OSVersionInfo versionInfo = operatingSystem.getVersionInfo();
    HardwareAbstractionLayer hardware = systemInfo.getHardware();
    CentralProcessor processor = hardware.getProcessor();
    GlobalMemory globalMemory = hardware.getMemory();
    CentralProcessor.ProcessorIdentifier processorIdentifier = processor.getProcessorIdentifier();
    VirtualMemory virtualMemory = globalMemory.getVirtualMemory();
    FileSystem fileSystem = operatingSystem.getFileSystem();
    List<OSFileStore> osFileStores = fileSystem.getFileStores();
    List<GraphicsCard> graphicsCards = hardware.getGraphicsCards();
    ComputerSystem computerSystem = hardware.getComputerSystem();
    Sensors sensors = hardware.getSensors();
    List<SoundCard> soundCards = hardware.getSoundCards();
    List<UsbDevice> usbDevices = hardware.getUsbDevices(true);
    SubnetList subnetList = new SubnetList();

	public void printOsInfo(String savePath, int saveFileType) throws IOException {
		String[] info = new String[5];
		
        //Operating system name
        System.out.println("Operating System Info");
		System.out.println("------------------------------------------------------------------------");
        System.out.println("Operating System: " + operatingSystem.toString());
        info[0] = operatingSystem.toString();
        System.out.println("Manufacturer: " + operatingSystem.getManufacturer());
        info[1] = operatingSystem.getManufacturer();
        System.out.println("Number of bits supported by the OS (32 or 64): " + operatingSystem.getBitness());
        info[2] = Integer.toString(operatingSystem.getBitness());
        System.out.println("Operating System Architecture: " + System.getProperty("os.arch"));
        info[3] = System.getProperty("os.arch");
        System.out.println("User Account name: " + System.getProperty("user.name"));
        info[4] = System.getProperty("user.name");
        
		if(saveFileType == 0) {
			
			File pdf = new File("res/osInfoPdf.pdf");
			
			PDDocument pDDocument = Loader.loadPDF(pdf);
			PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
			if(pDAcroForm != null) {
				try {
					PDField field = pDAcroForm.getField("date");
					field.setValue(java.time.LocalDate.now().toString());
					
					field = pDAcroForm.getField("operatingSystem");
					if(info[0] != null) {
						field.setValue(info[0]);
					}
					field = pDAcroForm.getField("manufacturer");
					if(info[1] != null) {
						field.setValue(info[1]);
					}
					field = pDAcroForm.getField("osBits");
					if(info[2] != null) {
						field.setValue(info[2]);
					}
					field = pDAcroForm.getField("osArchitecture");
					if(info[3] != null) {
						field.setValue(info[3]);
					}
					field = pDAcroForm.getField("userAccountName");
					if(info[4] != null) {
						field.setValue(info[4]);
					}
				} finally {
					savePath += "\\os_info.pdf";
					pDDocument.save(savePath);
					File result = new File(savePath);
					pDDocument.close();
				}

			}
			
		} else {
			File textFile = new File(savePath + "\\os_info.txt");
			FileOutputStream fos = new FileOutputStream(textFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			bw.write("------------------------------------------------------------------------");
			bw.newLine();
			bw.write("Operating System Information: ");
			bw.newLine();
			bw.write("------------------------------------------------------------------------");
			bw.newLine();

			for (String row : info) {
				bw.write(row);
				bw.newLine();
			}
			bw.close();
		}
	}

	public void printHardwareInfo(String savePath, int saveFileType) {
        //Operating system name

        long usedMemory = globalMemory.getTotal() - globalMemory.getAvailable();

        System.out.println("Hardware Info");
		System.out.println("------------------------------------------------------------------------\n");
        System.out.println("CPU:\n");
        System.out.println("CPU Vendor: " + processorIdentifier.getVendor());
        System.out.println("CPU Name: " + processorIdentifier.getName());
        System.out.println("CPU ID: " + processorIdentifier.getProcessorID());
        System.out.println("Identifier: " + processorIdentifier.getIdentifier());
        System.out.println("Microarchitecture: " + processorIdentifier.getMicroarchitecture());
        System.out.println("Frequency (GHz): " + processorIdentifier.getVendorFreq() / 1000000000.0);
        System.out.println("Number of physical packages: " + processor.getPhysicalPackageCount());
        System.out.println("Number of physical CPUs: " + processor.getPhysicalProcessorCount());
        System.out.println("Number of logical CPUs: " + processor.getLogicalProcessorCount());
        if(sensors.getCpuTemperature() > 0) {
            System.out.println("CPU Temperature: " + sensors.getCpuTemperature());
        }
        if(sensors.getCpuVoltage() > 0) {
            System.out.println("CPU Voltage: " + sensors.getCpuVoltage());
        }

        System.out.println("\n\nGPUs:\n");
        int count = 1;
        for (GraphicsCard graphicsCard : graphicsCards) {
            System.out.println("GPU " + count + ": ");
            System.out.println("GPU Vendor: " + graphicsCard.getVendor());
            System.out.println("GPU Name: " + graphicsCard.getName());
            System.out.println("GPU ID: " + graphicsCard.getDeviceId());
            System.out.println("GPU Version: " + graphicsCard.getVersionInfo());
            System.out.println("VRAM: " + FormatUtil.formatBytes(graphicsCard.getVRam()));
            System.out.println("");
            count++;
        }
        System.out.println("\nRAM:\n");
        System.out.println("Total memory: " + FormatUtil.formatBytes(globalMemory.getTotal()));
        System.out.println("Available memory: " + FormatUtil.formatBytes(globalMemory.getAvailable()));
        System.out.println("Used memory: " + FormatUtil.formatBytes(usedMemory));
        System.out.println("");

        List<PhysicalMemory> physicalMemories = globalMemory.getPhysicalMemory();
        count = 1;
        for (PhysicalMemory physicalMemory : physicalMemories) {
            System.out.println("Memory Bank " + count + ": ");
            System.out.println("Manufacturer: " + physicalMemory.getManufacturer());
            System.out.println("Memory type: " + physicalMemory.getMemoryType());
            System.out.println("Bank/slot label: " + physicalMemory.getBankLabel());
            System.out.println("Capacity: " + FormatUtil.formatBytes(physicalMemory.getCapacity()));
            System.out.println("Clock speed: " + FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
            System.out.println("");
            count++;
        }
        System.out.println("");
        System.out.println("\nVirtual Memory:\n");
        System.out.println("Max virtual memory: " + FormatUtil.formatBytes(virtualMemory.getVirtualMax()));
        System.out.println("Virtual memory used: " + FormatUtil.formatBytes(virtualMemory.getVirtualInUse()));
        System.out.println("Total swap: " + FormatUtil.formatBytes(virtualMemory.getSwapTotal()));
        System.out.println("Swap used: " +FormatUtil.formatBytes(virtualMemory.getSwapUsed()));
        System.out.println("Pages swapped in: " + virtualMemory.getSwapPagesIn());
        System.out.println("Pages swapped out: " + virtualMemory.getSwapPagesOut());
        System.out.println("");
        System.out.println("\nStorage:\n");
        count = 1;
        for(OSFileStore fileStore : osFileStores) {
            System.out.println("Storage Device " + count + ": ");
            System.out.println("Description: " + fileStore.getDescription());
            System.out.println("Label: " + fileStore.getLabel());
            System.out.println("Logical Volume: " + fileStore.getLogicalVolume());
            System.out.println("Mount: " + fileStore.getMount());
            System.out.println("Name: " + fileStore.getName());
            System.out.println("Options: " + fileStore.getOptions());
            System.out.println("Type: " + fileStore.getType());
            System.out.println("UUID: " + fileStore.getUUID());
            System.out.println("Volume: " + fileStore.getVolume());
            System.out.println("Free Space: " + FormatUtil.formatBytes(fileStore.getFreeSpace()));
            System.out.println("Total Space: " + FormatUtil.formatBytes(fileStore.getTotalSpace()));
            System.out.println("Usable Space: " + FormatUtil.formatBytes(fileStore.getUsableSpace()));
            System.out.println();
            count++;
        }

        Baseboard motherboard = computerSystem.getBaseboard();
        Firmware firmware = computerSystem.getFirmware();
        List<PowerSource> powerSources = hardware.getPowerSources();

        System.out.println("\nMotherboad:\n");
        System.out.println("Motherboard Model: " + motherboard.getModel());
        System.out.println("Motherboard Manufacturer: " + motherboard.getManufacturer());
        System.out.println("Motherboard Serial Number: " + motherboard.getSerialNumber());
        System.out.println("Motherboard Version: " + motherboard.getVersion());

        if(powerSources.size() > 0) {
            System.out.println("\nBatteries:\n");
        }
        count = 1;
        for(PowerSource powerSource : powerSources) {
        	if(!powerSource.getDeviceName().equals("unknown")) {
                System.out.println("Battery " + count + ": ");
                System.out.println("Battery Name: " + powerSource.getName());
                System.out.println("Battery Device Name: " + powerSource.getDeviceName());
                System.out.println("Battery Capacity: " + powerSource.getDesignCapacity());
                System.out.println("Battery Amperage: " + powerSource.getAmperage());
                System.out.println("Battery Voltage: " + powerSource.getVoltage());
                System.out.println("Battery Manufacturer: " + powerSource.getManufacturer());
                System.out.println("Battery Manufacture Date: " + powerSource.getManufactureDate());
                System.out.println("Battery Serial Number: " + powerSource.getSerialNumber());
                System.out.println("Battery Temperature: " + powerSource.getTemperature());
                System.out.println("Estimated Time Until Empty: " + powerSource.getTimeRemainingEstimated());
                if(powerSource.isCharging()) {
                    System.out.println("Battery is Charging");
                } else {
                    System.out.println("Battery is Discharging");
                }
                if(powerSource.isPowerOnLine()) {
                    System.out.println("Battery is plugged in to an external power source.");
                }
                System.out.println("");
                count++;
        	}
        }

        if(usbDevices.size() > 0) {
            System.out.println("\n\nUSB Devices:\n");
            count = 1;
            for(UsbDevice usbDevice : usbDevices) {
            	System.out.println("USB Device " + count + ": ");
            	System.out.println("USB Device Name: " + usbDevice.getName());
            	System.out.println("USB Device Product ID: " + usbDevice.getProductId());
            	System.out.println("USB Device Serial Number: " + usbDevice.getSerialNumber());
            	System.out.println("USB Device Unique Device ID: " + usbDevice.getUniqueDeviceId());
            	System.out.println("USB Device Vendor: " + usbDevice.getVendor());
            	System.out.println("USB Device Vendor ID: " + usbDevice.getVendorId());
            	System.out.println("");
            	count++;
            }
        }

        if(soundCards.size() > 0) {
            System.out.println("\n\nSound Card:\n");
            count = 1;
            for(SoundCard soundCard : soundCards) {
            	System.out.println("Sound Card " + count + ": ");
            	System.out.println("Sound Card Name: " + soundCard.getName());
            	System.out.println("Sound Card Driver: " + soundCard.getDriverVersion());
            	System.out.println("Sound Card Codec: " + soundCard.getCodec());
            	System.out.println("");
            	count++;
            }
        }


        int[] fanSpeeds = sensors.getFanSpeeds();
        if(fanSpeeds.length > 0) {
            System.out.println("\n\nFan apeeds:\n");
            for(int fanSpeed : fanSpeeds) {
                System.out.println("Fan " + count + " Speed: " + fanSpeed + " rpm");
                count++;
            }
            System.out.println("");
        }

        System.out.println("\nFirmware:\n");
        System.out.println("Firmware Name: " + firmware.getName());
        System.out.println("Firmware Manufacturer: " + firmware.getManufacturer());
        System.out.println("Firmware Description: " + firmware.getDescription());
        System.out.println("Firmware Version: " + firmware.getVersion());
        System.out.println("Firmware Release Date: " + firmware.getReleaseDate());

        System.out.println("\nOther:\n");
        if(!computerSystem.getSerialNumber().equals("System Serial Number")) {
            System.out.println("Computer System Serial Numer: " + computerSystem.getSerialNumber());
        }
        if(!computerSystem.getManufacturer().equals("System manufacturer")) {
            System.out.println("Computer System Manufacturer: " + computerSystem.getManufacturer());
        }
        if(!computerSystem.getModel().equals("System Product Name")) {
            System.out.println("Computer System Model: " + computerSystem.getModel());
        }
        System.out.println("Hardware UUID: " + computerSystem.getHardwareUUID());
	}

	public void networkInfo(String savePath, int saveFileType) {

		List<NetworkIF> networkInfos = hardware.getNetworkIFs();

		int count = 1;
        System.out.println("Network Info");
		System.out.println("------------------------------------------------------------------------\n");
		for(NetworkIF networkInfo : networkInfos) {
	        System.out.println("Network Interface " + count + ":\n");
	        System.out.println("Interface Name: " + networkInfo.getName());
	        System.out.println("Interface Description: " + networkInfo.getDisplayName());
	        System.out.println("Interface Index: " + networkInfo.getIndex());
	        System.out.println("Interface MTU: " + networkInfo.getMTU());

	        String[] ipv6Addresses = networkInfo.getIPv6addr();
	        if(ipv6Addresses.length > 0) {
	        	int ipv6count = 1;
	        	for(String ipv6Address : ipv6Addresses) {
	    	        System.out.println("IPv6 Address " + ipv6count + ": " + ipv6Address);
	    	        ipv6count++;
	        	}
	        }
	        String[] ipv4Addresses = networkInfo.getIPv4addr();
	        if(ipv4Addresses.length > 0) {
	        	int ipv4count = 1;
	        	for(String ipv4Address : ipv4Addresses) {
	    	        System.out.println("IPv4 Address " + ipv4count + ": " + ipv4Address);
	    	        ipv4count++;
	        	}
	        }
	        Short[] subnetMasks = networkInfo.getSubnetMasks();
	        if(subnetMasks.length > 0) {
	        	int subnetmaskcount = 1;
	        	for(Short subnetMask : subnetMasks) {
	    	        System.out.println("Subnet Mask CIDR " + subnetmaskcount + ": /" + subnetMask);
	    	        int subnetInt = subnetMask;
	    	        String subnetText = subnetList.getSubnetMask(subnetInt);
	    	        System.out.println("Subnet Mask " + subnetmaskcount + ": " + subnetText);
	    	        subnetmaskcount++;
	        	}
	        }
	        System.out.println("MAC Address: " + networkInfo.getMacaddr());
	        System.out.println("MAC Address corresponds to known VM: " + networkInfo.isKnownVmMacAddr());
	        System.out.println("Time Stamp: " + networkInfo.getTimeStamp());
	        System.out.println("\n");
	        count++;
		}

	}

	public void javaInfo(String savePath, int saveFileType) {
        //JRE version number
        System.out.println("Java Version: " + System.getProperty("java.version"));

        //Installation directory for Java Runtime Environment (JRE)
        System.out.println("JRE Installation Folder: " + System.getProperty("java.home"));

        System.out.println("JRE Vendor Name: " + System.getProperty("java.vendor"));

        System.out.println("JRE Vendor URL: " + System.getProperty("java.vendor.url"));
	}
}
