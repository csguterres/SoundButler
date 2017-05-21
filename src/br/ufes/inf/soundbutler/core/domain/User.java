package br.ufes.inf.soundbutler.core.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User extends PersistentObjectSupport {
	
	
	@Column(length = 128)
	private String name;

	/** Electronic address, which also serves as username for identification. */
	@Column(length = 100)
	private String email;

	/** The password, which identifies the user. */
	@Column(length = 100)
	private String password;
	
	@ManyToMany
	private Set<Song> songs;

	/** The timestamp of the moment this user was created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	/** Getter for userName. */
	public String getName() {
		return name;
	}

	/** Setter for userName. */
	public void setName(String userName) {
		this.name = userName;
	}

	/** Getter for email. */
	public String getEmail() {
		return email;
	}

	/** Setter for email. */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Getter for password. */
	public String getPassword() {
		return password;
	}

	/** Setter for password. */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Getter for creationDate. */
	public Date getCreationDate() {
		return creationDate;
	}

	/** Setter for creationDate. */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

}
