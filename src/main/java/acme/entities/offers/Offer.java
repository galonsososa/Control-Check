
package acme.entities.offers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Offer extends DomainEntity {

	//Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				minMoney;

	@NotNull
	@Valid
	private Money				maxMoney;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^O[A-Z]{4}-\\d{5}$", message = "{master.pattern.offer}")
	private String				ticker;

	@NotBlank
	@URL
	private String				moreInfo;

}
