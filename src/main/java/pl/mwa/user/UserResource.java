package pl.mwa.user;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class UserResource {

	
	private final UserService service;
	
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
		long id = service.CreateUser(createUserDto);
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
	
	
	
	
	
}
