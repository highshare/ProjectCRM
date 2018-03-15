package pl.mwa.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mwa.representative.Representative;

public interface ClientRepository extends JpaRepository<Client, Long>{

	public Client findByRepresentatives(Representative representative);
	
	public Client findByName(String name);
	
	List<Client> findByIndustry(Industry industry);
	
	List<Client> findByResponsibleId(Long id);
	
	List<Client> findByResponsibleLastname(String lastname);

	List<Client> findByAddressCityIgnoreCase(String city);
	
	List<Client> findByActiveTrue();
	
	List<Client> findByActiveFalse();
	
}
