package com.softserve.edu.resources.entity;

public class EmplDB implements IEntity {
	
    public static enum EmplDBQueries {
        INSERT("INSERT INTO empl (firstname, email) VALUES ('%s', '%s');"),
        GET_BY_ID("SELECT id, firstname, email FROM empl WHERE id = '%s';"),
        GET_BY_FIELD("SELECT id, firstname, email FROM empl WHERE %s = '%s';"),
        GET_ALL("SELECT id, firstname, email FROM empl;"),
        UPDATE_BY_FIELD("UPDATE empl SET %s = '%s';"),
        DELETE_BY_ID("DELETE FROM empl WHERE id = '%s';");
    	//
        private String query;

        private EmplDBQueries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
    
    private Long id;
    private String firstname; 
    private String email;

    public EmplDB(Long id, String firstname, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.email = email;
	}

    public EmplDB(String firstname, String email) {
		super();
		this.firstname = firstname;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getEmail() {
		return email;
	}

}
