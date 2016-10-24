package com.softserve.edu.resources.services;

import java.util.List;

import com.softserve.edu.resources.dao.EmplDao;
import com.softserve.edu.resources.data.IUser;
import com.softserve.edu.resources.data.User;
import com.softserve.edu.resources.entity.EmplDB;

public class EmplService {

	// Create
	public boolean insertUser(IUser user) {
        return new EmplDao().insert(new EmplDB(user.getFirstname(), user.getEmail()));
	}
    
    // Read
    public IUser getEmplByEmail(String email) {
        //UserDB userDB = UserDao.get().getUserDBByLogin(login);
    	List<EmplDB> result = new EmplDao().getByFieldName("email", email);
    	if (result.isEmpty()) {
    		return new User("", "", "", "", "", "");
    	}
    	EmplDB emplDB = result.get(0);
        return new User(emplDB.getFirstname(), "", emplDB.getEmail(), "", "", "");
    }

    // Delete
    public boolean deleteUsersByEmail(String email) {
        //return UserDao.get().delete(UserDao.get().getUserDBByLogin(login));  
        return new EmplDao().delete(new EmplDao().getByFieldName("email", email).get(0));  
    }

    public boolean deleteUsersById(Long id) {
        //return UserDao.get().deleteById(id);  
        return new EmplDao().deleteById(id);  
    }

}
