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
	
/*	@Autowired
	ClientServiceImpl cs;*/
	
/*	@Autowired
	ClientRepository cr;*/
	
	
	@Autowired
	ClientService service;

/*	public ClientResource(ClientServiceImpl cs) {
		this.cs = cs;
	}*/
	
	
	 @GetMapping("/{id}")
	 ResponseEntity getClient(@PathVariable Long id){
	        ClientDto clientDto = service.findOne(id);
	        return ResponseEntity.ok(clientDto);
	}
	
	
    @GetMapping
    ResponseEntity getClients(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/industry/{industry}")
    ResponseEntity getClients(@PathVariable Industry industry) {
    	return ResponseEntity.ok(service.findByIndustry(industry));
    }
    
    @GetMapping("/responsible/{id}")
    ResponseEntity getClients(@PathVariable Long id) {
    	return ResponseEntity.ok(service.findByResponsibleId(id));
    }
    
    @GetMapping("/responsible")
    ResponseEntity getClients(@RequestParam String lastname) {
    	return ResponseEntity.ok(service.findByResponsibleLastname(lastname));
    }
    
    
    @GetMapping("/address-search")
    ResponseEntity getClientsByAddress(@RequestParam("city") String city) {
    	return ResponseEntity.ok(service.findByAddressCityIgnoreCase(city));
    }
    
    @PostMapping
    ResponseEntity createClient(@Valid @RequestBody CreateClientDto dto){
        return ResponseEntity.ok(service.createClient(dto));
    }
    
    
    @DeleteMapping("/{id}")
    ResponseEntity deleteClient(@PathVariable Long id){
    	service.remove(id);
        return ResponseEntity.accepted().build();
    }
    
    
    @PutMapping
    ResponseEntity updateClient(@Valid @RequestBody ClientDto dto){
    	service.update(dto);
        return ResponseEntity.accepted().build();
    }
    
	@GetMapping("/import")
	ResponseEntity getImportListFromCSV(@RequestParam(name = "filename", required = true) String filename) {
		return ResponseEntity.ok(CSVUtils.buildListFromCSV(filename, Client.class));
	}
	
	@PostMapping("/import")
	ResponseEntity addEntitiesFromCSV(@RequestParam(name = "filename", required = true) String filename) {
		service.importDataFromCSV(filename);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/export")
	ResponseEntity exportDBtoFile(@RequestParam(name = "filename", required = true) String filename) {
		service.exportDataToCSV(filename);
		return ResponseEntity.accepted().build();
	}
    
    
}
