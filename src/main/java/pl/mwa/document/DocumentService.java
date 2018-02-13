package pl.mwa.document;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mwa.exeption.ModelNotFound;

@Service
public class DocumentService {

	
	@Autowired
	DocumentRepository repository;
	
	
	
	public DocumentService(DocumentRepository repository) {
		this.repository = repository;
	}
	
	
    Long createDocument(CreateDocumentDto createDocumentDto){
    	createDocumentDto.setCreated(Timestamp.from(Instant.now()));
        Document document = repository.save(DocumentMapper.toEntity(createDocumentDto));
        return document.getId();
    }
	
    
    DocumentDto getDocument(long id){
        return repository.findById(id)
                .map(DocumentMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Document", id));
    }
    
    
    List<DocumentDto> getDocuments(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }
    
    
    List<DocumentDto> getDocumentsType(DocumentType documentType){
        return StreamSupport.stream(repository.findAllByDocumentType(documentType).spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }
    
    
    List<DocumentDto> getDocumentsStatus(DocumentStatus documentStatus) {
        return StreamSupport.stream(repository.findAllByDocumentStatus(documentStatus).spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    void deleteDocument(Long id){
        Document document = repository.findById(id)
                .orElseThrow(() -> new ModelNotFound("Document", id));
        repository.delete(document);
    }

    void update(DocumentDto documentDto){
        Document document = repository.findById(documentDto.getId())
                .orElseThrow(() -> new ModelNotFound("Document", documentDto.getId()));
        document.setTitle(documentDto.getTitle());
        document.setDocumentType(documentDto.getDocumentType());
        document.setDocumentStatus(documentDto.getDocumentStatus());
        document.setDescription(documentDto.getDescription());
        document.setValue(documentDto.getValue());
        document.setAuthor(documentDto.getAuthor());
        document.setAcceptedBy(documentDto.getAcceptedBy());
        repository.save(document);
    }


	public List<DocumentDto> getDocumentsSearch(DocumentType documentType, DocumentStatus documentStatus) {
		List<Document> list;
		if(documentType != null && documentStatus != null) {
				list = repository.findAllByDocumentTypeAndDocumentStatus(documentType, documentStatus);
		} else if(documentType != null){
				list = repository.findAllByDocumentType(documentType);
			} else {
				list = repository.findAllByDocumentStatus(documentStatus);
		} 
		return StreamSupport.stream(list.spliterator(), false)
				.map(DocumentMapper::toDto)
				.collect(Collectors.toList());
	}



    
	
}
