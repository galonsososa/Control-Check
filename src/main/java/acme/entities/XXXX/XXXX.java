
package acme.entities.XXXX;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXXX extends DomainEntity {

	//Serialisation identifier -------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes-------------------------------------------

	@NotBlank
	@Length(max = 144) //144 = x ; Maybe this is min(x), if doubt explain this in readme
	private String				description;

	@URL
	private String				moreInfo;

	//Relationships

	@NotNull
	@Valid
	@OneToOne
	private Job					job;

}
