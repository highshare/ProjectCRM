package pl.mwa.client;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mwa.representative.Representative;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	private boolean active;
	
	@OneToMany
	private Collection<Representative> representatives;
	
	
}
