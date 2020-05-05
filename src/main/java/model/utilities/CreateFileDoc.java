package model.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class CreateFileDoc {
	
	public void createEvidenceInDoc(String suiteName, String classtestName, String executionTestName, String timeStamps, String result) {
		
		File dir = new File(System.getProperty("user.dir") +File.separator+ "outPut" );
		File[] files = dir.listFiles();
		Arrays.sort(files, new Comparator<File>() {

			@Override
			public int compare(File f1, File f2) {
				long result = f2.lastModified() - f1.lastModified();
				if(f1.isFile()) {
					return 1;
				}
				if(result > 0) {
					return 1;
				}else if(result < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
	//	File folder = new File(files[0].getAbsolutePath() +File.separator+ suiteName + File.separator +classtestName +File.separator);
		
		
		
	}
	
}
