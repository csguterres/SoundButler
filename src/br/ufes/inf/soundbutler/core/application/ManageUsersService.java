package br.ufes.inf.soundbutler.core.application;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.soundbutler.core.domain.User;

@Local
public interface ManageUsersService extends CrudService<User> {
	

}
