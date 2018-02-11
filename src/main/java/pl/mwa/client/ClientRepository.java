package pl.mwa.client;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mwa.representative.Representative;

public interface ClientRepository extends JpaRepository<Client, Long>{

	public Client findByRepresentatives(Representative representative);
	
	public Client findByName(String name);
	
	
}
