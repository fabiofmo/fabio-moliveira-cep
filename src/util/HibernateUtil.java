package util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			Logger.getLogger("org.hibernate").setLevel(Level.OFF);	        
			Configuration configuration = new Configuration().configure("util/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
					
		} catch(Throwable ex){
			System.err.println(ex);
			throw new ExceptionInInitializerError(ex);
		}		
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
