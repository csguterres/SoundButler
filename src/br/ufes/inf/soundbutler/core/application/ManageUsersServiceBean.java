package br.ufes.inf.soundbutler.core.application;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.soundbutler.core.domain.User;
import br.ufes.inf.soundbutler.core.persistence.UserDAO;


@PermitAll
@Stateless
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {
		
	@Resource
	private SessionContext sessionContext;
	
	private UserDAO userDAO;
	
	@EJB
	private CoreInformation coreInformation;
	
	@Override
	public BaseDAO getDAO() {
		// TODO Auto-generated method stub
		return userDAO;
	}
	
	@Override
	protected User validate(User newEntity, User oldEntity) {
		// New users must have their creation date set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) newEntity.setCreationDate(now);

		// All users have their last update date set when persisted.

		return newEntity;
	}
	
	/** @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#create(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject) */
	@Override
	public void create(User entity) {
		// Performs the method as inherited (create the user).
		super.create(entity);
		
			// Retrieves the current user, i.e., the admin.
		User admin;
		try {
			
			admin = userDAO.retrieveByEmail(sessionContext.getCallerPrincipal().getName());
			// Creates the data model with the information needed to send an e-mail to the new user.
			Map<String, Object> dataModel = new HashMap<>();
			System.out.println("ENTROU AQUI!!!!!!") ;
			dataModel.put("config", coreInformation.getCurrentConfig());
			dataModel.put("admin", admin);
			dataModel.put("user", entity);
			
		} catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
