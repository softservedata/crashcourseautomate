package com.softserve.edu.resources.data;

public final class UserRepository {

	private UserRepository() {
	}

	public static User getAdmin() {
		return new User("Адміністратор", "Адміністратор",
				"admin@admin.com", "admin", "admin", "Україна");
	}

	public static User getRegistrator() {
		return new User("registrator", "registrator",
				"harasym@mail.ua", "registrator", "registrator", "Україна");
	}

	public static User getNewUser() {
		return new User("registrator13", "registrator1",
				"harasym@gmail3.ua", "registrator1", "registrator1", "Україна");
	}

	// public static User getFromDB() {
	// MyDBManager dbm = new MyDBManager();
	// return dbm.getUser();
	// }

}
