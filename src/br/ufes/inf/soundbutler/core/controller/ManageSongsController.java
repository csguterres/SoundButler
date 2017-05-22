package br.ufes.inf.soundbutler.core.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.soundbutler.core.application.ManageSongsService;
import br.ufes.inf.soundbutler.core.application.ManageUsersService;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;


@Named
@SessionScoped
public class ManageSongsController extends CrudController<Song> {

	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageUsersController.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private ManageSongsService manageSongsService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Song> getCrudService() {
		return manageSongsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageSongs.filter.byName", "name", getI18nMessage("msgsCore", "manageSongs.text.filter.byName")));
	}


}
