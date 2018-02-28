package pl.mwa.address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.mwa.document.DocumentService;

@Service
public class AddressService {

	private static final Logger log = LoggerFactory.getLogger(AddressService.class);
	
	AddressRepository repository;
	

	Long createAddress(CreateAddressDto dto) {
		Address address = repository.save(AddressMapper.toEntity(dto));
		return address.getId();
	}
	
	
	
}
