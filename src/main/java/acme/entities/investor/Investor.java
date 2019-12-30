
package acme.entities.investor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Investor extends DomainEntity {

	//Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				statement;

	@Range(min = 0, max = 5)
	private Integer				star;
}
