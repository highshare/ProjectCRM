package pl.mwa.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.mwa.document.DocumentResource;
import pl.mwa.exception.ModelNotFound;


@RequestMapping("/users")
@RestController
public class UserResource {

	
	private final UserService service;
	
	
	private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);

	
	
	public UserResource(UserService service) {
		this.service = service;
	}
	
	
	
	@GetMapping("/{id}")
	ResponseEntity getUser(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.getUser(id));
	}
	

	@GetMapping
	ResponseEntity getUsers() {
		return ResponseEntity.ok(service.getUsers());
	}
	
	
	@PostMapping
	ResponseEntity createUser(@Valid @RequestBody CreateUserDto createUserDto ) {
		long id = service.createUser(createUserDto);
		log.debug("User created with id: " + id);
		return ResponseEntity.ok(id);
	}
	
	
	@PutMapping
	ResponseEntity updateUser(@Valid @RequestBody UserDto userDto) {
		service.update(userDto);
		return ResponseEntity.accepted().build();
	}
	
	
	@DeleteMapping("/{id}")
	ResponseEntity deleteUser(@PathVariable("id") Long id) {
		service.deactivateUser(id);
		return ResponseEntity.accepted().build();
		
	}
	
	
    @ExceptionHandler(ModelNotFound.class)
    ResponseEntity handleException(ModelNotFound e){
    	log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }
	
	
}
