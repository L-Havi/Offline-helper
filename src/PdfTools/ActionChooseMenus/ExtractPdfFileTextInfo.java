package PdfTools.ActionChooseMenus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.BreakIterator;
import java.util.Locale;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractPdfFileTextInfo {

	public String getInfo(String saveFolder, int extractPageCount, int extractWordCount, int extractSentenceCount, int extractCharacterCount) {
		
		Locale currentLocale = new Locale("en", "US");
	    BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
	    File textFile = null;
	    
		try {
			int i = saveFolder.lastIndexOf("\\");
	    	String[] a =  {saveFolder.substring(0, i), saveFolder.substring(i)};
	    	i = a[1].lastIndexOf(".");
	    	String[] b =  {a[1].substring(0, i), a[1].substring(i)};
	    	System.out.println(a[0] + "\\" + b[0] + ".txt");
			textFile = new File(a[0] + "\\" + b[0] + "_text_data" + ".txt");
			FileOutputStream fos = new FileOutputStream(textFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	
			bw.write("------------------------------------------------------------------------");
			bw.newLine();
			bw.write("PDF File " + saveFolder + "'s text data.");
			bw.newLine();
			bw.write("------------------------------------------------------------------------");
			bw.newLine();
	
			File pdf = new File(saveFolder);
			PDDocument document = Loader.loadPDF(pdf);
			PDFTextStripper pdfStripper = new PDFTextStripper();
		      String text = pdfStripper.getText(document);
			
			if(extractPageCount == 1) {
				int pageCount = document.getNumberOfPages();
				bw.newLine();
				bw.write("Page Count: " + pageCount);
				bw.newLine();
			}
			if(extractWordCount == 1) {
				int wordCount = wordCount(text);
				bw.newLine();
				bw.write("Word Count: " + wordCount);
				bw.newLine();
			}
			if(extractSentenceCount == 1) {
				int sentenceCount = sentenceCount(text, sentenceIterator);
				bw.newLine();
				bw.write("Sentence Count: " + sentenceCount);
				bw.newLine();
			}
			if(extractCharacterCount == 1) {
				int count = 0; 
				
		        for(int j = 0; j < text.length(); j++) {    
		            if(text.charAt(j) != ' ')    
		                count++;    
		        }
		        
				bw.newLine();
				bw.write("Character Count: " + count);
				bw.newLine();
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textFile.getAbsolutePath();
	}
	
	private static int wordCount(String word) { 
		
		if (word == null || word.isEmpty()) { 
			return 0; 
		} 
		
		int wordCount = 0; 
		boolean isWord = false; 
		int endOfLine = word.length() - 1; 
		char[] characters = word.toCharArray(); 
		
		for (int i = 0; i < characters.length; i++) { 
			if (Character.isLetter(characters[i]) && i != endOfLine) { 
				isWord = true; 
			} else if (!Character.isLetter(characters[i]) && isWord) { 
				wordCount++; 
				isWord = false; 
			} else if (Character.isLetter(characters[i]) && i == endOfLine) { 
				wordCount++; 
			} 
		}
		return wordCount; 
	}

	private static int sentenceCount(String word, BreakIterator iterator) {
	    StringBuffer markers = new StringBuffer();
	    markers.setLength(word.length() + 1);
	    for (int k = 0; k < markers.length(); k++) {
	        markers.setCharAt(k, ' ');
	    }
	    int count = 0;
	    iterator.setText(word);
	    int boundary = iterator.first();
	    while (boundary != BreakIterator.DONE) {
	        markers.setCharAt(boundary, '^');
	        ++count;
	        boundary = iterator.next();
	    }
	    return (count - 1);
	}
	
}
