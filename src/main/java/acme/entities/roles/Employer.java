package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employer extends UserRole{

	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String 		company;
	
	@NotBlank
	private String		sector;
	
		

}
