package by.mkwt.anthill.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import by.mkwt.anthill.entity.user.Skill;
import by.mkwt.anthill.entity.user.User;
import javassist.expr.NewArray;

public class Main {

	public static void main(String[] args) {
//		Configuration configuration = new Configuration().configure();
//        configuration.addAnnotatedClass(Skill.class);
//        configuration.addAnnotatedClass(User.class);
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
//        
//        Session session = sessionFactory.openSession();
//        
//        Transaction tx = session.getTransaction();
//        
//        try {
//        	tx.begin();
//        	session.persist(new Skill("name"));
//        	tx.commit();
//        	
//        	
//        	System.out.println("sdfsdf");			
//		} catch (ConstraintViolationException e) {
//			System.out.println(e.getCause());
//			session.close();
//			sessionFactory.close();
//		}
	}
}
