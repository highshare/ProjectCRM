package pl.mwa.client;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.mwa.util.CSVUtils;


@RequestMapping("/clients")
@RestController
public class ClientResource {
	
	@Autowired
	ClientServiceImpl cs;
	
	@Autowired
	ClientRepository cr;
	

	public ClientResource(ClientServiceImpl cs) {
		this.cs = cs;
	}
	
	
	 @GetMapping("/{id}")
	 ResponseEntity getClient(@PathVariable Long id){
	        Client client = cs.findOne(id);
	        return ResponseEntity.ok(client);
	}
	
	
    @GetMapping
    ResponseEntity getClients(){
        return ResponseEntity.ok(cs.findAll());
    }

    @GetMapping("/industry/{industry}")
    ResponseEntity getClients(@PathVariable Industry industry) {
    	return ResponseEntity.ok(cs.findByIndustry(industry));
    }
    
    @GetMapping("/responsible/{id}")
    ResponseEntity getClients(@PathVariable Long id) {
    	return ResponseEntity.ok(cs.findByResponsibleId(id));
    }
    
    @GetMapping("/responsible")
    ResponseEntity getClients(@RequestParam String lastname) {
    	return ResponseEntity.ok(cs.findByResponsibleLastname(lastname));
    }
    
    
    @GetMapping("/address-search")
    ResponseEntity getClientsByAddress(@RequestParam("city") String city) {
    	return ResponseEntity.ok(cs.findByAddressCityIgnoreCase(city));
    }
    
    @PostMapping
    ResponseEntity createClient(@Valid @RequestBody Client client){
        cs.save(client);
        return ResponseEntity.ok(client.getId());
    }
    
    
    @DeleteMapping("/{id}")
    ResponseEntity deleteClient(@PathVariable Long id){
    	cs.remove(id);
        return ResponseEntity.accepted().build();
    }
    
    
    @PutMapping
    ResponseEntity updateClient(@Valid @RequestBody Client client){
    	cs.update(client);
        return ResponseEntity.accepted().build();
    }
    
	@GetMapping("/import")
	ResponseEntity getImportListFromCSV(@RequestParam(name = "filename", required = true) String filename) {
		return ResponseEntity.ok(CSVUtils.buildListFromCSV(filename, Client.class));
	}
	
	@PostMapping("/import")
	ResponseEntity addEntitiesFromCSV(@RequestParam(name = "filename", required = true) String filename) {
		new ClientServiceImpl(cr).importDataFromCSV(filename);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/export")
	ResponseEntity exportDBtoFile(@RequestParam(name = "filename", required = true) String filename) {
		new ClientServiceImpl(cr).exportDataToCSV(filename);
		return ResponseEntity.accepted().build();
	}
    
    
}
