package ir.maktab56.Twitter.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class HibernateUtil {

    private static EntityManagerFactory entityManagerFactory;
    
    private HibernateUtil() {
    }

    public static synchronized EntityManagerFactory getEntityMangerFactory() {
        if(entityManagerFactory == null) {
        	entityManagerFactory = Persistence.createEntityManagerFactory("Unit");
        }
        
    	return entityManagerFactory;
    }
}