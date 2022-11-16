package SystemInformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import com.google.common.io.Files;

import PdfTools.ActionChooseMenus.MergePdfAction;
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

	PDFMergerUtility PDFmerger = new PDFMergerUtility();
	MergePdfAction mergePdf = new MergePdfAction();
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
        
        File result;
        
		if(saveFileType == 0) {
			
			File pdf = new File("res/pdf/osInfoPdf.pdf");
			

			
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
					result = new File(savePath);
					pDDocument.close();
				}

				if(result.exists()) {
					JOptionPane.showMessageDialog(null, "Created PDF containing operating system information to location " + savePath, "Get OS information to PDF", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to create PDF containing operating system information to location " + savePath, "Get OS information to PDF", JOptionPane.ERROR_MESSAGE);
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
			
			if(textFile.exists()) {
				JOptionPane.showMessageDialog(null, "Created txt-file containing operating system information to location " + savePath, "Get OS information to txt-file", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create txt-file containing operating system information to location " + savePath, "Get OS information to txt-file", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void printHardwareInfo(String savePath, int saveFileType) {

		List<String> info = new ArrayList<String>();
        long usedMemory = globalMemory.getTotal() - globalMemory.getAvailable();
        List<PhysicalMemory> physicalMemories = globalMemory.getPhysicalMemory();
        Baseboard motherboard = computerSystem.getBaseboard();
        Firmware firmware = computerSystem.getFirmware();
        List<PowerSource> powerSources = hardware.getPowerSources();
        
        if(saveFileType == 0) {
        	
            List<File> result = new ArrayList<File>();
        	
			int ogcount = 1;
	        
	        File hardwareInformationBase = getHardwareInfoBase(savePath);
	        if(hardwareInformationBase != null) {
		        result.add(hardwareInformationBase);
	        }
	        
	        ogcount = 1;
	        for (GraphicsCard graphicsCard : graphicsCards) {
	        	File gpuPdf = getGpu(savePath, graphicsCard, ogcount);
		        if(gpuPdf != null) {
			        result.add(gpuPdf);
		        }
		        ogcount++;
	        }
			
	        File ramPdf = getRam(savePath);
	        if(ramPdf != null) {
		        result.add(ramPdf);
	        }
	        
	        File virtualMemoryPdf = getVirtualMemory(savePath);
	        if(virtualMemoryPdf != null) {
		        result.add(virtualMemoryPdf);
	        }
	        
	        ogcount = 1;
	        for (PhysicalMemory physicalMemory : physicalMemories) {
	        	File physicalMemoryPdf = getPhysicalMemory(savePath, physicalMemory, ogcount);
		        if(physicalMemoryPdf != null) {
			        result.add(physicalMemoryPdf);
		        }
		        ogcount++;
	        }
	        
	        ogcount = 1;
	        for (OSFileStore fileStore : osFileStores) {
	        	File storagedevicePdf = getStorageDevices(savePath, fileStore, ogcount);
		        if(storagedevicePdf != null) {
			        result.add(storagedevicePdf);
		        }
		        ogcount++;
	        }
	        
	        File motherboardPdf = getMotherBoard(savePath);
	        if(motherboardPdf != null) {
		        result.add(motherboardPdf);
	        }
	        
	        ogcount = 1;
	        for(PowerSource powerSource : powerSources) {
	        	File batteryPdf = getBattery(savePath, powerSource, ogcount);
		        if(batteryPdf != null) {
			        result.add(batteryPdf);
		        }
		        ogcount++;
	        }
	        
	        ogcount = 1;
            for(UsbDevice usbDevice : usbDevices) {
	        	File usbPdf = getUsbDevice(savePath, usbDevice, ogcount);
		        if(usbPdf != null) {
			        result.add(usbPdf);
		        }
		        ogcount++;
            }
	        
            ogcount = 1;
	        for (SoundCard soundCard : soundCards) {
	        	File soundcardPdf = getSoundCards(savePath, soundCard, ogcount);
		        if(soundcardPdf != null) {
			        result.add(soundcardPdf);
		        }
		        ogcount++;
	        }
	        
			File firmwarePdf = getFirmware(savePath);
	        if(firmwarePdf != null) {
		        result.add(firmwarePdf);
	        }
			
			File otherPdf = getOther(savePath);
	        if(otherPdf != null) {
		        result.add(otherPdf);
	        }
	        
	        if(result.size() > 0) {
	    		try {
			    	PDFmerger.setDestinationFileName(savePath + "\\hardware_info.pdf");
			    	for(File file : result) {
							PDFmerger.addSource(file);
			    	}
			    	PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	File tempFile = new File(savePath + "\\memorybank_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\gpu_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\hardware_information_base.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\firmware_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\battery_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\motherboard_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\otherhardware_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\ram_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\soundcard_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\storage_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\usb_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
		    	tempFile = new File(savePath + "\\virtualmemory_info.pdf");
		    	if(tempFile.exists()) {
		    		tempFile.delete();
		    	}
	        }
	        
	        File success = new File(savePath + "\\hardware_info.pdf");
	        
			if(success.exists()) {
				JOptionPane.showMessageDialog(null, "Created PDF containing hardware information to location " + savePath, "Get hardware information to PDF", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create PDF containing hardware information to location " + savePath, "Get hardware information to PDF", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			
	        info.add("CPU:\n");
	        info.add("CPU Vendor: " + processorIdentifier.getVendor());
	        info.add("CPU Name: " + processorIdentifier.getName());
	        info.add("CPU ID: " + processorIdentifier.getProcessorID());
	        info.add("Identifier: " + processorIdentifier.getIdentifier());
	        info.add("Microarchitecture: " + processorIdentifier.getMicroarchitecture());
	        info.add("Frequency (GHz): " + processorIdentifier.getVendorFreq() / 1000000000.0);
	        info.add("Number of physical packages: " + processor.getPhysicalPackageCount());
	        info.add("Number of physical CPUs: " + processor.getPhysicalProcessorCount());
	        info.add("Number of logical CPUs: " + processor.getLogicalProcessorCount());
	        if(sensors.getCpuTemperature() > 0) {
	        	info.add("CPU Temperature: " + sensors.getCpuTemperature());
	        }
	        if(sensors.getCpuVoltage() > 0) {
	        	info.add("CPU Voltage: " + sensors.getCpuVoltage());
	        }

	        info.add("\n\nGPUs:\n");
	        int count = 1;
	        for (GraphicsCard graphicsCard : graphicsCards) {
	        	info.add("GPU " + count + ": ");
	        	info.add("GPU Vendor: " + graphicsCard.getVendor());
	        	info.add("GPU Name: " + graphicsCard.getName());
	        	info.add("GPU ID: " + graphicsCard.getDeviceId());
	        	info.add("GPU Version: " + graphicsCard.getVersionInfo());
	        	info.add("VRAM: " + FormatUtil.formatBytes(graphicsCard.getVRam()));
	        	info.add("");
	            count++;
	        }
	        info.add("\nRAM:\n");
	        info.add("Total memory: " + FormatUtil.formatBytes(globalMemory.getTotal()));
	        info.add("Available memory: " + FormatUtil.formatBytes(globalMemory.getAvailable()));
	        info.add("Used memory: " + FormatUtil.formatBytes(usedMemory));
	        info.add("");
			
	        count = 1;
	        for (PhysicalMemory physicalMemory : physicalMemories) {
	        	info.add("Memory Bank " + count + ": ");
	        	info.add("Manufacturer: " + physicalMemory.getManufacturer());
	        	info.add("Memory type: " + physicalMemory.getMemoryType());
	        	info.add("Bank/slot label: " + physicalMemory.getBankLabel());
	        	info.add("Capacity: " + FormatUtil.formatBytes(physicalMemory.getCapacity()));
	        	info.add("Clock speed: " + FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
	        	info.add("");
	            count++;
	        }
	        info.add("");
	        info.add("\nVirtual Memory:\n");
	        info.add("Max virtual memory: " + FormatUtil.formatBytes(virtualMemory.getVirtualMax()));
	        info.add("Virtual memory used: " + FormatUtil.formatBytes(virtualMemory.getVirtualInUse()));
	        info.add("Total swap: " + FormatUtil.formatBytes(virtualMemory.getSwapTotal()));
	        info.add("Swap used: " +FormatUtil.formatBytes(virtualMemory.getSwapUsed()));
	        info.add("Pages swapped in: " + virtualMemory.getSwapPagesIn());
	        info.add("Pages swapped out: " + virtualMemory.getSwapPagesOut());
	        info.add("");
	        info.add("\nStorage:\n");
	        count = 1;
	        for(OSFileStore fileStore : osFileStores) {
	        	info.add("Storage Device " + count + ": ");
	        	info.add("Description: " + fileStore.getDescription());
	        	info.add("Label: " + fileStore.getLabel());
	        	info.add("Logical Volume: " + fileStore.getLogicalVolume());
	        	info.add("Mount: " + fileStore.getMount());
	        	info.add("Name: " + fileStore.getName());
	        	info.add("Options: " + fileStore.getOptions());
	        	info.add("Type: " + fileStore.getType());
	        	info.add("UUID: " + fileStore.getUUID());
	        	info.add("Volume: " + fileStore.getVolume());
	        	info.add("Free Space: " + FormatUtil.formatBytes(fileStore.getFreeSpace()));
	        	info.add("Total Space: " + FormatUtil.formatBytes(fileStore.getTotalSpace()));
	        	info.add("Usable Space: " + FormatUtil.formatBytes(fileStore.getUsableSpace()));
	        	info.add("");
	            count++;
	        }
			
	        info.add("\nMotherboad:\n");
	        info.add("Motherboard Model: " + motherboard.getModel());
	        info.add("Motherboard Manufacturer: " + motherboard.getManufacturer());
	        info.add("Motherboard Serial Number: " + motherboard.getSerialNumber());
	        info.add("Motherboard Version: " + motherboard.getVersion());

	        if(powerSources.size() > 0) {
	        	info.add("\nBatteries:\n");
	        }
	        count = 1;
	        for(PowerSource powerSource : powerSources) {
	        	if(!powerSource.getDeviceName().equals("unknown")) {
	        		info.add("Battery " + count + ": ");
	        		info.add("Battery Name: " + powerSource.getName());
	        		info.add("Battery Device Name: " + powerSource.getDeviceName());
	        		info.add("Battery Capacity: " + powerSource.getDesignCapacity());
	        		info.add("Battery Amperage: " + powerSource.getAmperage());
	        		info.add("Battery Voltage: " + powerSource.getVoltage());
	        		info.add("Battery Manufacturer: " + powerSource.getManufacturer());
	        		info.add("Battery Manufacture Date: " + powerSource.getManufactureDate());
	        		info.add("Battery Serial Number: " + powerSource.getSerialNumber());
	        		info.add("Battery Temperature: " + powerSource.getTemperature());
	        		info.add("Estimated Time Until Empty: " + powerSource.getTimeRemainingEstimated());
	                if(powerSource.isCharging()) {
	                	info.add("Battery is Charging");
	                } else {
	                	info.add("Battery is Discharging");
	                }
	                if(powerSource.isPowerOnLine()) {
	                	info.add("Battery is plugged in to an external power source.");
	                }
	                info.add("");
	                count++;
	        	}
	        }

	        if(usbDevices.size() > 0) {
	        	info.add("\n\nUSB Devices:\n");
	            count = 1;
	            for(UsbDevice usbDevice : usbDevices) {
	            	info.add("USB Device " + count + ": ");
	            	info.add("USB Device Name: " + usbDevice.getName());
	            	info.add("USB Device Product ID: " + usbDevice.getProductId());
	            	info.add("USB Device Serial Number: " + usbDevice.getSerialNumber());
	            	info.add("USB Device Unique Device ID: " + usbDevice.getUniqueDeviceId());
	            	info.add("USB Device Vendor: " + usbDevice.getVendor());
	            	info.add("USB Device Vendor ID: " + usbDevice.getVendorId());
	            	info.add("");
	            	count++;
	            }
	        }

	        if(soundCards.size() > 0) {
	        	info.add("\n\nSound Card:\n");
	            count = 1;
	            for(SoundCard soundCard : soundCards) {
	            	info.add("Sound Card " + count + ": ");
	            	info.add("Sound Card Name: " + soundCard.getName());
	            	info.add("Sound Card Driver: " + soundCard.getDriverVersion());
	            	info.add("Sound Card Codec: " + soundCard.getCodec());
	            	info.add("");
	            	count++;
	            }
	        }


	        int[] fanSpeeds = sensors.getFanSpeeds();
	        if(fanSpeeds.length > 0) {
	        	info.add("\n\nFan apeeds:\n");
	            for(int fanSpeed : fanSpeeds) {
	            	info.add("Fan " + count + " Speed: " + fanSpeed + " rpm");
	                count++;
	            }
	            info.add("");
	        }

	        info.add("\nFirmware:\n");
	        info.add("Firmware Name: " + firmware.getName());
	        info.add("Firmware Manufacturer: " + firmware.getManufacturer());
	        info.add("Firmware Description: " + firmware.getDescription());
	        info.add("Firmware Version: " + firmware.getVersion());
	        info.add("Firmware Release Date: " + firmware.getReleaseDate());

	        info.add("\nOther:\n");
	        if(!computerSystem.getSerialNumber().equals("System Serial Number")) {
	        	info.add("Computer System Serial Number: " + computerSystem.getSerialNumber());
	        }
	        if(!computerSystem.getManufacturer().equals("System manufacturer")) {
	        	info.add("Computer System Manufacturer: " + computerSystem.getManufacturer());
	        }
	        if(!computerSystem.getModel().equals("System Product Name")) {
	        	info.add("Computer System Model: " + computerSystem.getModel());
	        }
	        info.add("Hardware UUID: " + computerSystem.getHardwareUUID());
			
			File textFile = new File(savePath + "\\hardware_info.txt");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(textFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			try {
				bw.write("------------------------------------------------------------------------");
				bw.newLine();
				bw.write("Hardware Information: ");
				bw.newLine();
				bw.write("------------------------------------------------------------------------");
				bw.newLine();

				for (String row : info) {
					bw.write(row);
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(textFile.exists()) {
				JOptionPane.showMessageDialog(null, "Created txt-file containing hardware information to location " + savePath, "Get hardware information to txt-file", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create txt-file containing hardware information to location " + savePath, "Get hardware information to txt-file", JOptionPane.ERROR_MESSAGE);
			}
		}
        
	}

	public void networkInfo(String savePath, int saveFileType) {

		List<NetworkIF> networkInfos = hardware.getNetworkIFs();
		
		File result = null;
		
		List<File> files = new ArrayList<File>();
		
		List<String> info = new ArrayList<>();
		
		if(saveFileType == 0) {
			
			int count = 1;
			for(NetworkIF networkInfo : networkInfos) {
				if(count == 1) {
					File tempfile = getFirstPageInterface(count, networkInfo, savePath);
			        if(tempfile != null) {
			        	files.add(tempfile);
			        }
				} else {
					File tempfile = getOtherPageInterface(count, networkInfo, savePath);
			        if(tempfile != null) {
			        	files.add(tempfile);
			        }
				}
				count++;
			}

			if(files.size() > 0) {
	    		try {
			    	PDFmerger.setDestinationFileName(savePath + "\\network_info.pdf");
			    	for(File file : files) {
							PDFmerger.addSource(file);
			    	}
			    	PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for(File file : files) {
					if(file.exists()) {
						file.delete();
					}
				}
	    		
	        }
	        
	        File success = new File(savePath + "\\network_info.pdf");
			
			if(success.exists()) {
				JOptionPane.showMessageDialog(null, "Created PDF containing network information to location " + savePath, "Get network information to PDF", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create PDF containing network information to location " + savePath, "Get network information to PDF", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			File textFile = new File(savePath + "\\network_info.txt");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(textFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			int count = 1;
			for(NetworkIF networkInfo : networkInfos) {
				info.add("Network Interface " + count + ":\n");
				info.add("Interface Name: " + networkInfo.getName());
				info.add("Interface Description: " + networkInfo.getDisplayName());
				info.add("Interface Index: " + networkInfo.getIndex());
				info.add("Interface MTU: " + networkInfo.getMTU());

		        String[] ipv6Addresses = networkInfo.getIPv6addr();
		        if(ipv6Addresses.length > 0) {
		        	int ipv6count = 1;
		        	for(String ipv6Address : ipv6Addresses) {
		        		info.add("IPv6 Address " + ipv6count + ": " + ipv6Address);
		    	        ipv6count++;
		        	}
		        }
		        String[] ipv4Addresses = networkInfo.getIPv4addr();
		        if(ipv4Addresses.length > 0) {
		        	int ipv4count = 1;
		        	for(String ipv4Address : ipv4Addresses) {
		        		info.add("IPv4 Address " + ipv4count + ": " + ipv4Address);
		    	        ipv4count++;
		        	}
		        }
		        Short[] subnetMasks = networkInfo.getSubnetMasks();
		        if(subnetMasks.length > 0) {
		        	int subnetmaskcount = 1;
		        	for(Short subnetMask : subnetMasks) {
		        		info.add("Subnet Mask CIDR " + subnetmaskcount + ": /" + subnetMask);
		    	        int subnetInt = subnetMask;
		    	        String subnetText = subnetList.getSubnetMask(subnetInt);
		    	        info.add("Subnet Mask " + subnetmaskcount + ": " + subnetText);
		    	        subnetmaskcount++;
		        	}
		        }
		        info.add("MAC Address: " + networkInfo.getMacaddr());
		        info.add("MAC Address corresponds to known VM: " + networkInfo.isKnownVmMacAddr());
		        info.add("Time Stamp: " + networkInfo.getTimeStamp());
		        info.add("\n");
		        count++;
			}
			
			try {
				bw.write("------------------------------------------------------------------------");
				bw.newLine();
				bw.write("Network Information: ");
				bw.newLine();
				bw.write("------------------------------------------------------------------------");
				bw.newLine();

				for (String row : info) {
					bw.write(row);
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if(textFile.exists()) {
				JOptionPane.showMessageDialog(null, "Created txt-file containing network information to location " + savePath, "Get network information to txt-file", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create txt-file containing network information to location " + savePath, "Get network information to txt-file", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void javaInfo(String savePath, int saveFileType) {
		
		File result = null;
		
		List<String> info = new ArrayList<>();
		
		if(saveFileType == 0) {
			
			info.add(System.getProperty("java.version"));
			info.add(System.getProperty("java.home"));
			info.add(System.getProperty("java.vendor"));
			info.add(System.getProperty("java.vendor.url"));
			
			File pdf = new File("res/pdf/java_info.pdf");
			
			PDDocument pDDocument = null;
			try {
				pDDocument = Loader.loadPDF(pdf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
			
			if(pDAcroForm != null) {
				
				try {
					PDField field = pDAcroForm.getField("date");
					field.setValue(java.time.LocalDate.now().toString());
					field = pDAcroForm.getField("javaVersion");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("JreInstallationFolder");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("JreVendorName");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("JreVendorUrl");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					savePath += "\\java_info.pdf";
					try {
						pDDocument.save(savePath);
						result = new File(savePath);
						pDDocument.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(result.exists()) {
					JOptionPane.showMessageDialog(null, "Created PDF containing Java information to location " + savePath, "Get Java information to PDF", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to create PDF containing Java information to location " + savePath, "Get Java information to PDF", JOptionPane.ERROR_MESSAGE);
				}

				
			}
			
		} else {
			File textFile = new File(savePath + "\\java_info.txt");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(textFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			info.add("Java Version: " + System.getProperty("java.version"));
			info.add("JRE Installation Folder: " + System.getProperty("java.home"));
			info.add("JRE Vendor Name: " + System.getProperty("java.vendor"));
			info.add("JRE Vendor URL: " + System.getProperty("java.vendor.url"));
			
			try {
				bw.write("------------------------------------------------------------------------");
				bw.newLine();
				bw.write("Java Information: ");
				bw.newLine();
				bw.write("------------------------------------------------------------------------");
				bw.newLine();

				for (String row : info) {
					bw.write(row);
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if(textFile.exists()) {
				JOptionPane.showMessageDialog(null, "Created txt-file containing Java information to location " + savePath, "Get Java information to txt-file", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to create txt-file containing Java information to location " + savePath, "Get Java information to txt-file", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private File getOtherPageInterface(int count, NetworkIF networkInfo, String savePath) {
		File interfacePdf = new File("res/pdf/network_info_otherpages.pdf");
		File result = new File(savePath + "\\otherpage_network_info" + count + ".pdf");
		
		List<String> info = new ArrayList<String>();
		List<String> ipv6 = new ArrayList<String>();
		List<String> ipv4 = new ArrayList<String>();
		List<String> subnetlist = new ArrayList<String>();

		PDField field;
		
		info.add(Integer.toString(count));
		
		info.add(networkInfo.getName());
		info.add(networkInfo.getDisplayName());
		info.add(Integer.toString(networkInfo.getIndex()));
		info.add(Long.toString(networkInfo.getMTU()));

        String[] ipv6Addresses = networkInfo.getIPv6addr();
        if(ipv6Addresses.length > 0) {
        	int ipv6count = 1;
        	for(String ipv6Address : ipv6Addresses) {
        		ipv6.add(ipv6Address);
    	        ipv6count++;
        	}
        }
        String[] ipv4Addresses = networkInfo.getIPv4addr();
        if(ipv4Addresses.length > 0) {
        	int ipv4count = 1;
        	for(String ipv4Address : ipv4Addresses) {
        		ipv4.add(ipv4Address);
    	        ipv4count++;
        	}
        }
        Short[] subnetMasks = networkInfo.getSubnetMasks();
        if(subnetMasks.length > 0) {
        	int subnetmaskcount = 1;
        	for(Short subnetMask : subnetMasks) {
        		subnetlist.add("/" + subnetMask);
    	        int subnetInt = subnetMask;
    	        String subnetText = subnetList.getSubnetMask(subnetInt);
    	        subnetlist.add(subnetText);
    	        subnetmaskcount++;
        	}
        }
        info.add(networkInfo.getMacaddr());
        info.add(Boolean.toString(networkInfo.isKnownVmMacAddr()));
        info.add(Long.toString(networkInfo.getTimeStamp()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(interfacePdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
						field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("interfaceName");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("interfaceDescription");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("interfaceIndex");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("interfaceMtu");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					int setipv6count = 1;
					for(String item : ipv6) {
						if(setipv6count <= 5) {
							field = pDAcroForm.getField("ipv6address" + setipv6count);
							if(item != null) {
									field.setValue(item);
							}
						}
						setipv6count++;
					}
					int setipv4count = 1;
					for(String item : ipv4) {
						if(setipv4count < 2) {
							field = pDAcroForm.getField("ipv4address");
							if(item != null) {
									field.setValue(item);
							}
						}
						setipv4count++;
					}
					int setsubnetcount = 1;
					for(String item : subnetlist) {
						if(setsubnetcount < 2) {
							field = pDAcroForm.getField("subnetMaskCidr");
							if(subnetlist.get(0) != null) {
								field.setValue(subnetlist.get(0));
							}
							field = pDAcroForm.getField("subnetMask");
							if(subnetlist.get(1) != null) {
									field.setValue(subnetlist.get(1));
							}
						}
						setsubnetcount++;
					}
					field = pDAcroForm.getField("macAddress");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
					field = pDAcroForm.getField("macAddressCorresponds");
					if(info.get(6) != null) {
							field.setValue(info.get(6));
					}
					field = pDAcroForm.getField("timeStamp");
					if(info.get(7) != null) {
							field.setValue(info.get(7));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}

	
	private File getFirstPageInterface(int count, NetworkIF networkInfo, String savePath) {
		
		File interfacePdf = new File("res/pdf/network_info_firstpage.pdf");
		File result = new File(savePath + "\\firstpage_network_info.pdf");
		
		List<String> info = new ArrayList<String>();
		List<String> ipv6 = new ArrayList<String>();
		List<String> ipv4 = new ArrayList<String>();
		List<String> subnetlist = new ArrayList<String>();

		PDField field;
		
		info.add(Integer.toString(count));
		
		info.add(networkInfo.getName());
		info.add(networkInfo.getDisplayName());
		info.add(Integer.toString(networkInfo.getIndex()));
		info.add(Long.toString(networkInfo.getMTU()));

        String[] ipv6Addresses = networkInfo.getIPv6addr();
        if(ipv6Addresses.length > 0) {
        	int ipv6count = 1;
        	for(String ipv6Address : ipv6Addresses) {
        		ipv6.add(ipv6Address);
    	        ipv6count++;
        	}
        }
        String[] ipv4Addresses = networkInfo.getIPv4addr();
        if(ipv4Addresses.length > 0) {
        	int ipv4count = 1;
        	for(String ipv4Address : ipv4Addresses) {
        		info.add(ipv4Address);
    	        ipv4count++;
        	}
        }
        Short[] subnetMasks = networkInfo.getSubnetMasks();
        if(subnetMasks.length > 0) {
        	int subnetmaskcount = 1;
        	for(Short subnetMask : subnetMasks) {
        		subnetlist.add("/" + subnetMask);
    	        int subnetInt = subnetMask;
    	        String subnetText = subnetList.getSubnetMask(subnetInt);
    	        subnetlist.add(subnetText);
    	        subnetmaskcount++;
        	}
        }
        info.add(networkInfo.getMacaddr());
        info.add(Boolean.toString(networkInfo.isKnownVmMacAddr()));
        info.add(Long.toString(networkInfo.getTimeStamp()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(interfacePdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("date");
					field.setValue(java.time.LocalDate.now().toString());
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
						field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("interfaceName");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("interfaceDescription");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("interfaceIndex");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("interfaceMtu");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					int setipv6count = 1;
					for(String item : ipv6) {
						if(setipv6count <= 5) {
							field = pDAcroForm.getField("ipv6address" + setipv6count);
							if(item != null) {
									field.setValue(item);
							}
						}
						setipv6count++;
					}
					int setipv4count = 1;
					for(String item : ipv4) {
						if(setipv4count < 2) {
							field = pDAcroForm.getField("ipv4address");
							if(item != null) {
									field.setValue(item);
							}
						}
						setipv4count++;
					}
					int setsubnetcount = 1;
					for(String item : subnetlist) {
						if(setsubnetcount < 2) {
							field = pDAcroForm.getField("subnetMaskCidr");
							if(subnetlist.get(0) != null) {
								field.setValue(subnetlist.get(0));
							}
							field = pDAcroForm.getField("subnetMask");
							if(subnetlist.get(1) != null) {
									field.setValue(subnetlist.get(1));
							}
						}
						setsubnetcount++;
					}
					field = pDAcroForm.getField("macAddress");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
					field = pDAcroForm.getField("macAddressCorresponds");
					if(info.get(6) != null) {
							field.setValue(info.get(6));
					}
					field = pDAcroForm.getField("timeStamp");
					if(info.get(7) != null) {
							field.setValue(info.get(7));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getUsbDevice(String savePath, UsbDevice usbDevice, int count) {
		
		File usbPdf = new File("res/pdf/usb_info.pdf");
		File result = new File(savePath + "\\usb_info.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
		
    	info.add(usbDevice.getName());
    	info.add(usbDevice.getProductId());
    	info.add(usbDevice.getSerialNumber());
    	info.add(usbDevice.getUniqueDeviceId());
    	info.add(usbDevice.getVendor());
    	info.add(usbDevice.getVendorId());
		
		info.add(Integer.toString(count));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(usbPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("text_1hz");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("name");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("productId");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("serialNumber");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("uniqueDeviceId");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("vendor");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
					field = pDAcroForm.getField("vendorId");
					if(info.get(6) != null) {
							field.setValue(info.get(6));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getStorageDevices(String savePath, OSFileStore fileStore, int count) {
		
		File storagePdf = new File("res/pdf/storage_info.pdf");
		File result = new File(savePath + "\\storage_info.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
		
		info.add(Integer.toString(count));
    	info.add(fileStore.getDescription());
    	info.add(fileStore.getLabel());
    	info.add(fileStore.getLogicalVolume());
    	info.add(fileStore.getMount());
    	info.add(fileStore.getName());
    	info.add(fileStore.getOptions());
    	info.add(fileStore.getType());
    	info.add(fileStore.getUUID());
    	info.add(fileStore.getVolume());
    	info.add(FormatUtil.formatBytes(fileStore.getFreeSpace()));
    	info.add(FormatUtil.formatBytes(fileStore.getTotalSpace()));
    	info.add(FormatUtil.formatBytes(fileStore.getUsableSpace()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(storagePdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("description");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("label");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("logicalVolume");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("mount");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("name");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
					field = pDAcroForm.getField("options");
					if(info.get(6) != null) {
							field.setValue(info.get(6));
					}
					field = pDAcroForm.getField("type");
					if(info.get(7) != null) {
							field.setValue(info.get(7));
					}
					field = pDAcroForm.getField("uuid");
					if(info.get(8) != null) {
							field.setValue(info.get(8));
					}
					field = pDAcroForm.getField("volume");
					if(info.get(9) != null) {
							field.setValue(info.get(9));
					}
					field = pDAcroForm.getField("freeSpace");
					if(info.get(10) != null) {
							field.setValue(info.get(10));
					}
					field = pDAcroForm.getField("totalSpace");
					if(info.get(11) != null) {
							field.setValue(info.get(11));
					}
					field = pDAcroForm.getField("usedSpace");
					if(info.get(12) != null) {
							field.setValue(info.get(12));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getSoundCards(String savePath, SoundCard soundCard, int count) {
		
		File soundcardPdf = new File("res/pdf/soundcard_info.pdf");
		File result = new File(savePath + "\\soundcard_info.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
		
		info.add(Integer.toString(count));
    	info.add(soundCard.getName());
    	info.add(soundCard.getDriverVersion());
    	info.add(soundCard.getCodec());
    	
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(soundcardPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("name");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("driver");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("codec");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getVirtualMemory(String savePath) {
		
		File virtualmemoryPdf = new File("res/pdf/virtualmemory_info.pdf");
		
		File result = new File(savePath + "\\virtualmemory_info.pdf");
		
		List<String> info = new ArrayList<String>();
		
		PDField field;
		
        info.add(FormatUtil.formatBytes(virtualMemory.getVirtualMax()));
        info.add(FormatUtil.formatBytes(virtualMemory.getVirtualInUse()));
        info.add(FormatUtil.formatBytes(virtualMemory.getSwapTotal()));
        info.add(FormatUtil.formatBytes(virtualMemory.getSwapUsed()));
        info.add(Long.toString(virtualMemory.getSwapPagesIn()));
        info.add(Long.toString(virtualMemory.getSwapPagesOut()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(virtualmemoryPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("maxVirtualMemory");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("virtualMemoryUsed");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("totalSwap");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("swapUsed");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("pagesSwappedIn");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("pagesSwappedOut");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getRam(String savePath) {
		
		File ramPdf = new File("res/pdf/ram_info.pdf");
		
		File result = new File(savePath + "\\ram_info.pdf");
		
        long usedMemory = globalMemory.getTotal() - globalMemory.getAvailable();
		
		List<String> info = new ArrayList<String>();
		
		PDField field;
		
        info.add(FormatUtil.formatBytes(globalMemory.getTotal()));
        info.add(FormatUtil.formatBytes(globalMemory.getAvailable()));
        info.add(FormatUtil.formatBytes(usedMemory));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(ramPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("totalMemory");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("availableMemory");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("usedMemory");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getOther(String savePath) {
		
		File otherPdf = new File("res/pdf/otherhardware_info.pdf");
		
		File result = new File(savePath + "\\otherhardware_info.pdf");
		
		
		List<String> info = new ArrayList<String>();
		
		PDField field;
		
        info.add(computerSystem.getSerialNumber());
        info.add(computerSystem.getManufacturer());
        info.add(computerSystem.getModel());
        info.add(computerSystem.getHardwareUUID());
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(otherPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("computerSystemSerialNumber");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("computerSystemManufacturer");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("computerSystemModel");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("hardwareUUID");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		if(result.exists()) {
			return result;
		}
		return null;
	}


	private File getMotherBoard(String savePath) {
		
		File motherboardPdf = new File("res/pdf/motherboard_info.pdf");
		
		File result = new File(savePath + "\\motherboard_info.pdf");
		
		
		List<String> info = new ArrayList<String>();

        Baseboard motherboard = computerSystem.getBaseboard();
		
		PDField field;
		
        info.add(motherboard.getModel());
        info.add(motherboard.getManufacturer());
        info.add(motherboard.getSerialNumber());
        info.add(motherboard.getVersion());
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(motherboardPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("model");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("manufacturer");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("serialNumber");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("version");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getFirmware(String savePath) {
		
		File firmwarePdf = new File("res/pdf/firmware_info.pdf");
		
		File result = new File(savePath + "\\firmware_info.pdf");
		
		
		List<String> info = new ArrayList<String>();
		
        Firmware firmware = computerSystem.getFirmware();

		PDField field;
		
        info.add(firmware.getName());
        info.add(firmware.getManufacturer());
        info.add(firmware.getDescription());
        info.add(firmware.getVersion());
        info.add(firmware.getReleaseDate());
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(firmwarePdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("name");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("manufacturer");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("description");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("version");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("releaseDate");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getBattery(String savePath, PowerSource powerSource, int count) {
		
		File batteryPdf = new File("res/pdf/battery_info.pdf");
		File result = new File(savePath + "\\battery_info.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
		
    	if(!powerSource.getDeviceName().equals("unknown")) {
    		info.add(Integer.toString(count));
    		info.add(powerSource.getName());
    		info.add(powerSource.getDeviceName());
    		info.add(Integer.toString(powerSource.getDesignCapacity()));
    		info.add(Double.toString(powerSource.getAmperage()));
    		info.add(Double.toString(powerSource.getVoltage()));
    		info.add(powerSource.getManufacturer());
    		info.add(powerSource.getManufactureDate().toString());
    		info.add(powerSource.getSerialNumber());
    		info.add(Double.toString(powerSource.getTemperature()));
    		info.add(Double.toString(powerSource.getTimeRemainingEstimated()));
    	}
    	
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(batteryPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("name");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("deviceName");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("capacity");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("amperage");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("voltage");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
					field = pDAcroForm.getField("manufacturer");
					if(info.get(6) != null) {
							field.setValue(info.get(6));
					}
					field = pDAcroForm.getField("manufactureDate");
					if(info.get(7) != null) {
							field.setValue(info.get(7));
					}
					field = pDAcroForm.getField("serialNumber");
					if(info.get(8) != null) {
							field.setValue(info.get(8));
					}
					field = pDAcroForm.getField("temperature");
					if(info.get(9) != null) {
							field.setValue(info.get(9));
					}
					field = pDAcroForm.getField("estimatedTimeUntilEmpty");
					if(info.get(10) != null) {
							field.setValue(info.get(10));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getHardwareInfoBase(String savePath) {

		File cpuPdf = new File("res/pdf/hardware_information_base.pdf");
		
		File result = new File(savePath + "\\hardware_information_base.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
    	
        info.add(java.time.LocalDate.now().toString());
        info.add(processorIdentifier.getVendor());
        info.add(processorIdentifier.getName());
        info.add(processorIdentifier.getProcessorID());
        info.add(processorIdentifier.getIdentifier());
        info.add(processorIdentifier.getMicroarchitecture());
        info.add(Double.toString(processorIdentifier.getVendorFreq() / 1000000000.0));
        info.add(Integer.toString(processor.getPhysicalPackageCount()));
        info.add(Integer.toString(processor.getPhysicalProcessorCount()));
        info.add(Integer.toString(processor.getLogicalProcessorCount()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(cpuPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("date");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("cpuVendor");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("cpuName");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("cpuId");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("identifier");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("microarchitecture");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
					field = pDAcroForm.getField("frequency");
					if(info.get(6) != null) {
						field.setValue(info.get(6));
					}
					field = pDAcroForm.getField("numberOfPhysicalPackages");
					if(info.get(7) != null) {
							field.setValue(info.get(7));
					}
					field = pDAcroForm.getField("numberOfPhysicalCpus");
					if(info.get(8) != null) {
							field.setValue(info.get(8));
					}
					field = pDAcroForm.getField("numberOfLogicalCpus");
					if(info.get(9) != null) {
							field.setValue(info.get(9));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getGpu(String savePath, GraphicsCard graphicsCard, int count) {

		File gpuPdf = new File("res/pdf/gpu_info.pdf");
		File result = new File(savePath + "\\gpu_info.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
    	
    	info.add(Integer.toString(count));
    	info.add(graphicsCard.getVendor());
    	info.add(graphicsCard.getName());
    	info.add(graphicsCard.getDeviceId());
    	info.add(graphicsCard.getVersionInfo());
    	info.add(FormatUtil.formatBytes(graphicsCard.getVRam()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(gpuPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("gpuVendor");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("gpuName");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("gpuId");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("gpuVersion");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("vram");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
	private File getPhysicalMemory(String savePath, PhysicalMemory physicalMemory, int count) {
		File physicalMemoryPdf = new File("res/pdf/memorybank_info.pdf");
		File result = new File(savePath + "\\memorybank_info.pdf");
		
		List<String> info = new ArrayList<String>();

		PDField field;
    	
    	info.add(Integer.toString(count));
    	info.add(physicalMemory.getManufacturer());
    	info.add(physicalMemory.getMemoryType());
    	info.add(physicalMemory.getBankLabel());
    	info.add(FormatUtil.formatBytes(physicalMemory.getCapacity()));
    	info.add(FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
		
		PDDocument pDDocument = null;
		try {
			pDDocument = Loader.loadPDF(physicalMemoryPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		if(pDAcroForm != null && info.size() > 0) {
			try {
				try {
					field = pDAcroForm.getField("count");
					if(info.get(0) != null) {
							field.setValue(info.get(0));
					}
					field = pDAcroForm.getField("manufacturer");
					if(info.get(1) != null) {
							field.setValue(info.get(1));
					}
					field = pDAcroForm.getField("memoryType");
					if(info.get(2) != null) {
							field.setValue(info.get(2));
					}
					field = pDAcroForm.getField("bankSlotLabel");
					if(info.get(3) != null) {
							field.setValue(info.get(3));
					}
					field = pDAcroForm.getField("capacity");
					if(info.get(4) != null) {
							field.setValue(info.get(4));
					}
					field = pDAcroForm.getField("clockSpeed");
					if(info.get(5) != null) {
							field.setValue(info.get(5));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					pDDocument.save(result);
					pDDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}	
		
		if(result.exists()) {
			return result;
		}
		return null;
	}
	
}
