package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PasswordToolsPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels.AESDecryptFilePanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels.AESDecryptStringPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels.AESEncryptFilePanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.AESToolsPanels.AESEncryptStringPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanels.CheckMD5ChecksumPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanels.CreateMD5ChecksumPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.MD5ToolsPanels.HashTextWithMD5Panel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels.DecryptWithPGPPanel2;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels.EncryptWithPGPPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels.GeneratePGPKeypairPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels.SignWithPGPPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PGPToolsPanels.VerifySignatureWithPGPPanel2;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PasswordToolsPanels.CheckPasswordStrengthPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.PasswordToolsPanels.CreateRandomPasswordPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels.CreateSHAChecksumPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels.HashTextWithSHAPanel;
import GUI.ToolPages.EncryptionAndHashingToolsPanels.SHAToolsPanels.VerifySHAChecksumPanel;
import GUI.ToolPages.FileSystemToolsPanels.DeleteAllFilesInFolderPanel;
import GUI.ToolPages.FileSystemToolsPanels.GetFolderSizeAndFileCountPanel;
import GUI.ToolPages.FileSystemToolsPanels.GetPathsInFolderPanel;
import GUI.ToolPages.FileSystemToolsPanels.MassChangeFiletypesPanel;
import GUI.ToolPages.FileSystemToolsPanels.MassCopyFolderContentsPanel;
import GUI.ToolPages.FileSystemToolsPanels.MassRenameFilesPanel;
import GUI.ToolPages.FileSystemToolsPanels.RemoveDuplicateFilesPanel;
import GUI.ToolPages.FileSystemToolsPanels.SortFilesByExtensionPanel;
import GUI.ToolPages.FileSystemToolsPanels.UnzipOrZipFilesPanel;
import GUI.ToolPages.PDFToolsPanels.ConvertPDFFileToAnotherFormatPanel;
import GUI.ToolPages.PDFToolsPanels.EditPDFFileMetadataPanel;
import GUI.ToolPages.PDFToolsPanels.ExtractPDFFileMetadataPanel;
import GUI.ToolPages.PDFToolsPanels.ExtractTextFromPDFFilePanel;
import GUI.ToolPages.PDFToolsPanels.MergePDFFilesPanel;
import GUI.ToolPages.PDFToolsPanels.SplitPDFFilePanel;
import GUI.ToolPages.SystemInfoToolsPanels.GetEnvironmentVariablesPanel;
import GUI.ToolPages.SystemInfoToolsPanels.GetHardwareInfoPanel;
import GUI.ToolPages.SystemInfoToolsPanels.GetJavaInfoPanel;
import GUI.ToolPages.SystemInfoToolsPanels.GetNetworkInfoPanel;
import GUI.ToolPages.SystemInfoToolsPanels.GetOperatingSystemInfoPanel;
import GUI.ToolPages.ToolCollectionPanels.EncryptionAndHashingToolsPanel;
import GUI.ToolPages.ToolCollectionPanels.FileSystemToolsPanel;
import GUI.ToolPages.ToolCollectionPanels.PdfToolsPanel;
import GUI.ToolPages.ToolCollectionPanels.SettingsPanel;
import GUI.ToolPages.ToolCollectionPanels.SystemInfoToolsPanel;
import GUI.ToolPages.ToolCollectionPanels.WelcomePagePanel;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	final static String APP_TITLE = "Offline Helper";
	
    final static String FILE_SYSTEM_TOOLS_MENU = "File System Tools";
    final static String ENCRYPTION_AND_HASHING_TOOLS_MENU = "Encryption & Hashing Tools";
    final static String SYSTEM_INFO_MENU = "System Info";
    final static String PDF_TOOLS_MENU = "PDF Tools";
    final static String OTHERS_MENU = "Other";
    
    final static String REMOVE_DUPLICATE_FILES = "Remove Duplicate Files";
    final static String SORT_FILES_BY_EXTENSION = "Sort Files by Extension";
    final static String MASS_COPY_FOLDER_CONTENTS = "Mass copy Folder contents to Another Folder";
    final static String MASS_RENAME_FILES = "Mass rename Files in Folder";
    final static String UNZIP_OR_ZIP_FILES = "Unzip/Zip Files in a Folder";
    final static String MASS_CHANGE_FILETYPES = "Mass change Filetypes";
    final static String GET_PATHS_IN_FOLDER = "Get paths in Folder";
    final static String GET_FOLDER_SIZE_AND_FILE_COUNT = "Get Folder's Size & File count";
    final static String DELETE_ALL_FILES_IN_FOLDER = "Delete all Files in a Folder";
    
    final static String AES_TOOLS = "AES Tools";
    final static String AES_ENCRYPT_STRING = "Encrypt a string with AES";
    final static String AES_ENCRYPT_FILE = "Encrypt a File with AES";
    final static String AES_DECRYPT_STRING = "Decrypt a string with AES";
    final static String AES_DECRYPT_FILE = "Decrypt a File with AES";
    
    final static String MD5_TOOLS = "MD5 Tools";
    final static String CREATE_MD5_CHECKSUM = "Create a checksum for a File with MD5";
    final static String HASH_TEXT_WITH_MD5 = "Hash Text with MD5";
    final static String CHECK_MD5_CHECKSUM = "Create File's checksum with MD5";
    
    final static String PASSWORD_TOOLS = "Password Tools";
    final static String GENERATE_RANDOM_PASSWORD = "Generate random password";
    final static String CHECK_PASSWORD_STRENGTH = "Check password's strength";
    
    final static String PGP_TOOLS = "PGP Tools";
    final static String GENERATE_PGP_KEYPAIR = "Generate PGP Keypair";
    final static String ENCRYPT_WITH_PGP = "Encrypt with PGP";
    final static String DECRYPT_WITH_PGP = "Decrypt with PGP";
    final static String SIGN_WITH_PGP = "Create a digital signature with PGP";
    final static String VERIFY_SIGNATURE_WITH_PGP = "Verify a digital signature with PGP";
    
    final static String SHA_TOOLS = "SHA Tools";
    final static String CREATE_SHA_CHECKSUM = "Create a checksum for a File with SHA";
    final static String HASH_TEXT_WITH_SHA = "Hash Text with SHA";
    final static String CHECK_SHA_CHECKSUM = "Create File's checksum with SHA";
    
    final static String GET_OS_INFO = "Get Operating System information";
    final static String GET_NETWORK_INFO = "Get Network information";
    final static String GET_ENVIRONMENT_VARIABLES = "Get Environment Variables";
    final static String GET_HARDWARE_INFO = "Get Hardware information";
    final static String GET_JAVA_INFO = "Get Java Information";
    
    final static String MERGE_PDF_FILES = "Merge PDF Files";
    final static String SPLIT_PDF_FILE = "Split PDF File";
    final static String EXTRACT_TEXT_FROM_PDF_FILE = "Extract text from PDF File";
    final static String CONVERT_PDF_TO_ANOTHER_FORMAT = "Convert PDF File to Another format";
    final static String EXTRACT_PDF_FILE_METADATA = "Extract PDF File's metadata";
    final static String EDIT_PDF_METADATA = "Edit PDF File's metadata";
    
    final static String SETTINGS = "Settings";
    final static String WELCOME = "Welcome";
    final static String EXIT = "Exit";
    
	JPanel centerPanel = new CenterPanel();
    private CardLayout cardLayout = new CardLayout();
	
    JMenuBar mb = createMenuBar();  
    
    JMenu fileSystemToolMenu, encryptionAndHashingToolMenu, SystemInfoMenu, pdfToolsMenu, otherMenu;  
    JMenu aesToolsMenu, md5ToolsMenu, passwordToolsMenu, pgpToolsMenu, shaToolsMenu;
    
    JMenuItem removeDuplicateFilesMenuItem, sortFilesByERxtensionMenuItem, massCopyFolderContentsMenuItem, massRenameFilesMenuItem, unzipOrZipFilesMenuItem, 
    massChangeFiletypesMenuItem, getPathsInFolderMenuItem, getFolderSizeAndFileCountMenuItem, deleteAllFilesInFolderMenuItem;
    JMenuItem getOsInfoMenuItem, getNetworkInfoMenuItem, getEnvironmentVariablesMenuItem, getHardwareInfoMenuItem, getJavaInfoMenuItem;
    JMenuItem mergePdfFilesMenuItem, splitPdfFileMenuItem, extractTextFromPdfFileMenuItem, convertPdfToAnotherFormatMenuItem,
    extractPdfFileMetadataMenuItem, editPdfMetadataMenuItem;
    JMenuItem settingsMenuItem, exitMenuItem, welcomePage;
    
    JMenuItem aesEncryptStringMenuItem, aesEncryptFileMenuItem, aesDecryptFileMenuItem, aesDecryptStringMenuItem;
    JMenuItem createMd5ChecksumMenuItem, hashTextWithMd5MenuItem, checkMd5ChecksumMenuItem;
    JMenuItem generateRandomPasswordMenuItem, checkPasswordStrengthMenuItem;
    JMenuItem generatePgpKeypairMenuItem, encryptWithPgpMenuItem, decryptWithPgpMenuItem, signWithPgpMenuItem, verifyPgpSignatureMenuItem;
    JMenuItem createShaChecksumMenuItem, hashTextWithShaMenuItem, checkShaChecksumMenuItem;
    
    HashMap<String, JPanel> panels;
    
	public MainFrame(){
        
        try {
			this.setIconImage(ImageIO.read(new File("res/toolkit.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		panels = getPanels();
		
		mb = createMenuBar();
        this.setJMenuBar(mb);  
        
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1080,720));
		this.setTitle(APP_TITLE);
		
		centerPanel.setLayout(cardLayout);
		this.add(centerPanel, BorderLayout.CENTER);

		setPanels(panels);

		cardLayout.show(centerPanel, WELCOME);
		
		this.setVisible(true);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource() == exitMenuItem) {
    		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit program", JOptionPane.YES_NO_OPTION);
    		if(reply == JOptionPane.YES_OPTION) {
        		System.exit(0);
    		}
    	} else if(e.getSource() == removeDuplicateFilesMenuItem) {
        	cardLayout.show(centerPanel, REMOVE_DUPLICATE_FILES);
    	} else if(e.getSource() == sortFilesByERxtensionMenuItem) {
        	cardLayout.show(centerPanel, SORT_FILES_BY_EXTENSION);
    	} else if(e.getSource() == massCopyFolderContentsMenuItem) {
        	cardLayout.show(centerPanel, MASS_COPY_FOLDER_CONTENTS);
    	} else if(e.getSource() == massRenameFilesMenuItem) {
        	cardLayout.show(centerPanel, MASS_RENAME_FILES);
    	} else if(e.getSource() == unzipOrZipFilesMenuItem) {
        	cardLayout.show(centerPanel, UNZIP_OR_ZIP_FILES);
    	} else if(e.getSource() == massChangeFiletypesMenuItem) {
        	cardLayout.show(centerPanel, MASS_CHANGE_FILETYPES);
    	} else if(e.getSource() == getPathsInFolderMenuItem) {
        	cardLayout.show(centerPanel, GET_PATHS_IN_FOLDER);
    	} else if(e.getSource() == getFolderSizeAndFileCountMenuItem) {
        	cardLayout.show(centerPanel, GET_FOLDER_SIZE_AND_FILE_COUNT);
    	} else if(e.getSource() == deleteAllFilesInFolderMenuItem) {
        	cardLayout.show(centerPanel, DELETE_ALL_FILES_IN_FOLDER);
    	} else if(e.getSource() == getOsInfoMenuItem) {
        	cardLayout.show(centerPanel, GET_OS_INFO);
    	} else if(e.getSource() == getNetworkInfoMenuItem) {
        	cardLayout.show(centerPanel, GET_NETWORK_INFO);
    	} else if(e.getSource() == getEnvironmentVariablesMenuItem) {
        	cardLayout.show(centerPanel, GET_ENVIRONMENT_VARIABLES);
    	} else if(e.getSource() == getHardwareInfoMenuItem) {
        	cardLayout.show(centerPanel, GET_HARDWARE_INFO);
    	} else if(e.getSource() == getJavaInfoMenuItem) {
        	cardLayout.show(centerPanel, GET_JAVA_INFO);
    	} else if(e.getSource() == mergePdfFilesMenuItem) {
        	cardLayout.show(centerPanel, MERGE_PDF_FILES);
    	} else if(e.getSource() == splitPdfFileMenuItem) {
        	cardLayout.show(centerPanel, SPLIT_PDF_FILE);
    	} else if(e.getSource() == extractTextFromPdfFileMenuItem) {
        	cardLayout.show(centerPanel, EXTRACT_TEXT_FROM_PDF_FILE);
    	} else if(e.getSource() == convertPdfToAnotherFormatMenuItem) {
        	cardLayout.show(centerPanel, CONVERT_PDF_TO_ANOTHER_FORMAT);
    	} else if(e.getSource() == extractPdfFileMetadataMenuItem) {
        	cardLayout.show(centerPanel, EXTRACT_PDF_FILE_METADATA);
    	} else if(e.getSource() == editPdfMetadataMenuItem) {
        	cardLayout.show(centerPanel, EDIT_PDF_METADATA);
    	} else if(e.getSource() == settingsMenuItem) {
        	cardLayout.show(centerPanel, SETTINGS);
    	} else if(e.getSource() == aesEncryptStringMenuItem) {
        	cardLayout.show(centerPanel, AES_ENCRYPT_STRING);
    	} else if(e.getSource() == aesEncryptFileMenuItem) {
        	cardLayout.show(centerPanel, AES_ENCRYPT_FILE);
    	} else if(e.getSource() == aesDecryptFileMenuItem) {
        	cardLayout.show(centerPanel, AES_DECRYPT_STRING);
    	} else if(e.getSource() == aesDecryptStringMenuItem) {
        	cardLayout.show(centerPanel, AES_DECRYPT_FILE);
    	} else if(e.getSource() == createMd5ChecksumMenuItem) {
        	cardLayout.show(centerPanel, CREATE_MD5_CHECKSUM);
    	} else if(e.getSource() == hashTextWithMd5MenuItem) {
        	cardLayout.show(centerPanel, HASH_TEXT_WITH_MD5);
    	} else if(e.getSource() == checkMd5ChecksumMenuItem) {
        	cardLayout.show(centerPanel, CHECK_MD5_CHECKSUM);
    	} else if(e.getSource() == generateRandomPasswordMenuItem) {
        	cardLayout.show(centerPanel, GENERATE_RANDOM_PASSWORD);
    	} else if(e.getSource() == checkPasswordStrengthMenuItem) {
        	cardLayout.show(centerPanel, CHECK_PASSWORD_STRENGTH);
    	} else if(e.getSource() == generatePgpKeypairMenuItem) {
        	cardLayout.show(centerPanel, GENERATE_PGP_KEYPAIR);
    	} else if(e.getSource() == encryptWithPgpMenuItem) {
        	cardLayout.show(centerPanel, ENCRYPT_WITH_PGP);
    	} else if(e.getSource() == decryptWithPgpMenuItem) {
        	cardLayout.show(centerPanel, DECRYPT_WITH_PGP);
    	} else if(e.getSource() == signWithPgpMenuItem) {
        	cardLayout.show(centerPanel, SIGN_WITH_PGP);
    	} else if(e.getSource() == verifyPgpSignatureMenuItem) {
        	cardLayout.show(centerPanel, VERIFY_SIGNATURE_WITH_PGP);
    	} else if(e.getSource() == createShaChecksumMenuItem) {
        	cardLayout.show(centerPanel, CREATE_SHA_CHECKSUM);
    	} else if(e.getSource() == hashTextWithShaMenuItem) {
        	cardLayout.show(centerPanel, HASH_TEXT_WITH_SHA);
    	} else if(e.getSource() == checkShaChecksumMenuItem) {
        	cardLayout.show(centerPanel, CHECK_SHA_CHECKSUM);
    	} 

    }
	
	public JComponent getMainPanel() {
		return centerPanel;
	}
	
	private JMenuBar createMenuBar() {
		
		JMenuBar mb = new JMenuBar();
		
		fileSystemToolMenu = createFileSystemToolMenu();
		encryptionAndHashingToolMenu = createEncryptionAndHashingToolMenu();
		SystemInfoMenu = createSystemInfoMenu();
		pdfToolsMenu = createPdfToolsMenu();
		otherMenu = createOtherMenu();
        
        mb.add(fileSystemToolMenu);  
        mb.add(encryptionAndHashingToolMenu);  
        mb.add(SystemInfoMenu);  
        mb.add(pdfToolsMenu);  
        mb.add(otherMenu);  
        
        return mb;
		
	}
	
	private JMenu createOtherMenu() {
		
		JMenu menu = new JMenu(OTHERS_MENU);
		
	    settingsMenuItem = new JMenuItem(SETTINGS);
	    settingsMenuItem.addActionListener(this);
	    exitMenuItem = new JMenuItem(EXIT);
	    exitMenuItem.addActionListener(this);
		
	    menu.add(settingsMenuItem);
	    menu.add(exitMenuItem);
	    
		return menu;
	}

	private JMenu createPdfToolsMenu() {

		JMenu menu = new JMenu(PDF_TOOLS_MENU);
		
		mergePdfFilesMenuItem = new JMenuItem(MERGE_PDF_FILES);
		mergePdfFilesMenuItem.addActionListener(this);
		splitPdfFileMenuItem = new JMenuItem(SPLIT_PDF_FILE);
		splitPdfFileMenuItem.addActionListener(this);
		extractTextFromPdfFileMenuItem = new JMenuItem(EXTRACT_TEXT_FROM_PDF_FILE);
		extractTextFromPdfFileMenuItem.addActionListener(this);
		convertPdfToAnotherFormatMenuItem = new JMenuItem(CONVERT_PDF_TO_ANOTHER_FORMAT);
		convertPdfToAnotherFormatMenuItem.addActionListener(this);
	    extractPdfFileMetadataMenuItem = new JMenuItem(EXTRACT_PDF_FILE_METADATA);
	    extractPdfFileMetadataMenuItem.addActionListener(this);
	    editPdfMetadataMenuItem = new JMenuItem(EDIT_PDF_METADATA);
	    editPdfMetadataMenuItem.addActionListener(this);
		
	    menu.add(mergePdfFilesMenuItem);
	    menu.add(splitPdfFileMenuItem);
	    menu.add(extractTextFromPdfFileMenuItem);
	    menu.add(convertPdfToAnotherFormatMenuItem);
	    menu.add(extractPdfFileMetadataMenuItem);
	    menu.add(editPdfMetadataMenuItem);
	    
		return menu;
	}

	private JMenu createSystemInfoMenu() {

		JMenu menu = new JMenu(SYSTEM_INFO_MENU);
		
		getOsInfoMenuItem = new JMenuItem(GET_OS_INFO);
		getNetworkInfoMenuItem = new JMenuItem(GET_NETWORK_INFO); 
		getEnvironmentVariablesMenuItem = new JMenuItem(GET_ENVIRONMENT_VARIABLES);
		getHardwareInfoMenuItem = new JMenuItem(GET_HARDWARE_INFO);
		getJavaInfoMenuItem = new JMenuItem(GET_JAVA_INFO);
		
		getOsInfoMenuItem.addActionListener(this);
		getNetworkInfoMenuItem.addActionListener(this);
		getEnvironmentVariablesMenuItem.addActionListener(this);
		getHardwareInfoMenuItem.addActionListener(this);
		getJavaInfoMenuItem.addActionListener(this);
		
	    menu.add(getOsInfoMenuItem);
	    menu.add(getNetworkInfoMenuItem);
	    menu.add(getEnvironmentVariablesMenuItem);
	    menu.add(getHardwareInfoMenuItem);
	    menu.add(getJavaInfoMenuItem);
		
		return menu;
	}

	private JMenu createEncryptionAndHashingToolMenu() {

		JMenu menu = new JMenu(ENCRYPTION_AND_HASHING_TOOLS_MENU);
		
	    aesToolsMenu = createAesToolMenu();
	    md5ToolsMenu = createMd5ToolMenu();
	    passwordToolsMenu = createPasswordToolMenu();
	    pgpToolsMenu = createPgpToolsMenu();
	    shaToolsMenu = createShaToolsMenu();
		
	    menu.add(aesToolsMenu);
	    menu.add(md5ToolsMenu);
	    menu.add(passwordToolsMenu);
	    menu.add(pgpToolsMenu);
	    menu.add(shaToolsMenu);
	    
		return menu;
	}

	private JMenu createShaToolsMenu() {
		JMenu menu = new JMenu(SHA_TOOLS);
		
		createShaChecksumMenuItem = new JMenuItem(CREATE_SHA_CHECKSUM);
		createShaChecksumMenuItem.addActionListener(this);
		hashTextWithShaMenuItem = new JMenuItem(HASH_TEXT_WITH_SHA);
		hashTextWithShaMenuItem.addActionListener(this);
		checkShaChecksumMenuItem = new JMenuItem(CHECK_SHA_CHECKSUM);
		checkShaChecksumMenuItem.addActionListener(this);
		
	    menu.add(createShaChecksumMenuItem);
	    menu.add(hashTextWithShaMenuItem);
	    menu.add(checkShaChecksumMenuItem);
		
		return menu;
	}

	private JMenu createPgpToolsMenu() {
		
		JMenu menu = new JMenu(PGP_TOOLS);
		
		generatePgpKeypairMenuItem = new JMenuItem(GENERATE_PGP_KEYPAIR);
		generatePgpKeypairMenuItem.addActionListener(this);
		encryptWithPgpMenuItem = new JMenuItem(ENCRYPT_WITH_PGP);
		encryptWithPgpMenuItem.addActionListener(this);
		decryptWithPgpMenuItem = new JMenuItem(DECRYPT_WITH_PGP);
		decryptWithPgpMenuItem.addActionListener(this);
		signWithPgpMenuItem = new JMenuItem(SIGN_WITH_PGP);
		signWithPgpMenuItem.addActionListener(this);
		verifyPgpSignatureMenuItem = new JMenuItem(VERIFY_SIGNATURE_WITH_PGP);
		verifyPgpSignatureMenuItem.addActionListener(this);
		
	    menu.add(generatePgpKeypairMenuItem);
	    menu.add(encryptWithPgpMenuItem);
	    menu.add(decryptWithPgpMenuItem);
	    menu.add(signWithPgpMenuItem);
	    menu.add(verifyPgpSignatureMenuItem);
	    
		return menu;
	}

	private JMenu createPasswordToolMenu() {
		JMenu menu = new JMenu(PASSWORD_TOOLS);
		
		generateRandomPasswordMenuItem = new JMenuItem(GENERATE_RANDOM_PASSWORD);
		generateRandomPasswordMenuItem.addActionListener(this);
		checkPasswordStrengthMenuItem = new JMenuItem(CHECK_PASSWORD_STRENGTH);
		checkPasswordStrengthMenuItem.addActionListener(this);
		
	    menu.add(generateRandomPasswordMenuItem);
	    menu.add(checkPasswordStrengthMenuItem);
	    
		return menu;
	}

	private JMenu createMd5ToolMenu() {
		JMenu menu = new JMenu(MD5_TOOLS);
		
		createMd5ChecksumMenuItem = new JMenuItem(CREATE_MD5_CHECKSUM);
		createMd5ChecksumMenuItem.addActionListener(this);
		hashTextWithMd5MenuItem = new JMenuItem(HASH_TEXT_WITH_MD5);
		hashTextWithMd5MenuItem.addActionListener(this);
		checkMd5ChecksumMenuItem = new JMenuItem(CHECK_MD5_CHECKSUM);
		checkMd5ChecksumMenuItem.addActionListener(this);
		
	    menu.add(createMd5ChecksumMenuItem);
	    menu.add(hashTextWithMd5MenuItem);
	    menu.add(checkMd5ChecksumMenuItem);
		
		return menu;
	}

	private JMenu createAesToolMenu() {

		JMenu menu = new JMenu(AES_TOOLS);
		
		aesEncryptStringMenuItem = new JMenuItem(AES_ENCRYPT_STRING);
		aesEncryptStringMenuItem.addActionListener(this);
		aesEncryptFileMenuItem = new JMenuItem(AES_ENCRYPT_FILE);
		aesEncryptFileMenuItem.addActionListener(this);
		aesDecryptFileMenuItem = new JMenuItem(AES_DECRYPT_STRING);
		aesDecryptFileMenuItem.addActionListener(this);
		aesDecryptStringMenuItem = new JMenuItem(AES_DECRYPT_FILE);
		aesDecryptStringMenuItem.addActionListener(this);
		
	    menu.add(aesEncryptStringMenuItem);
	    menu.add(aesEncryptFileMenuItem);
	    menu.add(aesDecryptFileMenuItem);
	    menu.add(aesDecryptStringMenuItem);
		
		return menu;
	}

	private JMenu createFileSystemToolMenu() {
		
		JMenu menu = new JMenu(FILE_SYSTEM_TOOLS_MENU);
		
	    removeDuplicateFilesMenuItem = new JMenuItem(REMOVE_DUPLICATE_FILES);
	    removeDuplicateFilesMenuItem.addActionListener(this);
	    sortFilesByERxtensionMenuItem = new JMenuItem(SORT_FILES_BY_EXTENSION);
	    sortFilesByERxtensionMenuItem.addActionListener(this);
	    massCopyFolderContentsMenuItem = new JMenuItem(MASS_COPY_FOLDER_CONTENTS);
	    massCopyFolderContentsMenuItem.addActionListener(this);
	    massRenameFilesMenuItem = new JMenuItem(MASS_RENAME_FILES);
	    massRenameFilesMenuItem.addActionListener(this);
	    unzipOrZipFilesMenuItem = new JMenuItem(UNZIP_OR_ZIP_FILES);
	    unzipOrZipFilesMenuItem.addActionListener(this);
	    massChangeFiletypesMenuItem = new JMenuItem(MASS_CHANGE_FILETYPES);
	    massChangeFiletypesMenuItem.addActionListener(this);
	    getPathsInFolderMenuItem = new JMenuItem(GET_PATHS_IN_FOLDER);
	    getPathsInFolderMenuItem.addActionListener(this);
	    getFolderSizeAndFileCountMenuItem = new JMenuItem(GET_FOLDER_SIZE_AND_FILE_COUNT);
	    getFolderSizeAndFileCountMenuItem.addActionListener(this);
	    deleteAllFilesInFolderMenuItem = new JMenuItem(DELETE_ALL_FILES_IN_FOLDER);
	    deleteAllFilesInFolderMenuItem.addActionListener(this);
		
	    menu.add(removeDuplicateFilesMenuItem);
	    menu.add(sortFilesByERxtensionMenuItem);
	    menu.add(massCopyFolderContentsMenuItem);
	    menu.add(massRenameFilesMenuItem);
	    menu.add(unzipOrZipFilesMenuItem);
	    menu.add(massChangeFiletypesMenuItem);
	    menu.add(getPathsInFolderMenuItem);
	    menu.add(getFolderSizeAndFileCountMenuItem);
	    menu.add(deleteAllFilesInFolderMenuItem);
	    
	    return menu;
	}
	
	private HashMap<String, JPanel> getPanels(){
		
		HashMap<String, JPanel> pagePanels = new HashMap<String, JPanel>();
		
		JPanel systemInfoToolsPage = new SystemInfoToolsPanel(SYSTEM_INFO_MENU);
		JPanel fileSystemToolsPage = new FileSystemToolsPanel(FILE_SYSTEM_TOOLS_MENU);
		JPanel encryptionAndHashingToolsPage  = new EncryptionAndHashingToolsPanel(ENCRYPTION_AND_HASHING_TOOLS_MENU);
		JPanel pdfToolsPage = new PdfToolsPanel(PDF_TOOLS_MENU);
		JPanel settingsPage = new SettingsPanel(SETTINGS);
		JPanel removeDuplicateFilesPage = new RemoveDuplicateFilesPanel(REMOVE_DUPLICATE_FILES);
		JPanel sortFilesByExtensionPage = new SortFilesByExtensionPanel(SORT_FILES_BY_EXTENSION);
		JPanel massCopyFolderContentsPage = new MassCopyFolderContentsPanel(MASS_COPY_FOLDER_CONTENTS);
		JPanel massRenameFilesPage = new MassRenameFilesPanel(MASS_RENAME_FILES);
		JPanel unzipOrZipFilesPage = new UnzipOrZipFilesPanel(UNZIP_OR_ZIP_FILES);
		JPanel massChangeFileTypesPage = new MassChangeFiletypesPanel(MASS_CHANGE_FILETYPES);
		JPanel getFolderPathsPage = new GetPathsInFolderPanel(GET_PATHS_IN_FOLDER);
		JPanel getFolderSizeAndFileCountPage = new GetFolderSizeAndFileCountPanel(GET_FOLDER_SIZE_AND_FILE_COUNT);
		JPanel deleteAllFilesInFolderPage = new DeleteAllFilesInFolderPanel(DELETE_ALL_FILES_IN_FOLDER);
		JPanel aesToolsPage = new AESToolsPanel(AES_TOOLS);
		JPanel aesEncryptStringPage = new AESEncryptStringPanel(AES_ENCRYPT_STRING);
		JPanel aesEncryptFilePage = new AESEncryptFilePanel(AES_ENCRYPT_FILE);
		JPanel aesDecryptStringPage = new AESDecryptStringPanel(AES_DECRYPT_STRING);
		JPanel aesDecryptFilePage = new AESDecryptFilePanel(AES_DECRYPT_FILE);
		JPanel md5ToolsPage = new MD5ToolsPanel(MD5_TOOLS);
		JPanel createMd5ChecksumPage = new CreateMD5ChecksumPanel(CREATE_MD5_CHECKSUM);
		JPanel hashTextWithMd5Page = new HashTextWithMD5Panel(HASH_TEXT_WITH_MD5);
		JPanel checkMd5ChecksumPage = new CheckMD5ChecksumPanel(CHECK_MD5_CHECKSUM);
		JPanel passwordToolsPage = new PasswordToolsPanel(PASSWORD_TOOLS);
		JPanel generateRandomPasswordPage = new CreateRandomPasswordPanel(GENERATE_RANDOM_PASSWORD);
		JPanel checkPasswordStrengthPage = new CheckPasswordStrengthPanel(CHECK_PASSWORD_STRENGTH);
		JPanel pgpToolsPage = new PGPToolsPanel(PGP_TOOLS);
		JPanel generatePgpKeypairPage = new GeneratePGPKeypairPanel(GENERATE_PGP_KEYPAIR);
		JPanel encryptWithPgpPage = new EncryptWithPGPPanel(ENCRYPT_WITH_PGP);
		JPanel decryptWithPgpPage = new DecryptWithPGPPanel2(DECRYPT_WITH_PGP);
		JPanel signWithPgpPage = new SignWithPGPPanel(SIGN_WITH_PGP);
		JPanel verifySignatureWithPgpPage = new VerifySignatureWithPGPPanel2(VERIFY_SIGNATURE_WITH_PGP);
		JPanel shaToolsPage = new SHAToolsPanel(SHA_TOOLS);
		JPanel createShaChecksumPage = new CreateSHAChecksumPanel(CREATE_SHA_CHECKSUM);
		JPanel hashTextWithShaPage = new HashTextWithSHAPanel(HASH_TEXT_WITH_SHA);
		JPanel checkShaChecksumPage = new VerifySHAChecksumPanel(CHECK_SHA_CHECKSUM);
		JPanel getOsInfoPage = new GetOperatingSystemInfoPanel(GET_OS_INFO);
		JPanel getNetworkInfoPage = new GetNetworkInfoPanel(GET_NETWORK_INFO);
		JPanel getEnvVariablesPage = new GetEnvironmentVariablesPanel(GET_ENVIRONMENT_VARIABLES);
		JPanel getHardwareInfoPage = new GetHardwareInfoPanel(GET_HARDWARE_INFO);
		JPanel getJavaInfoPage = new GetJavaInfoPanel(GET_JAVA_INFO);
		JPanel mergePdfFilesPage = new MergePDFFilesPanel(MERGE_PDF_FILES);
		JPanel splitPdfFilePage = new SplitPDFFilePanel(SPLIT_PDF_FILE);
		JPanel extractTextFromPdfFilePage = new ExtractTextFromPDFFilePanel(EXTRACT_TEXT_FROM_PDF_FILE);
		JPanel convertPdfFileToAnotherFormatPage = new ConvertPDFFileToAnotherFormatPanel(CONVERT_PDF_TO_ANOTHER_FORMAT);
		JPanel extractPdfFileMetadataPage = new ExtractPDFFileMetadataPanel(EXTRACT_PDF_FILE_METADATA);
		JPanel editPdfFileMetadataPage = new EditPDFFileMetadataPanel(EDIT_PDF_METADATA);
		JPanel welcomePage = new WelcomePagePanel(WELCOME);
		
		pagePanels.put(SYSTEM_INFO_MENU, systemInfoToolsPage);
		pagePanels.put(FILE_SYSTEM_TOOLS_MENU, fileSystemToolsPage);
		pagePanels.put(ENCRYPTION_AND_HASHING_TOOLS_MENU, encryptionAndHashingToolsPage);
		pagePanels.put(PDF_TOOLS_MENU, pdfToolsPage);
		pagePanels.put(SETTINGS, settingsPage);
		pagePanels.put(REMOVE_DUPLICATE_FILES, removeDuplicateFilesPage);
		pagePanels.put(SORT_FILES_BY_EXTENSION, sortFilesByExtensionPage);
		pagePanels.put(MASS_COPY_FOLDER_CONTENTS, massCopyFolderContentsPage);
		pagePanels.put(MASS_RENAME_FILES, massRenameFilesPage);
		pagePanels.put(UNZIP_OR_ZIP_FILES, unzipOrZipFilesPage);
		pagePanels.put(MASS_CHANGE_FILETYPES, massChangeFileTypesPage);
		pagePanels.put(GET_PATHS_IN_FOLDER, getFolderPathsPage);
		pagePanels.put(GET_FOLDER_SIZE_AND_FILE_COUNT, getFolderSizeAndFileCountPage);
		pagePanels.put(DELETE_ALL_FILES_IN_FOLDER, deleteAllFilesInFolderPage);
		pagePanels.put(AES_TOOLS, aesToolsPage);
		pagePanels.put(AES_ENCRYPT_STRING, aesEncryptStringPage);
		pagePanels.put(AES_ENCRYPT_FILE, aesEncryptFilePage);
		pagePanels.put(AES_DECRYPT_STRING, aesDecryptStringPage);
		pagePanels.put(AES_DECRYPT_FILE, aesDecryptFilePage);
		pagePanels.put(MD5_TOOLS, md5ToolsPage);
		pagePanels.put(CREATE_MD5_CHECKSUM, createMd5ChecksumPage);
		pagePanels.put(HASH_TEXT_WITH_MD5, hashTextWithMd5Page);
		pagePanels.put(CHECK_MD5_CHECKSUM, checkMd5ChecksumPage);
		pagePanels.put(PASSWORD_TOOLS, passwordToolsPage);
		pagePanels.put(GENERATE_RANDOM_PASSWORD, generateRandomPasswordPage);
		pagePanels.put(CHECK_PASSWORD_STRENGTH, checkPasswordStrengthPage);
		pagePanels.put(PGP_TOOLS, pgpToolsPage);
		pagePanels.put(GENERATE_PGP_KEYPAIR, generatePgpKeypairPage);
		pagePanels.put(ENCRYPT_WITH_PGP, encryptWithPgpPage);
		pagePanels.put(DECRYPT_WITH_PGP, decryptWithPgpPage);
		pagePanels.put(SIGN_WITH_PGP, signWithPgpPage);
		pagePanels.put(VERIFY_SIGNATURE_WITH_PGP, verifySignatureWithPgpPage);
		pagePanels.put(SHA_TOOLS, shaToolsPage);
		pagePanels.put(CREATE_SHA_CHECKSUM, createShaChecksumPage);
		pagePanels.put(HASH_TEXT_WITH_SHA, hashTextWithShaPage);
		pagePanels.put(CHECK_SHA_CHECKSUM, checkShaChecksumPage);
		pagePanels.put(GET_OS_INFO, getOsInfoPage);
		pagePanels.put(GET_NETWORK_INFO, getNetworkInfoPage);
		pagePanels.put(GET_ENVIRONMENT_VARIABLES, getEnvVariablesPage);
		pagePanels.put(GET_HARDWARE_INFO, getHardwareInfoPage);
		pagePanels.put(GET_JAVA_INFO, getJavaInfoPage);
		pagePanels.put(MERGE_PDF_FILES, mergePdfFilesPage);
		pagePanels.put(SPLIT_PDF_FILE, splitPdfFilePage);
		pagePanels.put(EXTRACT_TEXT_FROM_PDF_FILE, extractTextFromPdfFilePage);
		pagePanels.put(CONVERT_PDF_TO_ANOTHER_FORMAT, convertPdfFileToAnotherFormatPage);
		pagePanels.put(EXTRACT_PDF_FILE_METADATA, extractPdfFileMetadataPage);
		pagePanels.put(EDIT_PDF_METADATA, editPdfFileMetadataPage);
		pagePanels.put(WELCOME, welcomePage);
		
		return pagePanels;
		
	}
	
    private void setPanels(HashMap<String, JPanel> panels) {
        for (String name : panels.keySet()) {
            centerPanel.add(panels.get(name), name);
        }
    }
	
}
