package spring5_component_study.spring;

import java.time.LocalDateTime;

import spring5_component_study.exception.WrongIdPasswordException;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	public void changePassword(String oldPassword,String newPassword) {
		if(!password.equals(oldPassword)) {
			throw new WrongIdPasswordException();
		}
		this.password = newPassword;
	}	
	public Member( String email, String name, String password, LocalDateTime registerDateTime) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.registerDateTime = registerDateTime;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}


	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	
	
	
	
}
