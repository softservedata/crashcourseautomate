package com.softserve.edu.resources.data;

public final class UserRepository {

	private UserRepository() {
	}

	public static User getAdmin() {
		return new User("�����������", "�����������",
				"admin@admin.com", "admin", "admin", "������");
	}

	public static User getRegistrator() {
		return new User("registrator", "registrator",
				"harasym@mail.ua", "registrator", "registrator", "������");
	}

	public static User getNewUser() {
		return new User("registrator1", "registrator1",
				"harasym@mail.ua", "registrator1", "registrator1", "������");
	}

	// public static User getFromDB() {
	// MyDBManager dbm = new MyDBManager();
	// return dbm.getUser();
	// }

}
