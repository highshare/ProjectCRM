package pl.mwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	
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
	
	
}
