
package acme.entities.auditorRequests;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.components.Status;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class AuditorRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	private Status				status;

	@NotBlank
	private String				firm;

	@NotBlank
	private String				statement;

	@NotNull
	@Valid
	@OneToOne
	private UserAccount			userAccount;

}
