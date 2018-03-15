package pl.mwa.client;

import java.util.List;

import pl.mwa.representative.Representative;

public interface ClientService {

	public Client findByRepresentatives(Representative representative);
	
	public Client findByName(String name);
	
	public void remove(long id);
	
	public void save(Client client);
	
	public Client findOne(Long id);
	
	List<Client> findByIndustry(Industry industry);

	List<Client> findByResponsibleId(Long id);
	
	List<Client> findByResponsibleLastname(String lastname);
	
	List<Client> findByAddressCityIgnoreCase(String city);
	
	List<Client> findByActiveTrue();
	
	List<Client> findByActiveFalse();

	
}
