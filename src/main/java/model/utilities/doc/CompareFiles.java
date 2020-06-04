package model.utilities.doc;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class CompareFiles {

	public void compareNaturalOrder(File[] files) {

		Arrays.sort(files, comparatorCrescent());
	};
	
	public void compareReverseOrder(File[] files) {

		Arrays.sort(files, comparatorDecrescent());
	};

	public Comparator<File> comparatorDecrescent() {

		return new Comparator<File>() {

			@Override
			public int compare(File f1, File f2) {
				long result = f2.lastModified() - f1.lastModified();
				if (f1.isFile()) {
					return 1;
				}
				if (result > 0) {
					return -1;
				} else if (result < 0) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}
	
	public Comparator<File> comparatorCrescent() {

		return new Comparator<File>() {

			@Override
			public int compare(File f1, File f2) {
				long result = f2.lastModified() - f1.lastModified();
			
				if (result > 0) {
					return -1;
				} else if (result < 0) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}

}
