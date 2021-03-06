package com.psl.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.psl.entity.User;
import com.psl.util.HibernateUtility;

public class UserDao {
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String authenticateUser(String username, String password) {
		Session session = HibernateUtility.getSesssion();

		Query query = session.createQuery("from User where username='" + username + "' AND password='" + password + "'");

		List<User> list = query.list();
		System.out.println("Size" + list.size());
		for (User user : list) {
//			System.out.println(user);
//			return user.getUsername();
			return user.getUserType();
			//return user.getUserType() + " " + user.getEmployeeFirstName() + " "+ user.getEmployeeID();

		}
		return null;
	}

	public boolean addUser(User user) {
		
		Session session = HibernateUtility.getSesssion();

		Query q = session.createQuery("from User ");

		List<User> list = q.list();
		System.out.println("Size:" + list.size());
		if (list.size() == 0) {
			session.save(user);
			session.beginTransaction().commit();
			return true;
		} else {
			for (User s : list) {
				System.out.println(s);
				if (s.getUsername().equalsIgnoreCase(user.getUsername())) {
					System.out.println("already present");
					return false;
				} else {
					session.save(user);
					session.beginTransaction().commit();
				}
			}
			return true;
		}

	}

//	public static void main(String[] args) {
//		UserDao userd = new UserDao();
//		User u = new User(101, "anuja", "N", "admin1", "12345", "Administrator");
//		userd.setUser(u);
//		userd.addUser(u);
//		System.out.println(userd.authenticateUser("Ganesh", "ganesh"));
//	}

}
