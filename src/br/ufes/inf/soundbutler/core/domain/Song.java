package br.ufes.inf.soundbutler.core.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Song extends PersistentObjectSupport{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@NotNull
	@Size(max = 100)
	private String name;
	
	@ManyToMany(mappedBy = "songs") 
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
    
}
