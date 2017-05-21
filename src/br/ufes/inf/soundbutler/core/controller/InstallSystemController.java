package br.ufes.inf.soundbutler.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.soundbutler.core.application.InstallSystemService;
import br.ufes.inf.soundbutler.core.domain.User;
import br.ufes.inf.soundbutler.core.domain.MarvinConfiguration;
import br.ufes.inf.soundbutler.core.exceptions.SystemInstallFailedException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@ConversationScoped
public class InstallSystemController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/core/installSystem/";

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemController.class.getCanonicalName());

	/** The JSF conversation. */
	@Inject
	private Conversation conversation;

	/** The "Install System" service. */
	@EJB
	private InstallSystemService installSystemService;

	/** Input: the administrator being registered during the installation. */
	private User admin = new User();

	/** Input: the repeated password for the admininstrator registration. */
	private String repeatPassword;

	/** Input: system configuration information. */
	private MarvinConfiguration config = new MarvinConfiguration();

	/** Getter for repeatPassword. */
	public String getRepeatPassword() {
		return repeatPassword;
	}

	/** Setter for repeatPassword. */
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	/** Getter for admin. */
	public User getAdmin() {
		return admin;
	}

	/** Getter for config. */
	public MarvinConfiguration getConfig() {
		return config;
	}

	/**
	 * Checks if both password fields have the same value.
	 * 
	 * This method is intended to be used with AJAX.
	 */
	public void ajaxCheckPasswords() {
		checkPasswords();
	}

	/**
	 * Checks if the contents of the password fields match.
	 * 
	 * @return <code>true</code> if the passwords match, <code>false</code> otherwise.
	 */
	private boolean checkPasswords() {
		if (((repeatPassword != null) && (!repeatPassword.equals(admin.getPassword()))) || ((repeatPassword == null) && (admin.getPassword() != null))) {
			logger.log(Level.INFO, "Password and repeated password are not the same");
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_WARN, "installSystem.error.passwordsDontMatch.summary", "installSystem.error.passwordsDontMatch.detail");
			return false;
		}
		return true;
	}

	/**
	 * Begins the installation process.
	 * 
	 * @return The path to the web page that shows the first step of the installation process.
	 */
	public String begin() {
		logger.log(Level.FINEST, "Beginning conversation. Current conversation transient? -> {0}", new Object[] { conversation.isTransient() });

		// Begins the conversation, dropping any previous conversation, if existing.
		if (!conversation.isTransient()) conversation.end();
		conversation.begin();

		// Go to the first view.
		return VIEW_PATH + "index.xhtml?faces-redirect=true";
	}

	/**
	 * Registers the administrator as one of the steps of system installation and moves to the next step.
	 * 
	 * @return The path to the web page that shows the next step in the installation process.
	 */
	public String registerAdministrator() {

		// Check if passwords don't match. Add an error in that case.
		if (!checkPasswords()) return null;

		// Proceeds to the next view.
		return VIEW_PATH + "config.xhtml?faces-redirect=true";
	}

	/**
	 * Saves the SMTP configuration information and ends the installation process.
	 * 
	 * @return The path to the web page that shows the next step in the installation process.
	 */
	public String saveConfig() {

		// Installs the system.
		try {
			installSystemService.installSystem(config, admin);
		}
		catch (SystemInstallFailedException e) {
			logger.log(Level.SEVERE, "System installation threw exception", e);
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_FATAL, "installSystem.error.installFailed.summary", "installSystem.error.installFailed.detail");
			return null;
		}

		// Ends the conversation.
		conversation.end();

		// Proceeds to the final view.
		return VIEW_PATH + "done.xhtml?faces-redirect=true";
	}
}
