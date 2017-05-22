package br.ufes.inf.soundbutler.core.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.soundbutler.core.domain.Song;
import br.ufes.inf.soundbutler.core.domain.User;
import br.ufes.inf.soundbutler.core.domain.User_;

/**
 * TODO: document this type.
 *
 * @author V�tor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(UserJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext(unitName="SoundButler")
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}
	
//	public UserJPADAO(){
//	}

	/** @see br.ufes.inf.soundbutler.core.persistence.UserDAO#retrieveByEmail(java.lang.String) */
	@Override
	public User retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the user whose e-mail is \"{0}\"...", email);

		// Constructs the query over the User class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get(User_.email), email));
		User result = executeSingleResultQuery(cq, email);
		logger.log(Level.INFO, "Retrieve user by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		return result;
	}
	
	public List<Song> retrieveSongs(String username) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving songs of user \"{0}\"...", username);

		// Constructs the query over the User class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Song> cq = cb.createQuery(Song.class);
		Root<User> root = cq.from(User.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get(User_.name), username));
		SetJoin<User, Song> allSongs = root.join(User_.songs) ;
		CriteriaQuery<Song> cq2 = cq.select(allSongs);
        TypedQuery<Song> query = entityManager.createQuery(cq2);
		
        logger.log(Level.INFO, "Retrieve user by user \"{0}\" returned \"{1}\"", new Object[] { username, query.getResultList() });

        return query.getResultList() ;

	}
	
    public void salvar(User user) {
    	try{
	        if (user.getId() == null) {
	        	System.out.println(user.getEmail()) ;
	    		
	        	System.out.println(entityManager.getClass()) ;
	
	        	entityManager.persist(user);
	        } else {
	            if (!entityManager.contains(user)) {
	                if (entityManager.find(User.class, user.getId()) == null) {
	                    throw new Exception("Erro ao atualizar os dados do Cleinte!");
	                }
	            }
	            entityManager.merge(user);
	        }
    	}catch(Exception e){
			e.printStackTrace();
    	}
    }
    
    public void deletar(User user) {
    	try{
	        if (user.getId() != null) {
	        	User new_user = entityManager.merge(user) ;
	        	entityManager.remove(new_user);
	        }
    	}catch(Exception e){
			e.printStackTrace();
    	}
    }
}