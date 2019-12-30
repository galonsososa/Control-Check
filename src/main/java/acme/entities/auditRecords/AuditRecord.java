
package acme.entities.auditRecords;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(indexes = {
	@Index(columnList = "moment")
})
public class AuditRecord extends DomainEntity {

	//Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	@NotBlank
	private String				title;
	

	private boolean				draftMode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	private String				body;
	
	//Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Auditor				auditor;




}
