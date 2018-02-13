package pl.mwa.document;

final class DocumentMapper {

	private DocumentMapper() {}
	
	
	
	static DocumentDto toDto(Document document) {
		DocumentDto documentDto = new DocumentDto();
		documentDto.setId(document.getId());
		documentDto.setTitle(document.getTitle());
		documentDto.setCreated(document.getCreated());
		documentDto.setDocumentType(document.getDocumentType());
		documentDto.setDocumentStatus(document.getDocumentStatus());
		documentDto.setDescription(document.getDescription());
		documentDto.setValue(document.getValue());
		documentDto.setAuthor(document.getAuthor());
		documentDto.setAcceptedBy(document.getAcceptedBy());
		documentDto.setAccepted(document.getAccepted());
		return documentDto;
	}
	
	static Document toEntity(CreateDocumentDto dto) {
		return new Document().builder()
				.title(dto.getTitle())
				.created(dto.getCreated())
				.documentType(dto.getDocumentType())
				.documentStatus(dto.getDocumentStatus())
				.build();
	}
	
}
