package model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import controls.LogInController;
import javafx.scene.layout.VBox;
import util.UserDisplay;

@SuppressWarnings("serial")
public class UserList implements Serializable {

	private static UserList instance = null;
	private static TreeMap<String, User> list;
	private static User active = LogInController.getActiveUser();
	private static User clicked;

//	Singleton Constructor
	private UserList() {
		list = new TreeMap<>();
	}

//	Singleton Instance
	public static UserList getInstance() {
		if (instance == null) {
			instance = new UserList();
		}
		return instance;
	}

//	Getters and Setters
	public User getActive() {
		return active;
	}
	
	public void setActive(User u) {
		active = get(u);
	}

	public User getClicked() {
		return clicked;
	}

	public void setClicked(User u) {
		clicked = get(u);
	}

	public TreeMap<String, User> getList() {
		return list;
	}
	
	public void setList(TreeMap<String, User> l) {
		list = l;
	}

//	Add a User with Key UserName
	public void add(User u) {
		list.put(u.getUsername().toLowerCase(), u);
	}

//	Get a User by User
	public User get(User u) {
		return list.get(u.getUsername().toLowerCase());
	}

//	Get a User by Key UserName
	public User get(String s) {
		return list.get(s);
	}
	
//  Deletes a User
	public void deleteUser(User u) {
		list.remove(u.getUsername());
	}

//	Check if User Exists
	public boolean contains(User u) {
		if (list.containsValue(u)) {
			return true;
		}
		return false;
	}

//	Check if UserName Exists
	public boolean contains(String s) {
		if (list.containsKey(s.toLowerCase())) {
			return true;
		}
		return false;
	}

//	Display All Users
	public VBox displayUsers() {
		VBox view = new VBox();
		for (Map.Entry<String, User> u : list.entrySet()) {
			UserDisplay temp = new UserDisplay(u.getValue());
			view.getChildren().add(temp.getUserDisplay());
		}
		return view;
	}

}
