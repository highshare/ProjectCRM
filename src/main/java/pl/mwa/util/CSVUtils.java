package pl.mwa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

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
	
	public static List buildListFromCSV(String filename, Class aClass) {
		Path fullPath = Paths.get(System.getProperty("user.home"),"Documents", filename);
		try {
			List beans = new CsvToBeanBuilder(new FileReader(fullPath.toString()))
					.withThrowExceptions(true).withType(aClass).build().parse();
			return beans;
		} catch (IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	
	public static void exportListToCSV(String filename, List beans) {
		Path fullPath = Paths.get(System.getProperty("user.home"),"Documents", filename);
		try {
			Writer writer = new FileWriter(fullPath.toString());
	        StatefulBeanToCsvBuilder beanToCsv = new StatefulBeanToCsvBuilder(writer);
	        try {
				beanToCsv.build().write(beans);
				writer.close();
			} catch (CsvDataTypeMismatchException e) {
				e.printStackTrace();
			} catch (CsvRequiredFieldEmptyException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
}
