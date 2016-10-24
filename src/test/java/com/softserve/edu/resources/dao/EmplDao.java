package com.softserve.edu.resources.dao;

import com.softserve.edu.resources.entity.EmplDB;
import com.softserve.edu.resources.entity.EmplDB.EmplDBQueries;

public class EmplDao extends ADao<EmplDB> {

	public EmplDao() {
		super();
		init();
	}

	// TODO Create abstract method in ADao
	private void init() {
		for (EmplDBQueries emplDBQueries : EmplDBQueries.values()) {
			sqlQueries.put(emplDBQueries.name(), emplDBQueries);
		}
	}

	protected EmplDB createInstance(String[] args) {
		return new EmplDB(Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2]);
	}

	protected String[] getFields(EmplDB entity) {
		String[] fields = new String[10];
		//fields[0] = entity.getId().toString();
		fields[0] = entity.getFirstname();
		fields[1] = entity.getEmail();
		return fields;
	}

	// TODO DELETE Method
	// public boolean deleteById(Long id) {
	// return super.deleteById(id);
	// }

}
