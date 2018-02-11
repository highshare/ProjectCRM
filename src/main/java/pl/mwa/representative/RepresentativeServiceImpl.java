package pl.mwa.representative;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import pl.mwa.client.Client;

@Service
public class RepresentativeServiceImpl implements RepresentativeService {

	@Autowired
	RepresentativeRepository rr;
	
	public RepresentativeServiceImpl(RepresentativeRepository rr) {
		this.rr = rr;
	}
	
	
	@Override
	public Representative findByClient(Client client) {
		return rr.findByClient(client);
	}

	
	@Override
	public void remove(long id) {
		Representative rep = rr.findOne(id);
		rep.setActive(false);
		rr.save(rep);
	}

	
	
	@Override
	public void save(Representative representative) {
		rr.save(representative);
	}


	@Override
	public Collection<Representative> findAllByActiveTrue() {
		return rr.findAllByActiveTrue();
	}


	@Override
	public Page<Representative> findAll(Pageable pageable) {
		return rr.findAllByActiveTrue(pageable);
	}


	@Override
	public Representative findOne(long id) {
		return rr.findOne(id);
	}

	
	public List<Representative> buildListFromCSV(String filename) {
		Path fullPath = Paths.get(System.getProperty("user.home"),"Documents", filename);
		try {
			List<Representative> representatives = new CsvToBeanBuilder(new FileReader(fullPath.toString()))
					.withThrowExceptions(true).withType(Representative.class).build().parse();
			return representatives;
		} catch (IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public void saveToDB(List<Representative> representatives) {
		rr.save(representatives);
	}
	
	
	public void importDataFromCSV(String filename) {
		List<Representative> representatives = buildListFromCSV(filename);
		saveToDB(representatives);
	}
	
	public void exportDataToCSV(String filename) {
		List<Representative> representatives = buildListFromDB();
		exportListToCSV(filename, representatives);
	}
	
	public List<Representative> buildListFromDB() {
		return rr.findAll();
	}
	
	public void exportListToCSV(String filename, List<Representative> representatives) {
		Path fullPath = Paths.get(System.getProperty("user.home"),"Documents", filename);
		try {
			Writer writer = new FileWriter(fullPath.toString());
	        StatefulBeanToCsvBuilder<Representative> beanToCsv = new StatefulBeanToCsvBuilder<Representative>(writer);
	        try {
				beanToCsv.build().write(representatives);
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