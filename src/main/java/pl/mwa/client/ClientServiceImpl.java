package pl.mwa.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mwa.representative.Representative;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository cr;
	
	
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
		cr.save(client);

	}


	public Client findOne(Long id) {
		return cr.findOne(id);
	}


	public List<Client> findAll() {
		return cr.findAll();
	}

	
}
