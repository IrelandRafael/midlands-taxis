package com.ait.dto;

public class UserDto {

	private int id;
	private UserCategory category;
	
	public UserDto(int id, UserCategory category) {
		super();
		this.id = id;
		this.category = category;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserCategory getCategory() {
		return category;
	}
	public void setCategory(UserCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "UseDto [id=" + id + ", category=" + category + "]";
	}
}
