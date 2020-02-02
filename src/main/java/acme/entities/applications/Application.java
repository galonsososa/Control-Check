
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.components.Status;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Application extends DomainEntity {

	//Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 15)
	@Pattern(regexp = "^([A-Z]||\\d){4}-([A-Z]||\\d){4}:([A-Z]||\\d){4}$", message = "{master.pattern.application}")
	private String				reference;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;
	@NotNull
	private Status				status;

	@NotBlank
	private String				statement;

	private String				justification;

	private String				answer;

	@URL
	private String				bow;

	private String				password;

	////Relationships

	@NotNull
	@Valid
	@OneToOne
	private Job					job;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

}
