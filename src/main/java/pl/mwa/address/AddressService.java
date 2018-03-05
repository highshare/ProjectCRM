package pl.mwa.address;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.mwa.client.Client;
import pl.mwa.exception.ModelNotFound;
import pl.mwa.util.EntityUtils;

@Service
public class AddressService {

	private static final Logger log = LoggerFactory.getLogger(AddressService.class);
	
	AddressRepository repository;
	

	Long createAddress(CreateAddressDto dto) {
		Address address = repository.save(AddressMapper.toEntity(dto));
		return address.getId();
	}
	
	AddressDto getAddress(long id) {
		return repository.findById(id)
				.map(AddressMapper::toDto)
				.orElseThrow(() -> new ModelNotFound("Address", id));
	}
	
	List<AddressDto> getAddresses() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(AddressMapper::toDto)
				.collect(Collectors.toList());
	}
	
	
	void deleteAddress(long id) {
		Address address = repository.findById(id)
				.orElseThrow(() -> new ModelNotFound("Address", id));
		repository.delete(address);
	}
	
	
    void update(AddressDto addressDto){
        Address address = repository.findById(addressDto.getId())
                .orElseThrow(() -> new ModelNotFound("Address", addressDto.getId()));
        EntityUtils.setter(addressDto.getStreet(), t -> address.setStreet(t));
        EntityUtils.setter(addressDto.getStreetNumber(), t -> address.setStreetNumber(t));
        EntityUtils.setter(addressDto.getAdditionalNumber(), t -> address.setAdditionalNumber(t));
        EntityUtils.setter(addressDto.getPostalCode(), t -> address.setPostalCode(t));
        EntityUtils.setter(addressDto.getClient(), t -> address.setClient(t));
        EntityUtils.setter(addressDto.getCity(), t -> address.setCity(t));
        repository.save(address);
    }

	
	List<AddressDto> getAddressesSearch(String street, String streetNumber,
			String additionalNumber, String postalCode, String city, Client client) {
		List<AddressDto> dtos = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(AddressMapper::toDto)
				.collect(Collectors.toList());
		return null;
	}
	
	private static List<AddressDto> filter(List<AddressDto> dtos, String street, String streetNumber,
			String additionalNumber, String postalCode, String city, Client client){
		return dtos.stream()
				.filter(addFilter(street, addressDto -> street.equalsIgnoreCase(addressDto.getStreet())))
				.filter(addFilter(streetNumber, addressDto -> streetNumber.equalsIgnoreCase(addressDto.getStreetNumber())))
				.filter(addFilter(additionalNumber, addressDto -> additionalNumber.equalsIgnoreCase(addressDto.getAdditionalNumber())))
				.filter(addFilter(postalCode, addressDto -> postalCode.equalsIgnoreCase(addressDto.getPostalCode())))
				.filter(addFilter(city, addressDto -> city.equalsIgnoreCase(addressDto.getCity())))
				.filter(addFilter(client, addressDto -> client.equals(addressDto.getClient())))
				.collect(Collectors.toList());
	}
	
    private static Predicate<AddressDto> addFilter(Object value, Predicate<AddressDto> predicate) {
        if(value != null){
            return predicate;
        }
        return AddressDto -> true;
    }
	
	
}
