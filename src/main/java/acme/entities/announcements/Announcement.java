
package acme.entities.announcements;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(indexes = {
	@Index(columnList = "moment")
})
public class Announcement extends DomainEntity {

	//Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@URL
	private String				moreInfo;

	@NotBlank
	private String				text;

}
