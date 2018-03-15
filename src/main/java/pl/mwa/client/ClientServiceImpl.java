package pl.mwa.client;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mwa.address.AddressRepository;
import pl.mwa.representative.Representative;
import pl.mwa.util.CSVUtils;

@Service
public class ClientServiceImpl implements ClientService {

	
	ClientRepository cr;
	
	@Autowired
	AddressRepository ar;
	
	
	public ClientServiceImpl(ClientRepository cr) {
		super();
		this.cr = cr;
	}
	
	
	@Override
	public Client findByRepresentatives(Representative representative) {
		return cr.findByRepresentatives(representative);
	}

	@Override
	public Client findByName(String name) {
		return cr.findByName(name);
	}

	@Override
	public void remove(long id) {
		Client client = cr.findOne(id);
		client.setActive(false);
		cr.save(client);
	}


	@Override
	public void save(Client client) {
		client.setActive(true);
		if (client.getAddress() != null) {
			ar.save(client.getAddress());
		}
		cr.save(client);
	}

	public void update(Client client) {
		if (client != null) {
			Client oldclient = cr.findOne(client.getId());
			if (client.getName() == null) client.setName(oldclient.getName());
			if (client.getAbbr() == null) client.setAbbr(oldclient.getAbbr());
			if (client.getAddress() == null) client.setAddress(oldclient.getAddress());
			if (client.getActive() == null) client.setActive(oldclient.getActive());
			if (client.getIndustry() == null) client.setIndustry(oldclient.getIndustry());
			if (client.getRepresentatives() == null) client.setRepresentatives(oldclient.getRepresentatives());
			if (client.getResponsible() == null) client.setResponsible(oldclient.getResponsible());
			cr.save(client);
		}
	}

	public Client findOne(Long id) {
		return jsonNullReference(cr.findOne(id));
	}

	Client jsonNullReference(Client client) {
		if (client != null) {
			for (Representative r : client.getRepresentatives()) {
				r.setClient(null);
			}
			if (client.getAddress() != null)
				client.getAddress().setClient(null);
		}
		return client;
	}

	List<Client> jsonListNullReference(List<Client> list) {
		for (Client client : list) {
			client = jsonNullReference(client);
		}
		return list;
	}
	
	
	public List<Client> findAll() {
		return jsonListNullReference(cr.findAll());
	}

	public void saveToDB(List<Client> clients) {
		cr.save(clients);
	}
	
	
	public void importDataFromCSV(String filename) {
		List<Client> clients = CSVUtils.buildListFromCSV(filename, Client.class);
		saveToDB(clients);
	}
	
	
	public void exportDataToCSV(String filename) {
		List<Client> clients = buildListFromDB();
		CSVUtils.exportListToCSV(filename, clients);
	}
	
	public List<Client> buildListFromDB() {
		return cr.findAll();
	}


	@Override
	public List<Client> findByIndustry(Industry industry) {
		return jsonListNullReference(cr.findByIndustry(industry));
	}


	@Override
	public List<Client> findByResponsibleId(Long id) {
		return jsonListNullReference(cr.findByResponsibleId(id));
	}


	@Override
	public List<Client> findByResponsibleLastname(String lastname) {
		return jsonListNullReference(cr.findByResponsibleLastname(lastname));
	}


	@Override
	public List<Client> findByActiveTrue() {
		return jsonListNullReference(cr.findByActiveTrue());
	}


	@Override
	public List<Client> findByActiveFalse() {
		return jsonListNullReference(cr.findByActiveFalse());
	}


	@Override
	public List<Client> findByAddressCityIgnoreCase(String city) {
		return jsonListNullReference(cr.findByAddressCityIgnoreCase(city));
	}

	
}
