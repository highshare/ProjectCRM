package pl.mwa.representative;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

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

	
	
}
