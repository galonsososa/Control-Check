
package acme.entities.YYYY;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.applications.Application;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class YYYY extends DomainEntity {

	//Serialisation identifier -------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes-------------------------------------------

	@NotBlank
	private String				answer;

	@Length(min = 6) //6 is changed for x; min x letters, min x digits, min x symbols
	private String				password;

	//Relationships

	@NotNull
	@Valid
	@OneToOne
	private Application			application;

}
