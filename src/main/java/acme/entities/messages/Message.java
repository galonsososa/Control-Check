
package acme.entities.messages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	private String				tags;

	@NotBlank
	@Column(length = 1024)
	private String				body;

	//Relationships-----------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Thread				thread;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		authenticated;

}
