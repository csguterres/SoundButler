package br.ufes.inf.soundbutler.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;

@Local
public interface ManageSongsService extends CrudService<Song>{
	void deletar(Song song);
}
