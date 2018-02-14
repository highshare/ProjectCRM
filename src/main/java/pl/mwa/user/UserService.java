package pl.mwa.user;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.mwa.exception.ModelNotFound;
import pl.mwa.util.EntityUtils;

@Service
public class UserService {

	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	
	UserRepository repository;
	
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	
	Long CreateUser(CreateUserDto createUserDto) {
		createUserDto.setActive(true);
		createUserDto.setCreated(Timestamp.from(Instant.now()));
		User user = repository.save(UserMapper.toEntity(createUserDto));
		return user.getId(); 
	}

	
    UserDto getUser(Long id){
        return repository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("User", id));
    }
    
    List<UserDto> getUsers(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
    
  
    void deleteUser(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new ModelNotFound("User", id));
        repository.delete(user);
    }

    
    void update(UserDto userDto){
        User user = repository.findById(userDto.getId())
                .orElseThrow(() -> new ModelNotFound("User", userDto.getId()));
        EntityUtils.setter(userDto.getUsername(), t -> user.setUsername(t));
        EntityUtils.setter(userDto.getPassword(), t -> user.setPassword(t));
        EntityUtils.setter(userDto.getFirstname(), t -> user.setFirstname(t));
        EntityUtils.setter(userDto.getLastname(), t -> user.setLastname(t));
        EntityUtils.setter(userDto.getEmail(), t -> user.setEmail(t));
        EntityUtils.setter(userDto.getPhone(), t -> user.setPhone(t));
        EntityUtils.setter(userDto.getActive(), t -> user.setActive(t));
        EntityUtils.setter(userDto.getRoles(), t -> user.setRoles(t));
        EntityUtils.setter(userDto.getPosition(), t -> user.setPosition(t));
        repository.save(user);
    }

	
    void deactivateUser(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new ModelNotFound("User", id));
        user.setActive(false);
        repository.save(user);
    }
	
    
    
	
	
}
