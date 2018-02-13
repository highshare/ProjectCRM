package pl.mwa.document;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.mwa.representative.RepresentativeServiceImpl;


@RequestMapping("/documents")
@RestController
public class DocumentResource {

	
    private final DocumentService service;

    public DocumentResource(DocumentService service) {
        this.service = service;
    }
	
	
    @GetMapping("/{id}")
    ResponseEntity getDocument(@PathVariable long id){
        DocumentDto documentDto = service.getDocument(id);
        return ResponseEntity.ok(documentDto);
    }
	

    @GetMapping("/type/{documentType}")
    ResponseEntity getDocuments(@PathVariable DocumentType documentType) {
    	return ResponseEntity.ok(service.getDocumentsType(documentType));
    }
    
    
    @GetMapping("/status/{documentStatus}")
    ResponseEntity getDocuments(@PathVariable DocumentStatus documentStatus) {
    	return ResponseEntity.ok(service.getDocumentsStatus(documentStatus));
    }
    
    
    @GetMapping("/search")
    ResponseEntity getSearchRequest(@RequestParam(name="documentType", required = false) DocumentType documentType,
    		@RequestParam(name="documentStatus", required = false) DocumentStatus documentStatus ) {
    	
    	return ResponseEntity.ok(service.getDocumentsSearch(documentType, documentStatus));
    }
    
    
    
    @GetMapping
    ResponseEntity getDocuments(){
        return ResponseEntity.ok(service.getDocuments());
    }
    
    
    @PostMapping
    ResponseEntity createDocument(@RequestBody @Valid CreateDocumentDto createDocumentDto){
        long documentId = service.createDocument(createDocumentDto);
        return ResponseEntity.ok(documentId);
    }
    
    
    @DeleteMapping("/{id}")
    ResponseEntity deleteDocument(@PathVariable long id){
        service.deleteDocument(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity updateDocument(@Valid @RequestBody DocumentDto documentDto){
        service.update(documentDto);
        return ResponseEntity.accepted().build();
    }

    
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity handleException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
    

    
    
}
