package ar.edu.unq.tpi.persistencia.bean;

import java.io.Serializable;

public class Entity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}	
}
