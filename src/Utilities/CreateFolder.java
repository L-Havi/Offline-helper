package Utilities;

import java.io.File;
import java.util.List;

public class CreateFolder {

		public void createFolder(String path) {
			File folder = new File(path);

			if (!folder.exists()) {
				folder.mkdir();
			}
		}

		public void createFolders(List<String> paths) {
			for(String path : paths) {
				File folder = new File(path);

				if (!folder.exists()) {
					folder.mkdir();
				}
			}
		}
}
