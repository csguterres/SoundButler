package br.ufes.inf.soundbutler.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;
import br.ufes.inf.soundbutler.core.persistence.SongDAO;

@PermitAll
@Stateless
public class ManageSongsServiceBean  extends CrudServiceBean<Song> implements ManageSongsService {
	
	private SongDAO songDAO;
	@Override
	
	public BaseDAO<Song> getDAO() {
		// TODO Auto-generated method stub
		return songDAO;
	}

}
