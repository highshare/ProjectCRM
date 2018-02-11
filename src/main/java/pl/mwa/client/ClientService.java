package pl.mwa.client;

import pl.mwa.representative.Representative;

public interface ClientService {

	public Client findByRepresentatives(Representative representative);
	
	public Client findByName(String name);
	
	public void remove(long id);
	
	public void save(Client client);

	
}
