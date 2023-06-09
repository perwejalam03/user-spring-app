package org.jsp.userspringapp.controller;

import java.util.Scanner;

import org.jsp.userspringapp.UserConfig;
import org.jsp.userspringapp.dao.UserDao;
import org.jsp.userspringapp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	static Scanner sc = new Scanner(System.in);
	static UserDao dao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		dao = context.getBean(UserDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1. Save USer");
		System.out.println("2. Update User");
		System.out.println("3. Verify User by Phone and Password");
		System.out.println("4. Verify User by Email and Password");
		System.out.println("5. Verify User by Id and Password");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			verify();
			break;
		}
		case 4: {
			verifyByEandP();
			break;
		}
		case 5: {
			verifyByIandP();
			break;
		}
		default: {
			System.out.println("Invalid Choice");
		}
		}
	}

	public static void save() {
		System.out.println("Enter name, phone email and password");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String password = sc.next();
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
		u.setPhone(phone);
		u = dao.saveUser(u);
		System.out.println("User Saved with Id:" + u.getId());
	}

	public static void update() {
		System.out.println("Enter Id to Update Details ");
		int id = sc.nextInt();
		System.out.println("Enter name, phone email and password");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String password = sc.next();
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
		u.setPhone(phone);
		u = dao.updateUser(u);
		System.out.println("User Updated with Id:" + u.getId());
	}

	public static void verify() {
		System.out.println("Enter Phone and Password");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = new User();
		u = dao.verifyUser(phone, password);
		if (u != null) {
			System.out.println("Name:" + u.getName());
			System.out.println("Phone:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("Password:" + u.getPassword());
		}
		 else {
				System.err.println("Invalid Phone or Password");
			}
	}

	public static void verifyByEandP() {
		System.out.println("Enter Email and Password");
		String email = sc.next();
		String password = sc.next();
		User u = dao.verifyUserByEmailAndPassword(email, password);
		if (u != null) {
			System.out.println("Name:" + u.getName());
			System.out.println("Phone:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("Password:" + u.getPassword());
		} else {
			System.err.println("Invalid Email or Password");
		}
	}

	public static void verifyByIandP() {
		System.out.println("Enter Id and Password");
		int id = sc.nextInt();
		String password = sc.next();
		User u = dao.verifyUserByIdAndPassword(id, password);
		if (u != null) {
			System.out.println("Name:" + u.getName());
			System.out.println("Phone:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("Password:" + u.getPassword());
		} else {
			System.err.println("Invalid Id or Password");
		}
	}

}
