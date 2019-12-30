
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class CompanyRecord extends DomainEntity {

	//Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	//Attributes

	@NotBlank
	private String				companyName;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceoName;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				webSite;

	@Pattern(regexp = "^(\\+[0-9]{1,3}\\s)?(\\([0-9]{1,4}\\)\\s)?[0-9]{6,10}$", message = "{master.pattern.companyrecord}")
	@NotBlank
	private String				phone;

	@Email
	@NotBlank
	private String				email;

	private Boolean				incorporated;

	@NotNull
	@Range(min = 1, max = 5)
	private Integer				stars;

}
