package br.ufes.inf.soundbutler.core.domain;

import br.ufes.inf.soundbutler.core.domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-17T15:51:32.684-0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Song> songs ;
}
