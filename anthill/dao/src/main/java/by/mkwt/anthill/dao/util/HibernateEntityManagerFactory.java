package by.mkwt.anthill.dao.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateEntityManagerFactory {
	
	public final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("anthill");
	
	private HibernateEntityManagerFactory() {
	
	}
	
}
