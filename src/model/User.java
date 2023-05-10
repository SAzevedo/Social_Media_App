package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class User implements Serializable {
	Name name;
	String username;
	String password;
	LinkedList<Post> postList;
	LinkedList<User> following;
	LocalDate date;

	public User(Name name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.postList = new LinkedList<>();
		this.following = new LinkedList<>();
		this.date = LocalDate.now();
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LinkedList<Post> getPostList() {
		return postList;
	}

	public LinkedList<User> getFollowing() {
		return following;
	}

	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return name + ", username: " + username;
	}

}
