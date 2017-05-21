package br.ufes.inf.soundbutler.core.persistence;

import java.util.List ;

import javax.ejb.*;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;

@Local
public interface UserDAO extends BaseDAO<User> {
	
	List<Song> retrieveSongs(String username) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException ;

	User retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
