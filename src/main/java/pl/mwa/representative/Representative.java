package pl.mwa.representative;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mwa.client.Client;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="representatives")
public class Representative {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String firstname;
	
	@NotBlank
	private String lastname;
	
	private String position;

	private String phone;
	
	@Email
	private String email;
	
	private boolean active;
	
	
	@ManyToOne
	private Client client;
	
	
	public String getFullname() {
		return firstname + " " + lastname;
	}
	
	
}
