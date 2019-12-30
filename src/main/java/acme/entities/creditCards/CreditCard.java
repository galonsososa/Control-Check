
package acme.entities.creditCards;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CreditCard extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//attributes

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;

	@Pattern(regexp = "^[0-9]\\d\\d$", message = "{master.pattern.cvv}")
	@NotBlank
	private String				cvv;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				expirationDate;

	//	@ManyToOne(optional = false)
	//	private Sponsor				user;

	//relationships

}
