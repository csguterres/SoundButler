package br.ufes.inf.soundbutler.core.application;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


@Named
@Stateless
public class EmailService {
	private String name;
	private String email;
	private String message;
	
	
	public String send(){
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("soundbutlerufes", "soundbutler123"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom(this.getEmail());
			email.setSubject(this.getName()+" - TestMail");
			email.setMsg(this.getMessage());
			email.addTo("gabrielreisnf@gmail.com");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		 return "/contactus/send.xhtml";
		
		

	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
}
