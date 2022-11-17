package PdfTools.ActionChooseMenus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.fit.pdfdom.PDFDomTree;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import Titles.ToolTitles.PdfTitles.ConvertPdfActionTitle;
import Utilities.ExtractPageNumbersFromString;
import Utilities.UserInput.ChooseConvertFormat;
import Utilities.UserInput.ChooseExcludedPages;
import Utilities.UserInput.ChooseFiles;
import Utilities.UserInput.Name;
import WindowsResources.SourceFolder;

public class ConvertPdfAction {

	ChooseConvertFormat chooseConvertFormat = new ChooseConvertFormat();
	Scanner scanner = new Scanner(System.in);
	SourceFolder sourceFolder = new SourceFolder();
	Name name = new Name();
	ConvertPdfActionTitle convertPdfActionTitle = new ConvertPdfActionTitle();
	ChooseFiles chooseFiles = new ChooseFiles();
	PDFMergerUtility PDFmerger = new PDFMergerUtility();
	ChooseExcludedPages chooseExcludedPages = new ChooseExcludedPages();
	ExtractPageNumbersFromString extractPageNumbersFromString = new ExtractPageNumbersFromString();

	public void convertPdfFile() {

		String actionChoice;

		String originalPdf = "";
		String convertFormat = "";

		String divider = "\\";
		if(!System.getProperty("os.name").toLowerCase().contains("windows")) {
			divider = "/";
		}

		boolean run = true;

		while(run) {
			convertPdfActionTitle.printTitle(originalPdf, convertFormat);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				originalPdf = sourceFolder.getSourceFile();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				convertFormat = chooseConvertFormat.chooseAlgorithm();
			} else if(actionChoice.toLowerCase().trim().equals("3") && originalPdf != "" && originalPdf != "exit" && convertFormat != "" && convertFormat != "exit") {
				if(convertFormat.equals("html")) {
					//generateHTMLFromPDF(originalPdf);
					System.out.println("Successfully converted " + originalPdf + " to html File");
				} else if(convertFormat.equals("jpeg") || convertFormat.equals("jpg") || convertFormat.equals("gif") || convertFormat.equals("tiff") || convertFormat.equals("png")){
					//generateImageFromPDF(originalPdf, convertFormat);
					System.out.println("Successfully converted " + originalPdf + " to " + convertFormat + " File");
				}else {
					//generateDocxFromPDF(originalPdf);
					System.out.println("Successfully converted " + originalPdf + " to docx File");
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("4")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
		}

	}

	public void generateHTMLFromPDF(String filepath, String filename, String outputFolder) throws IOException {
	    PDDocument pdf = Loader.loadPDF(new File(filepath));
    	int i = filename.lastIndexOf('.');
    	String split = filename.substring(0,i);
	    Writer output = new PrintWriter(outputFolder + "\\" + split + ".html", "utf-8");
	    new PDFDomTree().writeText(pdf, output);
	    output.close();
	}

	public void generateImageFromPDF(String filepath, String extension, String filename, String outputFolder) throws IOException {
	    PDDocument document = Loader.loadPDF(new File(filepath));
	    PDFRenderer pdfRenderer = new PDFRenderer(document);
    	int i = filename.lastIndexOf('.');
    	String split = filename.substring(0,i);
	    String outputFile = outputFolder + "\\" +  split + "." + extension;
	    for (int page = 0; page < document.getNumberOfPages(); ++page) {
	        BufferedImage bim = pdfRenderer.renderImageWithDPI(
	          page, 300, ImageType.RGB);
	        ImageIOUtil.writeImage(
	          bim, String.format(outputFile, page + 1, extension), 300);
	    }
	    document.close();
	}

	public void generateDocxFromPDF(String filepath, String filename, String outputFolder) throws IOException {
		XWPFDocument doc = new XWPFDocument();
		String pdf = filepath;
		PdfReader reader = new PdfReader(pdf);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);

    	int index = filename.lastIndexOf('.');
    	String split = filename.substring(0,index);

		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
		    TextExtractionStrategy strategy =
		      parser.processContent(i, new SimpleTextExtractionStrategy());
		    String text = strategy.getResultantText();
		    XWPFParagraph p = doc.createParagraph();
		    XWPFRun run = p.createRun();
		    run.setText(text);
		    run.addBreak(BreakType.PAGE);
		}
		FileOutputStream out = new FileOutputStream(outputFolder + "\\" + split + ".docx");
		doc.write(out);
	}

}
