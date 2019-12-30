
package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.NonCommercialBanner;
import acme.entities.creditCards.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends UserRole {

	// Serialization identifier

	private static final long						serialVersionUID	= 1L;

	//Attributes

	@NotBlank
	private String									organizationName;

	//Relationships

	@OneToMany
	private Collection<@Valid CreditCard>			creditCards;

	@OneToMany
	private Collection<@Valid CommercialBanner>		commercialBanners;

	@OneToMany
	private Collection<@Valid NonCommercialBanner>	nonCommercialBanners;

}
