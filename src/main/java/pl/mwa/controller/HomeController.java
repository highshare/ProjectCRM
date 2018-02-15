package pl.mwa.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mwa.role.Role;
import pl.mwa.role.RoleService;
import pl.mwa.user.CreateUserDto;
import pl.mwa.user.UserService;

@Controller
public class HomeController {

	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	private final UserService userService;
	
	
	private final RoleService roleService;

	public HomeController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@ResponseBody
	@GetMapping("/")
	public String home() {
		return "welcome";
	}
	
	@ResponseBody
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	
	@GetMapping("/add-user")
	@ResponseBody
	public String addUser() {
		CreateUserDto u = new CreateUserDto();
		u.setFirstname("default");
		u.setLastname("admin");
		u.setUsername("admin");
		u.setPassword("admin");
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.findByName("ROLE_ADMIN"));
		u.setRoles(roles);
		u.setEmail("admin@admin.pl");
		userService.createUser(u);
		return "add-user";
	}
	
	
	
}
