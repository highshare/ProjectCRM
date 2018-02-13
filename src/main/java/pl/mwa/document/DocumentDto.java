package pl.mwa.document;

import java.sql.Timestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mwa.user.User;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {

	@NotNull
	private long id;
	
	@NotBlank
	private String title;
	
	@NotNull
	private Timestamp created;
	
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Enumerated(EnumType.STRING)
	private DocumentStatus documentStatus;
	
	private String description;
	
	private double value;
	
	private User author;
	
	private User acceptedBy;
	
	private Timestamp accepted;
	
	
	
}
