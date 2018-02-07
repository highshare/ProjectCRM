package pl.mwa.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank
	@Column
	private String password;
	
	@NotBlank
	@Column(nullable = false)
	private String firstname;
	
	@NotBlank
	@Column(nullable = false)
	private String lastname;
	
	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@Min(1000000000)
	@Max(999999999)
	private int phone;
	
	@NotBlank
	private boolean active;
	
	private LocalDateTime created;
	
	
	
}
