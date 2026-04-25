package com.smartlibrary.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
  private static SessionFactory factory;
  public static SessionFactory getSessionFactory() {
    if (factory == null) {
      try {
        factory = new Configuration().configure().buildSessionFactory();
      } catch (Exception ex) {
        ex.printStackTrace();
        throw new RuntimeException("Failed to create Hibernate session factory: " + ex.getMessage());
      }
    }
    return factory;
  }
}
