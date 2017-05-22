package br.ufes.inf.soundbutler.core.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.soundbutler.core.application.ManageUsersService;
import br.ufes.inf.soundbutler.core.domain.User;


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

//	private User user = new User();
	
//	public ManageUsersController(){
//		user = new User() ;
//	}
//	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageUsers.filter.byName", "name", getI18nMessage("msgsCore", "manageUsers.text.filter.byName")));
	}
//
//	public String salvar() {
//		manageUsersService.salvar(user) ;
//		user = new User() ;
//		return "/core/manageUsers/list?faces-redirect=true" ;
//	}
	
//	public String deletar() {
//
//		manageUsersService.deletar(user) ;
//		user = new User() ;
//		System.out.println(user) ;
//		return "/core/manageUsers/list?faces-redirect=true" ;
//	}
	
//	public String criarNovo(){
//		user = new User() ;
//		return "/core/manageUsers/form?faces-redirect=true" ;
//	}
	
}