package pl.mwa.representative;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.mwa.client.Client;

public interface RepresentativeService {

	public Representative findByClient(Client client);
	
	public void remove(long id);
	
	public void save(Representative representative);
	
	public Collection<Representative> findAllByActiveTrue();

	public Page<Representative> findAll(Pageable pageable);

	public Representative findOne(long id);
	
	
}
