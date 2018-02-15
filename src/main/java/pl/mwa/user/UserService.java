package pl.mwa.user;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.mwa.exception.ModelNotFound;
import pl.mwa.role.Role;
import pl.mwa.role.RoleRepository;
import pl.mwa.util.EntityUtils;

@Service
public class UserService {

	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	
	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserService(UserRepository repository, RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
		this.roleRepository = roleRepository;
	}
	
	
	
	public Long createUser(CreateUserDto createUserDto) {
		List<Role> roles = roleRepository.findAll();
		createUserDto.setActive(true);
		createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
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
        EntityUtils.setter(userDto.getPassword(), t -> user.setPassword(passwordEncoder.encode(t)));
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



	public User findByUserName(String username) {
		return repository.findByUsername(username);
	}
	
	User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return repository.findByUsername(auth.getName());
	}
    
	
	
	public void setLoggedUser(User user) {
		User tmpUser = getLoggedUser();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));}
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), passwordEncoder,
				grantedAuthorities));
		tmpUser.setUsername(user.getUsername());
		tmpUser.setPassword(passwordEncoder.encode(user.getPassword()));
		tmpUser.setRoles(user.getRoles());
		repository.save(tmpUser);
	}
	
	
	
	
}
