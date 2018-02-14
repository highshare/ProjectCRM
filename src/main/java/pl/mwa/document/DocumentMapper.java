package pl.mwa.document;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

final class DocumentMapper {

	private DocumentMapper() {}
	
	
	
	static DocumentDto toDto(Document document) {
		DocumentDto documentDto = new DocumentDto();
		try {
			BeanUtils.copyProperties(documentDto, document);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return documentDto;
	}
	
	static Document toEntity(CreateDocumentDto dto) {
		return new Document().builder()
				.title(dto.getTitle())
				.description(dto.getDescription())
				.created(dto.getCreated())
				.documentType(dto.getDocumentType())
				.documentStatus(dto.getDocumentStatus())
				.build();
	}
	
}
