package pl.mwa.representative;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.mwa.client.Client;
import pl.mwa.util.CSVUtils;

@Service
public class RepresentativeServiceImpl implements RepresentativeService {

	@Autowired
	RepresentativeRepository rr;
	
	public RepresentativeServiceImpl(RepresentativeRepository rr) {
		this.rr = rr;
	}
	
	
	@Override
	public Representative findByClient(Client client) {
		return jsonNullReference(rr.findByClient(client));
	}

	
	@Override
	public void remove(long id) {
		Representative rep = rr.findOne(id);
		rep.setActive(false);
		rr.save(rep);
	}

	
	
	@Override
	public void save(Representative representative) {
		representative.setActive(true);
		rr.save(representative);
	}

	
	public void update(Representative representative) {
		rr.save(representative);
	}

	@Override
	public List<Representative> findAllByActiveTrue() {
		return jsonListNullReference(rr.findAllByActiveTrue());
	}


	@Override
	public Page<Representative> findAll(Pageable pageable) {
		return rr.findAllByActiveTrue(pageable);
	}


	@Override
	public Representative findOne(long id) {
		return jsonNullReference(rr.findOne(id));
	}

	
	Representative jsonNullReference(Representative representative) {
		if (representative != null) {
			if (representative.getClient() != null) {
				representative.getClient().setRepresentatives(null);
				representative.getClient().setResponsible(null);
				if (representative.getClient().getAddress() != null) {
					representative.getClient().getAddress().setClient(null);
				}
			}
		}
		return representative;
	}
	
	List<Representative> jsonListNullReference(List<Representative> list){
		for (Representative representative : list) {
			representative = jsonNullReference(representative);
		}
		return list;
	}
	
	public void saveToDB(List<Representative> representatives) {
		rr.save(representatives);
	}
	
	
	public void importDataFromCSV(String filename) {
		List<Representative> representatives = CSVUtils.buildListFromCSV(filename, Representative.class);
		saveToDB(representatives);
	}
	
	
	public void exportDataToCSV(String filename) {
		List<Representative> representatives = buildListFromDB();
		CSVUtils.exportListToCSV(filename, representatives);
	}
	
	public List<Representative> buildListFromDB() {
		return rr.findAll();
	}


	@Override
	public Collection<Representative> findAll() {
		return rr.findAll();
	}
	
	
}