package org.diagram;

// Generated Mar 19, 2012 8:41:08 PM by Hibernate Tools 3.4.0.CR1

/**
 * Dog generated by hbm2java
 */
public class Dog implements java.io.Serializable {

	private long id;
	private String name;

	public Dog() {
	}

	public Dog(long id) {
		this.id = id;
	}

	public Dog(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
