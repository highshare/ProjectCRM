package pl.mwa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVUtils {

	public static List<String[]> reader(String url){

		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(url));
			List<String[]> myEntries = reader.readAll();
			reader.close();
			return myEntries;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
}
