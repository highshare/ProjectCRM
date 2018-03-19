package pl.mwa.representative;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.mwa.client.Client;

public interface RepresentativeRepository extends JpaRepository<Representative, Long>{

	public Representative findByClient(Client client);
	
	public List<Representative> findAllByActiveTrue();

	public Page<Representative> findAllByActiveTrue(Pageable pageable);
	
}
