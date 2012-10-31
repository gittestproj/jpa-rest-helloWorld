package com.acme.service;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.acme.domain.Person;

@Path("userListing")
public class UserListing {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private List<Person> users;

	//@PersistenceContext - I wish
	EntityManager em;
	UserTransaction ut;
	
	public UserListing() {
		initDb();
	}

	private void initDb() {
		try {
			ut = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			logger.info("ut: " + ut);
			
			em = ((EntityManagerFactory) (new InitialContext()).lookup("java:comp/env/jphw/emf")).createEntityManager();
			logger.info("em: " + em);
			
			this.users = em.createQuery("from Person").getResultList();

			if (this.users.size() == 0) {
				em.persist(new Person("Hello"));
				em.persist(new Person("World"));
				this.users = em.createQuery("from Person").getResultList();
			}
			em.close();
			ut.commit();
		} catch (Exception e) {
			if (ut != null) {
				try {
					ut.rollback();
				} catch (Exception e1) {
					logger.error("Rollback problem", e1);
				}
			}
			logger.error("DB init problem", e);
		}
	}

	@GET
	@Path("person/all")
	@Produces("application/json")
	public List<Person> getAll() {
		return this.users;
	}
}
