package br.ufes.inf.soundbutler.core.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.soundbutler.core.application.ManageUsersService;
import br.ufes.inf.soundbutler.core.domain.User;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageUsersController extends CrudController<User> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageUsersController.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private ManageUsersService manageUsersService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageUsers.filter.byName", "name", getI18nMessage("msgsCore", "manageUsers.text.filter.byName")));
	}

	/**
	 * TODO: document this method.
	 * 
	 * @param query
	 * @return
	 */
	
}