package br.ufes.inf.soundbutler.core.application;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;
import br.ufes.inf.soundbutler.core.persistence.SongDAO;

@PermitAll
@Stateless
public class ManageSongsServiceBean  extends CrudServiceBean<Song> implements ManageSongsService {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ManageSongsServiceBean.class.getCanonicalName());
	
	@EJB
	private SongDAO songDAO;
	
	@Resource
	private SessionContext sessionContext;
	
	@Override
	
	public BaseDAO<Song> getDAO() {
		// TODO Auto-generated method stub
		return songDAO;
	}
	
	public void deletar(Song song) {
		try {
			songDAO.deletar(song);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
