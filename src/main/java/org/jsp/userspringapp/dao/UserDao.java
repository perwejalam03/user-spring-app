package org.jsp.userspringapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.userspringapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	EntityManager manager;

	public User saveUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}

	public User updateUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(user);
		transaction.begin();
		transaction.commit();
		return user;
	}

	public User findUser(int id) {
		return manager.find(User.class, id);
	}

	public User verifyUser(long phone, String password) {
		Query q = manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public User verifyUserByEmailAndPassword(String email, String password) {
		Query q = manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public User verifyUserByIdAndPassword(int id, String password) {
		Query q = manager.createQuery("select u from User u where u.id=?1 and u.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean deleteUser(int id) {
		User u = findUser(id);
		if (u != null) {
			EntityTransaction transaction = manager.getTransaction();
			manager.remove(u);
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}

}
