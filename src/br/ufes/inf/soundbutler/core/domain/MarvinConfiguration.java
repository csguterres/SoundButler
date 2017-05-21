package br.ufes.inf.soundbutler.core.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
public class MarvinConfiguration extends PersistentObjectSupport {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The timestamp of the moment this configuration came in effect. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	/** Constructor. */
	public MarvinConfiguration() {}

	/** Getter for creationDate. */
	public Date getCreationDate() {
		return creationDate;
	}



	/** Setter for creationDate. */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
