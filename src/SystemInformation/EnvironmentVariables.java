package SystemInformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class EnvironmentVariables {
	public void getAllEnvironmentVariables(String savePath, int saveFileType) throws IOException {
        Map<String, String> map = System.getenv();
        System.out.println("System's environment variables");
		System.out.println("------------------------------------------------------------------------");

        
		List<String> info = new ArrayList<>();
		
		Set<String> keySet = map.keySet();
		
		ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
		
		for(String key : listOfKeys) {
			String temp = map.get(key);
			String env = key + ": " + temp;
			info.add(env);
		}
        
		if(saveFileType == 0) {
			
			File pdf = new File("res/environment_variables.pdf");
			
			PDDocument pDDocument = Loader.loadPDF(pdf);
			PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
			if(pDAcroForm != null) {
				
				int count = 1;
				
				try {
					PDField field = pDAcroForm.getField("date");
					field.setValue(java.time.LocalDate.now().toString());
					
					for(String item : info) {
						if(count < 54) {
							field = pDAcroForm.getField(Integer.toString(count));
							if(item != null) {
								field.setValue(item);
							}
						}
						count++;
					}

				} finally {
					savePath += "\\environment_variables.pdf";
					pDDocument.save(savePath);
					File result = new File(savePath);
					pDDocument.close();
				}
				
			}
			
		} else {
			File textFile = new File(savePath + "\\environment_variables.txt");
			FileOutputStream fos = new FileOutputStream(textFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			bw.write("------------------------------------------------------------------------");
			bw.newLine();
			bw.write("Environment Variables: ");
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
}
