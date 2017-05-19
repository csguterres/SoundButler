package br.ufes.inf.soundbutler.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.soundbutler.core.domain.Academic;
import br.ufes.inf.soundbutler.core.domain.MarvinConfiguration;
import br.ufes.inf.soundbutler.core.exceptions.SystemInstallFailedException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface InstallSystemService extends Serializable {
	/**
	 * TODO: document this method.
	 * 
	 * @param config
	 * @param admin
	 * @throws SystemInstallFailedException
	 */
	void installSystem(MarvinConfiguration config, Academic admin) throws SystemInstallFailedException;
}
