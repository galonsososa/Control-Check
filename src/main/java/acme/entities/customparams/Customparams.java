
package acme.entities.customparams;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customparams extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				spamWords;

	@NotNull
	@Min(0)
	@Max(100)
	private Double				spamThreshold;

}
