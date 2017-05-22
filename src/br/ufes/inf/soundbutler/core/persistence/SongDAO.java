package br.ufes.inf.soundbutler.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;

@Local
public interface SongDAO extends BaseDAO<Song> {
	public void deletar(Song song) throws Exception ;
}
