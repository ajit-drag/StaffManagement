package com.mindtree.coe.staffmanagement.daoimpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mindtree.coe.staffmanagement.exceptions.DaoException;

/**This class have actual connection with the database.
 * @author M1036033
 *
 */
public class DBConnection {
	/**
	 * This variable holds the actual connection and related configurations
	 */
	SessionFactory factory;

	/**
	 * Default constructor to create connection with the database using configuration defined in hibernate.cfg.xml in resource
	 * @throws DaoException 
	 */
	public DBConnection() throws DaoException {
		try{
		factory = new Configuration().configure().buildSessionFactory();
		}catch(Exception exception){
			throw new DaoException("Database Connection is not available", exception);
		}
	}
	
	/**
	 * @return Session factory object initialized with configuration defined in hibernate.cfg.xml in resource
	 */
	public SessionFactory getSessionFactory(){
		return factory;
	}
}
