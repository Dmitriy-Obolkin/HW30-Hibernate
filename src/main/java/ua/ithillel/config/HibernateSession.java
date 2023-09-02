package ua.ithillel.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
   private static final SessionFactory sessionFactory;

   static {
       Configuration configuration = new Configuration();
       configuration.configure();
//       configuration.addAnnotatedClass(Student.class); OR <mapping class="ua.ithillel.model.entity.Student"/> in hibernate.cfg.xml

       configuration.setProperty("hibernate.connection.url", System.getenv("JDBC_URL"));
       configuration.setProperty("hibernate.connection.username", System.getenv("JDBC_USERNAME"));
       configuration.setProperty("hibernate.connection.password", System.getenv("JDBC_PASSWORD"));

       sessionFactory = configuration.buildSessionFactory();
   }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
